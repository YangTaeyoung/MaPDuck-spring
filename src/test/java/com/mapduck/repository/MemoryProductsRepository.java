package com.mapduck.repository;

import com.mapduck.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryProductsRepository {


    MemoryProductRepository repository =  new MemoryProductRepository();

    @Test
    public void findByKeyword() {
        Product product1 = new Product();
        product1.setTitle("삼성 1");
        product1.setDescription("kkkkk");
        repository.save(product1);

        Product product2 = new Product();
        product2.setTitle("삼성 2");
        product2.setDescription("kkkkk");
        repository.save(product2);

        Product product3 = new Product();
        product3.setTitle("엘쥐 2");
        product3.setDescription("kkkkk");
        repository.save(product3);

        List<Product> result = repository.findByKeyword("삼성");

        Assertions.assertThat(result.size()).isEqualTo(2);




    }

}
