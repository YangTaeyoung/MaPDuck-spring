package com.mapduck.serivce;

import com.mapduck.advice.RestException;
import com.mapduck.domain.Product;
import com.mapduck.dto.ProductDto;
import com.mapduck.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 작성자 : 강동연
 * 작성일 : 2021.10.31
 * 설명 : 제품 서비스 구현체
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    public ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setPrId(product.getPrId());
        productDto.setImgPath(product.getImgPath());
        productDto.setPrCompany(companyService.entityToDto(product.getPrCompany()));
        productDto.setMoName(product.getMoName());
        productDto.setPrName(product.getPrName());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
    /**
     * 작성자 : 강동연
     * 작성일 : 2021.10.31
     * 설명: 제품dto 받아서 저장소에 저장
     *
     * @param productDto: 상품 dto
     * @return 저장한 상품 정보를 리턴
     *
     * 수정자: 양태영
     * 수정일: 21.11.07
     * 수정내용: DTO에 ModelMapper을 사용해 Entity로 변환한 후 저장할 수 있도록 변경.
     *
     */
    @Override
    public Product save(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        log.info("pr_name :{}", product.getPrName());
        log.info("mo_name :{}", product.getMoName());

        return productRepository.save(product);
    }

    /**
     * 작성자 : 강동연
     * 작성일 : 2021.10.31
     * 설명: keyword로 db에 관련 있는 제품 찾기
     *
     * @param keyword: 검색어
     * @return List<ProductDto>
     *
     * 수정자: 양태영
     * 수정일: 21.11.07
     * 수정내용: keyword를 통해 DB를 상품명, 모델명, 회사이름을 기준으로 검색하고 검색 결과를 Entity List로 내보내도록 설정
     */
    @Override
    public List<ProductDto> findByKeyword(String keyword) {
        List<Product> products = productRepository
                .findProductByPrNameContainsOrMoNameContainsOrPrCompany_CoName(
                        keyword, keyword, keyword
                );
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: products){
            productDtos.add(modelMapper.map(product,ProductDto.class));
        }
        return productDtos;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: 상품 아이디를 기준으로 상품엔티티를 가져오는 함수
     * @param id: 상품 아이디 정보
     * @return 검색한 상품 없으면 에러 발생시킴.
     */
    public Product getById(Long id){
        return productRepository.findById(id).orElseThrow(()->new RestException(HttpStatus.NOT_FOUND, "해당 id와 일치하는 상품을 찾을 수 없습니다."));
    }
}
