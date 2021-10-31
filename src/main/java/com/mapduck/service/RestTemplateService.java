package com.mapduck.service;



import com.mapduck.domain.ProductDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;


/**
 * 작성자 : 강동연
 * 작성일 : 2021.10.31
 * 설명: keyword 받아서 장고와 api 통신 후 크롤링된 결과값 반환
 */
@Service
public class RestTemplateService {


    /**
     * 작성자 : 강동연
     * 작성일 : 2021.10.31
     * 설명 : uri 빌드 후, RestTemplate 객체 생성 후 .getForEntity로 통신 후 result로 결과 받음.
     * @param keyword : 검색어
     * @return List<ProductDto>
     */
    public List<ProductDto> keyword(String keyword) {

        // uri 빌드
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8000")
                .path("/api/danawa/result")
                .queryParam("keyword",keyword)
                .encode(Charset.defaultCharset())
                .build()
                .toUri();

//        System.out.println(uri.toString());


        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<ProductDto[]> result = restTemplate.getForEntity(uri, ProductDto[].class);
        List<ProductDto> result_list = Arrays.asList(result.getBody()); // 결과 null일 경우 생각해야할듯.


        //String result = restTemplate.getForObject(uri, String.class);
        //getForEntity는 응답을 ResponseEntity로 받을 수 있도록 해준다 .
        //파라미터 첫번째는 요청 URI 이며 , 2번째는 받을 타입

//        System.out.println(result.getStatusCode());
//        System.out.println(result.getBody());

        return result_list;
    }
}
