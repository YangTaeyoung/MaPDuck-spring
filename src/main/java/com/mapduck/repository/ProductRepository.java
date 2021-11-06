package com.mapduck.repository;

import com.mapduck.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByPrNameContainsOrMoNameContainsOrPrCompany_CoName(String prName, String moName, String prCompany_coName);
}
