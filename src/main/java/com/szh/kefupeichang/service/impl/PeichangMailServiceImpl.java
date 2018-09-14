/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.service.impl;

import com.szh.kefupeichang.dao.PeichangMailDao;
import com.szh.kefupeichang.domain.PeichangMail;
import com.szh.kefupeichang.domain.PeichangMaillog;
import com.szh.kefupeichang.service.PeichangMailService;
import com.szh.kefupeichang.service.PeichangMaillogService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-10 15:25:26
 */
@Service
public class PeichangMailServiceImpl implements PeichangMailService{

    @Autowired
    PeichangMailDao peichangMailDao ;
  
    @Autowired
    PeichangMaillogService peichangMaillogService;
  
    

    /**
     * 
     * @param zhuangtai
     * @return 
     */
    @Override
    public List<PeichangMail> findByZhuangtai(Integer zhuangtai) {
        return peichangMailDao.findByZhuangtaiAndIsdelOrderByShijianDesc(zhuangtai, false);
    }

    /**
     * 
     * @param mailno
     * @return 
     */
    @Override
    public PeichangMail findByMailno(String mailno) {
        return peichangMailDao.findByMailnoAndIsdel(mailno,false);
    }

    /**
     * 
     * @param mailno
     * @return 
     */
    @Override
    public Boolean existsByMailno(String mailno) {
        return peichangMailDao.existsById(mailno);
    }

    /**
     * 
     * @param peichangMail
     * @param peichangMaillog
     * 
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void accept(PeichangMail peichangMail,PeichangMaillog peichangMaillog) {
        peichangMail.setZhuangtai(peichangMail.getZhuangtai()+10);
        peichangMailDao.save(peichangMail);
        peichangMaillogService.add(peichangMaillog);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void reject(PeichangMail peichangMail,PeichangMaillog peichangMaillog) {
        peichangMail.setZhuangtai(10);
        peichangMailDao.save(peichangMail);
        peichangMaillogService.add(peichangMaillog);
    }
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void add(PeichangMail peichangMail, PeichangMaillog peichangMaillog) throws Exception {
        if(existsByMailno(peichangMail.getMailno())){
            throw new Exception("邮件已存在");
        }
        
        peichangMailDao.save(peichangMail);
        peichangMaillogService.add(peichangMaillog);
        
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void update(PeichangMail peichangMail, PeichangMaillog peichangMaillog) throws Exception {
        if(!existsByMailno(peichangMail.getMailno())){
            throw new Exception("邮件不存在");
        }
        peichangMailDao.save(peichangMail);
        peichangMaillogService.add(peichangMaillog);
    }
    
    @Override
    public List<PeichangMail> findByZhuangtaiAndBumen(Integer zhuangtai,String bumen) {
        return peichangMailDao.findByZhuangtaiAndBumenAndIsdelOrderByShijianDesc(zhuangtai,bumen, false);
    }

    @Override
    public List<PeichangMail> findByBumen(String bumen) {
        return peichangMailDao.findByBumenAndIsdelOrderByShijianDesc(bumen, false);
    }

    @Override
    public List<PeichangMail> findAll() {
        Sort sort=new Sort(Direction.DESC,"shijian"); 
        return peichangMailDao.findAll(sort);
    }

    

}
