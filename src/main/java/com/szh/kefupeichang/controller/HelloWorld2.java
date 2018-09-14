/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.controller;



import com.szh.kefupeichang.domain.PeichangUser;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-7-19 14:10:08
 */

@Controller
public class HelloWorld2 {

    
    @RequestMapping("/hello")
    public String Hello(Map<String,Object> map){
        map.put("hello","from TemplateController.helloHtml");
        return "/hello" ;
    }
    
    @RequestMapping("/hello2")
    @ResponseBody
    public PeichangUser Hello2(Map<String,Object> map){
        PeichangUser user = new PeichangUser();

        return user;
    }
    
}
