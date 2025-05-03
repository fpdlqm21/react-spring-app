package com.hansol.hansol.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HansolController {

    @GetMapping("/")
    public String test(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/form")
    public String goForm(){
        return "form";
    }

    @GetMapping("/api/react")
    public String reactAPI(){
        return "Test OK";
    }

//    @GetMapping("/auth/login/kakao")
//    public ResponseEntity<?> kakaoLogin(@RequestParam("code") String accessCode, HttpServletResponse httpServletResponse){
//
//    }
}
