package com.api;


import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/mbti")
    public String mbti(){
        return "mbti";
    }
}
