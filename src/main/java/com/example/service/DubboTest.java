package com.example.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.service.IDemoService;
import org.springframework.stereotype.Service;

@Service
public class DubboTest {
    @DubboReference
    private IDemoService demoService;

    public void test(){
        demoService.hello();
    }
}
