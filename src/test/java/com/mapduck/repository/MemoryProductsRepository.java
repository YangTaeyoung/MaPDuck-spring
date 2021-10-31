package com.mapduck.repository;


import com.mapduck.domain.ProductDto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryProductsRepository {


    MemoryProductRepository repository =  new MemoryProductRepository();

    @Test
    public void findByKeyword() {

        ProductDto productDto1 = new ProductDto();
        productDto1.setTitle("삼성 1");
        productDto1.setDescription("kkkkk");
        repository.save(productDto1);

        ProductDto productDto2 = new ProductDto();
        productDto2.setTitle("삼성 2");
        productDto2.setDescription("kkkkk");
        repository.save(productDto2);

        ProductDto productDto3 = new ProductDto();
        productDto3.setTitle("엘쥐 2");
        productDto3.setDescription("kkkkk");
        repository.save(productDto3);

        List<ProductDto> result = repository.findByKeyword("삼성");


        Assertions.assertThat(result.size()).isEqualTo(2);




    }

}
