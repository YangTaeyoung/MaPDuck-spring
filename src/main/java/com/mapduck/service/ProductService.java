package com.mapduck.service;

import com.mapduck.domain.Product;

import java.util.List;

public interface ProductService {

    void save(Product product);
    List<Product> findByKeyword(String keyword);
}
