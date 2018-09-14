/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.controller;

import com.szh.kefupeichang.domain.PeichangMail;
import com.szh.kefupeichang.domain.PeichangMaillog;
import com.szh.kefupeichang.domain.PeichangUser;
import com.szh.kefupeichang.service.PeichangMailService;
import com.szh.kefupeichang.service.PeichangMaillogService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-10 10:21:54
 */
//BindingResult bindingResult
@Controller
@RequestMapping(value = "/peichang")
public class PeichangController {

    private final String addhtml = "peichang-add";
    private final String shenhelisthtml = "peichang-shenhelist";
    private final String shenhehtml = "peichang-shenhe";
    private final String fankuilisthtml="peichang-fankuilist";
    private final String fankuihtml="peichang-fankui";
    private final String tuihuihtml="peichang-tuihui";
    private final String tuihuihtmllist="peichang-tuihuilist";
    private final String chaxunhtmllist="peichang-chaxunlist";
    private final String chaxunhtml="peichang-chaxun";
    
    
    @Value("${com.szh.kefupeichang.imagepath}")
    private String imgpath;
    
    @Autowired
    PeichangMailService peichangMailService;
    @Autowired
    PeichangMaillogService peichangMaillogService;
    
    
    @GetMapping(value = "/chaxun")
    public ModelAndView chaxun(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(chaxunhtmllist);
        List<PeichangMail> list = new ArrayList();
        if(user.getQuanxian() <= 10){
            list = peichangMailService.findByBumen(user.getBumen());
        }else{
            list = peichangMailService.findAll();
        }
        
        if(!list.isEmpty() ){
            modelAndView.addObject("peichangmaillist", list);
        }
        return modelAndView;
    }  
    
    @GetMapping(value = "/chaxun/{mailno}")
    public ModelAndView chaxun(ModelAndView modelAndView,
            @PathVariable("mailno")  String mailno){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(chaxunhtml);
        PeichangMail peichangMail = peichangMailService.findByMailno(mailno);
        List<PeichangMaillog> peichangMailloglist = peichangMaillogService.findByMailno(mailno);
        
        modelAndView.addObject("peichangmailloglist",peichangMailloglist);
        if(peichangMail == null){
            modelAndView.addObject("msg", "邮件不存在");
        }
        modelAndView.addObject("peichangmailloglist",peichangMailloglist);
        modelAndView.addObject("peichangmail",peichangMail);
        return modelAndView;
    }
    
    @GetMapping(value = "/tuihui")
    public ModelAndView tuihui(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(tuihuihtmllist);
        List<PeichangMail> list = peichangMailService.findByZhuangtaiAndBumen(user.getQuanxian(),user.getBumen());
        if(!list.isEmpty() ){
            modelAndView.addObject("peichangmaillist", list);
        }
        return modelAndView;
    }   
    
    
    @GetMapping(value = "/tuihui/{mailno}")
    public ModelAndView tuihui(ModelAndView modelAndView,
            @PathVariable("mailno")  String mailno){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(tuihuihtml);
        PeichangMail peichangMail = peichangMailService.findByMailno(mailno);
        List<PeichangMaillog> peichangMailloglist = peichangMaillogService.findByMailno(mailno);
        
        modelAndView.addObject("peichangmailloglist",peichangMailloglist);
        if(peichangMail == null){
            modelAndView.addObject("msg", "邮件不存在");
        }
        modelAndView.addObject("peichangmailloglist",peichangMailloglist);
        modelAndView.addObject("peichangmail",peichangMail);
        return modelAndView;
    }    
    
    @PostMapping(value = "/update")
    public ModelAndView update(ModelAndView modelAndView,@Valid PeichangMail peichangMail,BindingResult bindingResult,
            @RequestParam(value="zhaopian1",required = false)MultipartFile zhaopian1,
            @RequestParam(value="zhaopian2",required = false)MultipartFile zhaopian2,
            @RequestParam(value="zhaopian3",required = false)MultipartFile zhaopian3,
            @RequestParam(value="zhaopian4",required = false)MultipartFile zhaopian4,
            @RequestParam(value="zhaopian5",required = false)MultipartFile zhaopian5
    ){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(tuihuihtml);
        if (bindingResult.hasErrors()) {
            List<ObjectError>  list = bindingResult.getAllErrors();
            
            modelAndView.addObject("msg",list.get(0).getDefaultMessage());
            modelAndView.addObject("peichangmail",peichangMail);
            return modelAndView;
        }
        
        MultipartFile [] zhaopians = new MultipartFile[]{zhaopian1,zhaopian2,zhaopian3,zhaopian4,zhaopian5};
        int i = 0;
        for(MultipartFile zhaopian:zhaopians){
            if(!zhaopian.isEmpty()){
                try{
                    String filepath = imgpath+peichangMail.getMailno()+"/";
                    new File(filepath).mkdirs();
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filepath+i+".jpg")));
                    out.write(zhaopians[i].getBytes());
                    out.flush();
                    out.close();
                    i++;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    modelAndView.addObject("msg","文件上传错误");
                    modelAndView.addObject("peichangmail",peichangMail);
                    return modelAndView;
                }
            }
        }
        peichangMail.setBumen(user.getBumen());
        peichangMail.setName(user.getName());
        peichangMail.setZhuangtai(20);
        peichangMail.setIsdel(false);
        peichangMail.setShijian(new Date());
        System.out.println("peichangmail--->0"+peichangMail);
        try{
            PeichangMaillog maillog = new PeichangMaillog();
            maillog.setMailno(peichangMail.getMailno());
            maillog.setName(user.getName());
            maillog.setZhuangtai(true);
            maillog.setYijian("揽投部提交");
            maillog.setShijian(new Date());
            maillog.setBumen(user.getBumen());
            peichangMailService.update(peichangMail,maillog);
            modelAndView.addObject("msg","保存成功");
            return modelAndView;
        }catch(Exception e){
            modelAndView.addObject("peichangmail",peichangMail);
            modelAndView.addObject("msg",e.getMessage());
            return modelAndView;
        }
        //return modelAndView;
    } 
    
    
    /**
     * 
     * @param modelAndView
     * @return 
     */
    @GetMapping(value = "/add")
    public ModelAndView add(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(addhtml);
        return modelAndView;
    }   
    /**
     * 
     * @param modelAndView
     * @param peichangMail
     * @param bindingResult
     * @param zhaopian1
     * @param zhaopian2
     * @param zhaopian3
     * @param zhaopian4
     * @param zhaopian5
     * @return 
     */
    @PostMapping(value = "/add")
    public ModelAndView add(ModelAndView modelAndView,@Valid PeichangMail peichangMail,BindingResult bindingResult,
            @RequestParam(value="zhaopian1",required = false)MultipartFile zhaopian1,
            @RequestParam(value="zhaopian2",required = false)MultipartFile zhaopian2,
            @RequestParam(value="zhaopian3",required = false)MultipartFile zhaopian3,
            @RequestParam(value="zhaopian4",required = false)MultipartFile zhaopian4,
            @RequestParam(value="zhaopian5",required = false)MultipartFile zhaopian5
    ){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(addhtml);
        if (bindingResult.hasErrors()) {
            List<ObjectError>  list = bindingResult.getAllErrors();
            
            modelAndView.addObject("msg",list.get(0).getDefaultMessage());
            modelAndView.addObject("peichangmail",peichangMail);
            return modelAndView;
        }
        
        MultipartFile [] zhaopians = new MultipartFile[]{zhaopian1,zhaopian2,zhaopian3,zhaopian4,zhaopian5};
        int i = 0;
        for(MultipartFile zhaopian:zhaopians){
            if(!zhaopian.isEmpty()){
                try{
                    String filepath = imgpath+peichangMail.getMailno()+"/";
                    new File(filepath).mkdirs();
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filepath+i+".jpg")));
                    out.write(zhaopians[i].getBytes());
                    out.flush();
                    out.close();
                    i++;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    modelAndView.addObject("msg","文件上传错误");
                    modelAndView.addObject("peichangmail",peichangMail);
                    return modelAndView;
                }
            }
        }
        peichangMail.setBumen(user.getBumen());
        peichangMail.setName(user.getName());
        peichangMail.setZhuangtai(20);
        peichangMail.setIsdel(false);
        peichangMail.setShijian(new Date());
        System.out.println("peichangmail--->0"+peichangMail);
        try{
            PeichangMaillog maillog = new PeichangMaillog();
            maillog.setMailno(peichangMail.getMailno());
            maillog.setName(user.getName());
            maillog.setZhuangtai(true);
            maillog.setYijian("揽投部提交");
            maillog.setShijian(new Date());
            maillog.setBumen(user.getBumen());
            peichangMailService.add(peichangMail,maillog);
            modelAndView.addObject("msg","保存成功");
            return modelAndView;
        }catch(Exception e){
            modelAndView.addObject("peichangmail",peichangMail);
            modelAndView.addObject("msg",e.getMessage());
            return modelAndView;
        }
        //return modelAndView;
    } 
    
    /**
     * 
     * @param modelAndView
     * @return 
     */
    @GetMapping(value = "/shenhe")
    public ModelAndView shenhelist(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(shenhelisthtml);
        List<PeichangMail> list = peichangMailService.findByZhuangtai(user.getQuanxian());
        if(!list.isEmpty() ){
            modelAndView.addObject("peichangmaillist", list);
        }
        return modelAndView;
    }   

    /**
     * 
     * @param modelAndView
     * @param bindingResult
     * @param mailno
     * @return 
     */
    @GetMapping(value = "/shenhe/{mailno}")
    public ModelAndView shenhe(ModelAndView modelAndView,
            @PathVariable("mailno")  String mailno){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(shenhehtml);
        PeichangMail peichangMail = peichangMailService.findByMailno(mailno);
        List<PeichangMaillog> peichangMailloglist = peichangMaillogService.findByMailno(mailno);
        if(peichangMail == null){
            modelAndView.addObject("msg", "邮件不存在");
        }
        modelAndView.addObject("peichangmailloglist",peichangMailloglist);
        modelAndView.addObject("peichangmail",peichangMail);
        return modelAndView;
    }    
    
    /**
     * 
     * @param modelAndView
     * @param mailno
     * @param yijian
     * @param yunxuxiaohui
     * @return 
     */
    @PostMapping(value = "/shenhe/accept")
    public ModelAndView accept(ModelAndView modelAndView,
            @RequestParam("mailno") String mailno,
            @RequestParam("yijian") String yijian,
            @RequestParam(value = "yunxuxiaohui",required = false) Boolean yunxuxiaohui
            ){
        
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(shenhelisthtml);
        PeichangMail peichangMail = peichangMailService.findByMailno(mailno);
        System.out.println(yunxuxiaohui);
        if(null == peichangMail){
            modelAndView.addObject("msg", "邮件不存在");   
            shenhe(modelAndView,mailno);
        }else{
            peichangMail.setYunxuxiaohui(yunxuxiaohui);
            if(!Objects.equals(peichangMail.getZhuangtai(), user.getQuanxian())){
                modelAndView.addObject("msg", "权限不足");
                shenhe(modelAndView,mailno);
            }else{
                PeichangMaillog maillog = new PeichangMaillog();
                maillog.setMailno(peichangMail.getMailno());
                maillog.setName(user.getName());
                maillog.setZhuangtai(true);
                maillog.setYijian(yijian+(yunxuxiaohui==null?"":"(允许销毁："+(yunxuxiaohui?"是":"否")+")"));
                maillog.setShijian(new Date());
                maillog.setBumen(user.getBumen());
                peichangMailService.accept(peichangMail,maillog);
                shenhelist(modelAndView);
            }
        }
        
        return modelAndView;
    } 
    
    /**
     * 
     * @param modelAndView
     * @param mailno
     * @param yijian
     * @return 
     */
    @PostMapping(value = "/shenhe/reject")
    public ModelAndView reject(ModelAndView modelAndView,
            @RequestParam("mailno") String mailno,
            @RequestParam("yijian") String yijian
            ){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(shenhelisthtml);
        PeichangMail peichangMail = peichangMailService.findByMailno(mailno);
        
        if(null == peichangMail){
            modelAndView.addObject("msg", "邮件不存在");   
            shenhe(modelAndView,mailno);
        }else{
            if(!Objects.equals(peichangMail.getZhuangtai(), user.getQuanxian())){
                modelAndView.addObject("msg", "权限不足");
                shenhe(modelAndView,mailno);
            }else{
                PeichangMaillog maillog = new PeichangMaillog();
                maillog.setMailno(peichangMail.getMailno());
                maillog.setName(user.getName());
                maillog.setZhuangtai(false);
                maillog.setYijian(yijian);
                maillog.setShijian(new Date());
                maillog.setBumen(user.getBumen());
                peichangMailService.reject(peichangMail,maillog);
                shenhelist(modelAndView);
            }
        }
        
        return modelAndView;
    } 
    
    @GetMapping(value = "/fankui")
    public ModelAndView fankuilist(ModelAndView modelAndView){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(fankuilisthtml);
        int quanxian = user.getQuanxian();
        if(user.getQuanxian() == 30){
            quanxian =40;
        }
        List<PeichangMail> list = peichangMailService.findByZhuangtai(quanxian);
        if(!list.isEmpty() ){
            modelAndView.addObject("peichangmaillist", list);
        }
        return modelAndView;
    }   

    
    @GetMapping(value = "/fankui/{mailno}")
    public ModelAndView fankui(ModelAndView modelAndView,
            @PathVariable("mailno")  String mailno){
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(fankuihtml);
        PeichangMail peichangMail = peichangMailService.findByMailno(mailno);
        List<PeichangMaillog> peichangMailloglist = peichangMaillogService.findByMailno(mailno);
        if(peichangMail == null){
            modelAndView.addObject("msg", "邮件不存在");
        }
        modelAndView.addObject("peichangmailloglist",peichangMailloglist);
        modelAndView.addObject("peichangmail",peichangMail);
        return modelAndView;
    }    
    
    
    @PostMapping(value = "/fankui")
    public ModelAndView fankui(ModelAndView modelAndView,
            @RequestParam("mailno") String mailno,
            @RequestParam(value = "shijipeichang",required = false) Double shijipeichang,
            @RequestParam(value = "isshouhui",required = false) Boolean isshouhui
            ){
        
        Subject subject = SecurityUtils.getSubject();
        PeichangUser user = (PeichangUser)subject.getPrincipal();
        modelAndView.addObject("user",user);
        modelAndView.setViewName(fankuilisthtml);
        PeichangMail peichangMail = peichangMailService.findByMailno(mailno);
        //System.out.println(yunxuxiaohui);
        if(null == peichangMail){
            modelAndView.addObject("msg", "邮件不存在");   
            fankui(modelAndView,mailno);
        }else{
            peichangMail.setIsshouhui(isshouhui);
            peichangMail.setShijipeichang(shijipeichang);
            if(user.getQuanxian() < 30){
                modelAndView.addObject("msg", "权限不足");
                fankui(modelAndView,mailno);
            }else{
                PeichangMaillog maillog = new PeichangMaillog();
                maillog.setMailno(peichangMail.getMailno());
                maillog.setName(user.getName());
                maillog.setZhuangtai(true);
                maillog.setYijian("实际赔偿："+shijipeichang+",实物是否已收回："+(isshouhui==null?"":(isshouhui?"是":"否"))+"");
                maillog.setShijian(new Date());
                maillog.setBumen(user.getBumen());
                peichangMailService.accept(peichangMail,maillog);
                shenhelist(modelAndView);
            }
        }
        
        return modelAndView;
    } 
}
