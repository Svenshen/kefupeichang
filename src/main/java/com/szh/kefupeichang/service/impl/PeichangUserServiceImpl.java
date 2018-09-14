/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.service.impl;

import com.szh.kefupeichang.dao.PeichangUserDao;
import com.szh.kefupeichang.domain.PeichangUser;
import com.szh.kefupeichang.service.PeichangUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-7 10:59:08
 */
@Service
public class PeichangUserServiceImpl implements  PeichangUserService{

    @Autowired
    PeichangUserDao peichangUserDao;
    
    /**
     * 
     * @param username
     * @return 
     */
    @Override
    public PeichangUser findByUsername(String username) {
        return peichangUserDao.findByUsername(username);
    }

    
    /**
     * 
     * @param user
     * @return 
     */
    @Override
    public Boolean updatePassword(PeichangUser user) {
        
        try{
            peichangUserDao.save(user);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    
}
