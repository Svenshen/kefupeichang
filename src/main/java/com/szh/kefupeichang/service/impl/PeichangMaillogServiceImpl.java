/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.service.impl;

import com.szh.kefupeichang.dao.PeichangMaillogDao;
import com.szh.kefupeichang.domain.PeichangMaillog;
import com.szh.kefupeichang.service.PeichangMaillogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-12 10:11:47
 */
@Service
public class PeichangMaillogServiceImpl implements PeichangMaillogService{

    @Autowired
    PeichangMaillogDao peichangMaillogDao;
    
    /**
     * 
     * @param peichangMaillog
     *  
     */
    @Override
    public void add(PeichangMaillog peichangMaillog) {
        peichangMaillogDao.save(peichangMaillog);
    }

    @Override
    public List<PeichangMaillog> findByMailno(String mailno) {
        return peichangMaillogDao.findByMailno(mailno);
    }

}
