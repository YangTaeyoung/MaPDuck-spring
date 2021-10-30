package com.mapduck.repository;

import com.mapduck.domain.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);
    List<Product> findByKeyword(String keyword);

}
