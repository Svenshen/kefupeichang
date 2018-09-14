/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.service;

import com.szh.kefupeichang.dao.PeichangMailDao;
import com.szh.kefupeichang.domain.PeichangMail;
import com.szh.kefupeichang.domain.PeichangMaillog;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-10 15:23:06
 */

public interface PeichangMailService {
    
    
    
    public void accept(PeichangMail peichangMail,PeichangMaillog peichangMaillog) ;
    
    public void reject(PeichangMail peichangMail,PeichangMaillog peichangMaillog) ;
    
    public void add(PeichangMail peichangMail,PeichangMaillog peichangMaillog) throws Exception;
    
    public void update(PeichangMail peichangMail,PeichangMaillog peichangMaillog) throws Exception;
    
    public List<PeichangMail> findByZhuangtai(Integer zhuangtai);
    
    public List<PeichangMail> findByZhuangtaiAndBumen(Integer zhuangtai,String bumen);
    
    public List<PeichangMail> findByBumen(String bumen);
    
    public List<PeichangMail> findAll();
    
    public PeichangMail findByMailno(String mailno);
    
    public Boolean existsByMailno(String mailno);
}
