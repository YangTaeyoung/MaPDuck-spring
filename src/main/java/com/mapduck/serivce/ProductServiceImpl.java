package com.mapduck.serivce;

import com.mapduck.advice.RestException;
import com.mapduck.domain.Product;
import com.mapduck.domain.Warranty;
import com.mapduck.dto.ProductDto;
import com.mapduck.dto.ProductReqDto;
import com.mapduck.repository.ProductRepository;
import com.mapduck.repository.WarrantyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private final WarrantyService warrantyService;

    /**
     * 작성자: 양태영
     * 작성일: 21.11.14
     * 설명: 엔티티에서 DTO로 변환하는 함수
     * @param product: 변환 전 엔티티
     * @return 변환 후 DTO
     */
    public ProductDto entityToResDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setPrId(product.getPrId());
        productDto.setImgPath(product.getImgPath());
        productDto.setPrCompany(companyService.entityToDto(product.getPrCompany()));
        productDto.setMoName(product.getMoName());
        productDto.setPrName(product.getPrName());
        productDto.setDescription(product.getDescription());
        productDto.setWarrantyDto(warrantyService.getMaxWarrantyDto(product.getWarranties()));
        return productDto;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.15
     * 설명: request Dto에서 entity로 변환하는 매핑함수
     * @param productReqDto
     * @return product Entity
     */
    public Product reqDtoToEntity(ProductReqDto productReqDto){
        var product = new Product();
        product.setPrName(productReqDto.getPrName());
        product.setMoName(productReqDto.getMoName());
        product.setDescription(productReqDto.getDescription());
        product.setImgPath(productReqDto.getImgPath());
        product.setPrCompany(companyService.dtoToEntity(productReqDto.getCompanyDto()));
        return product;
    }
    /**
     * 작성자 : 강동연
     * 작성일 : 2021.10.31
     * 설명: 제품dto 받아서 저장소에 저장
     *
     * @param productReqDto: 상품 dto
     * @return 저장한 상품 정보를 리턴
     *
     * 수정자: 양태영
     * 수정일: 21.11.07
     * 수정내용: DTO에 ModelMapper을 사용해 Entity로 변환한 후 저장할 수 있도록 변경.
     *
     * 수정자: 강동연
     * 수정일:21.11.15
     * 수정내용: 보장 기간 공백 시 저장 하지 않는 로직 추가
     */
    @Transactional
    @Override
    public Product save(ProductReqDto productReqDto) {
        Product product = reqDtoToEntity(productReqDto);
        product = productRepository.save(product);
        log.info("saved prId: {}", product.getPrId());

        if (productReqDto.getWrMonth() != 0) {
            Warranty warranty = new Warranty();
            warranty.setPrId(product);
            warranty.setCount(1);
            warranty.setMonth(productReqDto.getWrMonth());
            warrantyService.save(warranty);
        }

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
