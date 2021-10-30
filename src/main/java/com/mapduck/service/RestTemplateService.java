package com.mapduck.service;


import com.mapduck.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Service
public class RestTemplateService {

    // Server로 요청을 보내는 서비스이다.

    // get방식
    public Product keyword(String keyword) {

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

        ResponseEntity<Product> result = restTemplate.getForEntity(uri,Product.class);

        //String result = restTemplate.getForObject(uri, String.class);
        //getForEntity는 응답을 ResponseEntity로 받을 수 있도록 해준다 .
        //파라미터 첫번째는 요청 URI 이며 , 2번째는 받을 타입
//        System.out.println(result.getStatusCode());
//        System.out.println(result.getBody());

        return result.getBody();
    }
}
