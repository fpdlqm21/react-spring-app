package com.hansol.hansol.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReactApiController {
    @GetMapping("/api/data")
    public String hello(){
        return "Hello, this is springboot";
    }
}
