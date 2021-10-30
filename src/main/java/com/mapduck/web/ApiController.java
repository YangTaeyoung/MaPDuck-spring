package com.mapduck.web;

import com.mapduck.domain.Product;
import com.mapduck.repository.ProductRepository;
import com.mapduck.service.ProductServiceImpl;
import com.mapduck.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ApiController {

    private final RestTemplateService templateService;
    private final ProductServiceImpl productService;



    @GetMapping("/danawa")
    public List<Product> getKeyword(@RequestParam String keyword) {

        System.out.println("keyword:" + keyword);

        if (!productService.findByKeyword(keyword).isEmpty()) {
            return productService.findByKeyword(keyword);
        }

        return templateService.keyword(keyword);
    }

}
