package com.mapduck.repository;

import com.mapduck.domain.Product;
import com.mapduck.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByPrNameContainsOrMoNameContainsOrPrCompany_CoName(String prName, String moName, String prCompany_coName);
}
