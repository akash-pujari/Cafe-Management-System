package com.inn.cafe.serviceImpl;

import com.inn.cafe.dao.ProductDao;
import com.inn.cafe.jwt.JwtAuthenticationFilter;
import com.inn.cafe.pojo.Category;
import com.inn.cafe.pojo.Product;
import com.inn.cafe.service.ProductService;
import com.inn.cafe.utils.CafeUtils;
import com.inn.cafe.wrapper.ProductWrapper;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.inn.cafe.constants.CafeConstants.SOMETHING_WENT_WRONG;


@Service
@Setter
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    JwtAuthenticationFilter filter;

    @Autowired
    ProductDao productDao;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestBody) {
        try {
            if (filter.isAdmin()) {
                log.info("Into add product");
                if (validateProductMap(requestBody, false)) {
                    productDao.save(getProductFromMap(requestBody, false));
                    return CafeUtils.getResponse("Product added successfully!", HttpStatus.OK);

                } else {
                    return CafeUtils.getResponse("Invalid data", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponse("Only admin can add product", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponse(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try {
            List<ProductWrapper> products = productDao.getAllProduct();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Product getProductFromMap(Map<String, String> requestBody, boolean isAdd) {
        Category category = new Category();
        Product product = new Product();
        category.setId(Integer.parseInt(requestBody.get("id")));
        product.setCategory(category);

        if (isAdd) {
            product.setId(Integer.parseInt(requestBody.get("id")));
        } else {
            product.setStatus("true");
        }
        product.setName(requestBody.get("name"));
        product.setDescription(requestBody.get("description"));
        product.setPrice(Double.parseDouble(requestBody.get("price")));


        return product;
    }

    private boolean validateProductMap(Map<String, String> requestBody, boolean validateId) {
        if (requestBody.containsKey("name")) {
            if (requestBody.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }

        }
        return false;
    }

}
