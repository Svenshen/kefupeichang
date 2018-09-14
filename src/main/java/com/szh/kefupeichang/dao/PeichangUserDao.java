/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.dao;

import com.szh.kefupeichang.domain.PeichangUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-6 14:38:33
 */
@Repository
public interface PeichangUserDao extends  JpaRepository<PeichangUser,String>{
    
    public PeichangUser findByUsername(String username);
}
