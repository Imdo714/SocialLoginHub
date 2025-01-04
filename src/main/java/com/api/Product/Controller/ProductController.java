package com.api.Product.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @ResponseBody
    @GetMapping(value="/insert", produces="application/json; charset=UTF-8")
    public void savProduct(){

    }
}
