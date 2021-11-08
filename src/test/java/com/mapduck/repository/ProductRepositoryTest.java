package com.mapduck.repository;

import com.mapduck.domain.Company;
import com.mapduck.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    void product_save_test() {
        // given\
        Company company = new Company();
        company.setCoName("inha");
        final Product product = new Product();
        product.setPrCompany(company);
        product.setPrName("spring");
        product.setMoName("taeYoung");

        // when
        Product save = productRepository.save(product);

        // then
        Assertions.assertThat(save.getPrName()).isEqualTo("spring");
    }

}