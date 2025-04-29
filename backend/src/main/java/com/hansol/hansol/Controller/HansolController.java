package com.hansol.hansol.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
