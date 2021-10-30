package com.mapduck.repository;

import com.mapduck.domain.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 임시 저장소
public class MemoryProductRepository implements ProductRepository{

//    private Map<String,Product> store = new HashMap<>();
    private List<Product> store = new ArrayList<>();

    @Override
    public Product save(Product product) {
        store.add(product);
        return product;
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
         List<Product> products = store.stream().filter(product -> product.getTitle().contains(keyword)).collect(Collectors.toList());
         return products;

    }
}
