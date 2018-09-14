/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.controller;

import com.szh.kefupeichang.domain.PeichangUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-6 14:15:31
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/index","/"})
    public ModelAndView index(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        modelAndView.addObject("user",((PeichangUser)subject.getPrincipal()));
        modelAndView.setViewName("index");
        return modelAndView;
    }
    
}
