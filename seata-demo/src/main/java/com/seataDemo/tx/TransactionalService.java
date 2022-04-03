package com.seataDemo.tx;

import com.seataDemo.entity.pojo.Product;

public interface TransactionalService {


    void insertDis(Product product);
}
