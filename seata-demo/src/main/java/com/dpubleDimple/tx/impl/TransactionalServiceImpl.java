package com.dpubleDimple.tx.impl;

import com.dpubleDimple.entity.pojo.Price;
import com.dpubleDimple.entity.pojo.Product;
import com.dpubleDimple.service.PriceService;
import com.dpubleDimple.service.ProductService;
import com.dpubleDimple.tx.TransactionalService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionalServiceImpl implements TransactionalService {

    @Autowired
    private ProductService productService;

    @Autowired
    private PriceService priceService;

    @Override
    @GlobalTransactional
    public void insertDis(Product product) {

        this.productService.add(product);

        Price price = new Price();
        price.setProductId(product.getId());
        price.setPrice(new BigDecimal(10000));
        this.priceService.add(price);

    }
}
