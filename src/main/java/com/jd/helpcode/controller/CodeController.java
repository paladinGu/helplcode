package com.jd.helpcode.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/code")
public class CodeController {

    @GetMapping("getCode")
    public void getCode(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){

    }

    @GetMapping("putCode")
    public void putCode(){

    }
}
