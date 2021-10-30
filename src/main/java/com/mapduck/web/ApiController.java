package com.mapduck.web;

import com.mapduck.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ApiController {

    private final RestTemplateService templateService;


    @GetMapping("/danawa")
    public String getKerword(@RequestParam String keyword) {
        System.out.println("keyword:" + keyword);
        return keyword;
    }

}
