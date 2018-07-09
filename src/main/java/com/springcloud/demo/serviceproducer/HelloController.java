package com.springcloud.demo.serviceproducer;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${neo.hello}")
    private String config;

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        return config +" -> hello " + name;
    }
}
