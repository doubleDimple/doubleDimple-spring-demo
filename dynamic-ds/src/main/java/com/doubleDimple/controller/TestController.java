package com.doubleDimple.controller;

import com.doubleDimple.entity.pojo.Price;
import com.doubleDimple.entity.pojo.Product;
import com.doubleDimple.service.PriceService;
import com.doubleDimple.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PriceService priceService;

    @RequestMapping("/test/query")
    public String getPrice(){
        Product productByPrimaryKey = productService.getProductByPrimaryKey(11L);
        Price priceByPrimaryKey = priceService.getPriceByPrimaryKey(1l);
        System.out.println(priceByPrimaryKey);
        return priceByPrimaryKey.toString();
    }
}
