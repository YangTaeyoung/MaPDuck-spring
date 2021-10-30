package com.mapduck.service;

import com.mapduck.domain.Product;
import com.mapduck.repository.MemoryProductRepository;
import com.mapduck.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
        return productRepository.findByKeyword(keyword);
    }
}
