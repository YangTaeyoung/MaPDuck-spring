package com.mapduck.repository;

import com.mapduck.dto.ProductDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryProductsRepositoryTest {


    MemoryProductRepository repository =  new MemoryProductRepository();

    // 수정자: 양태영
    // 수정일: 21.11.07
    // 수정내용: 레파지토리 변경으로 테스트 코드 전체 주석처리
    @Test
    public void findByKeyword() {
//        ProductDto productDto1 = new ProductDto();
//        productDto1.setName("삼성 1");
//        productDto1.setDescription("kkkkk");
//        repository.save(productDto1);
//
//        ProductDto productDto2 = new ProductDto();
//        productDto2.setName("삼성 2");
//        productDto2.setDescription("kkkkk");
//        repository.save(productDto2);
//
//        ProductDto productDto3 = new ProductDto();
//        productDto3.setName("엘쥐 2");
//        productDto3.setDescription("kkkkk");
//        repository.save(productDto3);
//
//        List<ProductDto> result = repository.findByKeyword("삼성");
//
//        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
