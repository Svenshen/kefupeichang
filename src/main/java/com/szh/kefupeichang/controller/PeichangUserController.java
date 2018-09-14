/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.controller;

import com.szh.kefupeichang.domain.PeichangUser;
import com.szh.kefupeichang.service.PeichangUserService;
import com.szh.kefupeichang.service.impl.PeichangUserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-10 0:25:32
 */
@Controller
@RequestMapping(value = "/user")
public class PeichangUserController {

    
    private final String changepasswordhtml = "user-changepassword";
    
    @Autowired
    PeichangUserService peichangUserService;
    @PostMapping(value = "/changepassword")
    public ModelAndView changePassowrd(ModelAndView modelAndView,@RequestParam("password1") String password1,@RequestParam("password2") String password2){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("name",user.getName());
        System.out.println("changepassword:"+password1+","+password2);
        if(password1 == null || password1.isEmpty() || password2 == null || password2.isEmpty()){
            modelAndView.addObject("msg", "密码不符合规范，请更换密码");
        }else if(password1.equals(password2)){
            user.setPassword(password1);
            if(peichangUserService.updatePassword(user)){
                modelAndView.addObject("msg", "密码修改成功");
            }else{
                modelAndView.addObject("msg", "系统错误，请重试");
            }
            
        }else{
            modelAndView.addObject("msg", "两次密码不一致，请重新输入");
        }
        
        modelAndView.setViewName(changepasswordhtml);
        return modelAndView;
    }
    
    @GetMapping(value = "/changepassword")
    public ModelAndView changePassowrd(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("name",user.getName());
        modelAndView.setViewName(changepasswordhtml);
        return modelAndView;
    }    
    
}
