package com.seataDemo.tx.impl;

import com.seataDemo.entity.pojo.Price;
import com.seataDemo.entity.pojo.Product;
import com.seataDemo.service.PriceService;
import com.seataDemo.service.ProductService;
import com.seataDemo.tx.TransactionalService;
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
    @GlobalTransactional(rollbackFor = Exception.class)
    public void insertDis(Product product) {

        try {
            this.productService.add(product);

            Price price = new Price();
            price.setProductId(product.getId());
            price.setPrice(new BigDecimal(10000));
            this.priceService.add(price);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("出现异常");
        }

    }
}
