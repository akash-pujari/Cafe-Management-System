package com.inn.cafe.restImpl;

import com.inn.cafe.rest.ProductRest;
import com.inn.cafe.service.ProductService;
import com.inn.cafe.utils.CafeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.inn.cafe.constants.CafeConstants.SOMETHING_WENT_WRONG;

@RestController
public class ProductRestImpl implements ProductRest {

    private static final Logger log = LoggerFactory.getLogger(ProductRestImpl.class);
    @Autowired
    ProductService productService;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestBody) {
        try {
            log.info("Into productRestImpl adding product");
            return productService.addNewProduct(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponse(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
