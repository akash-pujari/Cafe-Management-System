package com.inn.cafe.restImpl;

import com.inn.cafe.rest.BillRest;
import com.inn.cafe.service.BillService;
import com.inn.cafe.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.inn.cafe.constants.CafeConstants.SOMETHING_WENT_WRONG;

@RestController
public class BillRestImpl implements BillRest {

    @Autowired
    private BillService billService;

    @Override
    public ResponseEntity<String> generateReport(Map<String, String> requestBody) {
        try {
            return billService.generateReport(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponse(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
