package com.unicam.hackhub.Controller;

import com.unicam.hackhub.Model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private static Map<Integer, Product> productRepository = new HashMap<>();
    static {
        Product miele = new Product(1,"miele",23.67);
        Product zucchero = new Product(2,"zucchero",10.89);

        productRepository.put(miele.getId(), miele);
        productRepository.put(zucchero.getId(), zucchero);
    }

    @RequestMapping(value = "/prod")
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(productRepository.values(), HttpStatus.OK);
    }




}
