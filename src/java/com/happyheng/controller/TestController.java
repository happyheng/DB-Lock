package com.happyheng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by happyheng on 2019/3/21.
 */
@RequestMapping("/testcontroller")
@RestController
public class TestController {


    @RequestMapping("/test")
    public String priintTest() {
        System.out.println("test");

        return "test";
    }


}
