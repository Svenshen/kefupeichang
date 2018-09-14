/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.controller;

import com.szh.kefupeichang.domain.PeichangUser;
import com.szh.kefupeichang.service.PeichangUserService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-6 14:20:03
 */
@Controller
public class LoginController {

    @Autowired
    PeichangUserService peichangUserService;
    
    @GetMapping("/login")
    public ModelAndView index(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        System.out.println("登录状态"+subject.isAuthenticated());
                
         if(subject.isAuthenticated()){
           modelAndView.setViewName("index");
        }else{
            modelAndView.setViewName("login");
         }
        return modelAndView;
    }
    
    @PostMapping(value = "/login")
    public ModelAndView login(PeichangUser peichangUser ,ModelAndView modelAndView,HttpServletRequest request) throws Exception {
        
//        String msg = "空";
//        Subject subject = SecurityUtils.getSubject();
//        if(subject.isAuthenticated()){
//           subject.logout();
//        }
//        UsernamePasswordToken token = new UsernamePasswordToken(peichangUser.getUsername(),peichangUser.getPassword());
//        
//        try{
//            //subject.login(token);
//        }catch(UnknownAccountException uae){
//            msg="账号不存在";
//        }catch(IncorrectCredentialsException ice){
//            msg=" 密码不正确";
//        }
        
//        System.out.println("asdasdasasdasdasdasd");
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println(exception);
        String msg = "空";
        if(exception != null){
            if(UnknownAccountException.class.getName().equals(exception)){
                msg = "账号不存在";
            }else if(IncorrectCredentialsException.class.getName().equals(exception)){
                msg = "密码不正确";
            }else{
                msg = ""+exception;
            }
        }
//        System.out.println(msg);
//        if(bindingResult.hasErrors()){
//            modelAndView.addObject("error",bindingResult.getFieldError().getDefaultMessage());
//            modelAndView.setViewName("login");
//            return modelAndView;
//        }
//        if(peichangUserService.login(peichangUser)){
//             modelAndView.setViewName("index");
//        }else{
//            modelAndView.addObject("error","用户名或密码错误");
//            modelAndView.setViewName("login");
//            return modelAndView;
//        }

        //map.put("msg",msg);
        //modelAndView.setViewName("login");
        modelAndView.addObject("msg",msg);
        return modelAndView;
    }
}
