package com.mapduck.serivce;


import com.mapduck.dto.NaverIdPwDto;
import com.mapduck.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 작성자: 강동연
 * 작성일: 2021.11.08
 * 설명: Naver id,pw로 Server to Server 통신 하기위한 RestTemplate
 */
@Service
@Slf4j
public class NaverRestTemplateService {


    /**
     * 작성자: 강동연
     * 작성일: 2021.11.08
     * @param naverIdPwDto: 네이버 id와 pw로 이루어진 객체
     * @return List<ProductDto>
     */
    public List<ProductDto> getNaverMyproducts(NaverIdPwDto naverIdPwDto) {

        // uri 빌드
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8000")
                .path("/api/naver/myproducts")
                .encode(Charset.defaultCharset())
                .build()
                .toUri();

        log.info("uri: {}", uri.toString());

        // Id와 pw의 정보를 headers에 저장
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("id", naverIdPwDto.getId());
        headers.add("password", naverIdPwDto.getPassword());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        // exchange를 통해 uri와, Get, headers 정보를 넣어서 ProductDto[]를 반환받는다.
        ResponseEntity<ProductDto[]> result = restTemplate.exchange(uri, HttpMethod.GET, entity, ProductDto[].class);
        List<ProductDto> result_list = Arrays.asList(Objects.requireNonNull(result.getBody())); // 결과 null일 경우 생각해야할듯.

        log.info("result.getStatusCode: {}", result.getStatusCode());

        return result_list;
    }
}
