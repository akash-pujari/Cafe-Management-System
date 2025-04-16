package com.inn.cafe.serviceImpl;

import com.inn.cafe.constants.CafeConstants;
import com.inn.cafe.dao.BillDao;
import com.inn.cafe.jwt.JwtAuthenticationFilter;
import com.inn.cafe.pojo.Bill;
import com.inn.cafe.service.BillService;
import com.inn.cafe.utils.CafeUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.FileOutputStream;
import java.util.Map;
import java.util.stream.Stream;

import static com.inn.cafe.constants.CafeConstants.SOMETHING_WENT_WRONG;

@Service
public class BillServiceImpl implements BillService {
    private static final Logger log = LoggerFactory.getLogger(BillServiceImpl.class);
    @Autowired
    private BillDao billDao;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Override
    public ResponseEntity<String> generateReport(Map<String, String> requestBody) {
        log.info("Inside generateReport");
        try {
            String filename;
            if (validateRequestMap(requestBody)) {
                if (requestBody.containsKey("isGenerate") && !Boolean.parseBoolean(requestBody.get("isGenerate"))) {
                    filename = (String) requestBody.get("uuid");
                } else {
                    filename = CafeUtils.getUUID();
                    requestBody.put("uuid", filename);
                    insertBill(requestBody);
                }

                String data = "Name: " + requestBody.get("name") + "\n" + "Contact Number: " + requestBody.get("contactNumber") +
                        "\n" + "Email: " + requestBody.get("email") + "\n" + "Payment Method: " + requestBody.get("paymentMethod");
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(CafeConstants.STORE_LOCATION + "\\" + filename + ".pdf"));
                document.open();
                setRectangleInPdf(document);
                Paragraph chunk = new Paragraph("CAFE MANAGEMENT SYSTEM", getFont("Header"));
                chunk.setAlignment(Element.ALIGN_CENTER);
                document.add(chunk);

                Paragraph paragraph = new Paragraph(data + "\n \n", getFont("Data"));
                document.add(paragraph);

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);

                addTableHeader(table);

                JSONArray jsonArray=CafeUtils.getJsonArrayFromString(requestBody.get("productDetails"));
                for (int i = 0; i < jsonArray.length(); i++) {
                   /*addRow();*/
                }

            }
            return CafeUtils.getResponse("Required data not found", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponse(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void addTableHeader(PdfPTable table) {
        log.info("Inside table header");
        Stream.of("Name", "Category", "Quantity", "Price", "Sub Total").forEach(coumnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(coumnTitle));
            header.setBackgroundColor(BaseColor.YELLOW);
            header.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);
        });
    }

    private Font getFont(String header) {
        log.info("Inside getFont");
        switch (header) {
            case "hear":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;

            case "Data":
                Font headerData = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                headerData.setStyle(Font.BOLD);
                return headerData;
            default:
                return new Font();

        }
    }

    private void setRectangleInPdf(Document document) throws DocumentException {
        log.info("Inside setRectangular Pdf");
        Rectangle rectangle = new Rectangle(577, 825, 15, 15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBackgroundColor(BaseColor.BLACK);
        rectangle.setBorderWidth(1);
        document.add(rectangle);
    }

    private void insertBill(Map<String, String> requestBody) {
        try {
            Bill bill = new Bill();
            bill.setUuid((String) requestBody.get("uuid"));
            bill.setName(requestBody.get("name"));
            bill.setEmail(requestBody.get("email"));
            bill.setContactNumber(requestBody.get("contactNumber"));
            bill.setPaymentMethod(requestBody.get("paymentMethod"));
            bill.setTotal(Integer.parseInt(requestBody.get("total")));
            bill.setProductdetails(requestBody.get("productDetails"));
            bill.setCreatedBy(filter.getCurrentUser());
            billDao.save(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateRequestMap(Map<String, String> requestBody) {

        return requestBody.containsKey("namr") && requestBody.containsKey("contactNumber")
                && requestBody.containsKey("email") && requestBody.containsKey("paymentMethod")
                && requestBody.containsKey("productDetails") && requestBody.containsKey("total");
    }
}
