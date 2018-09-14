/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.dao;

import com.szh.kefupeichang.domain.PeichangMaillog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-12 9:53:43
 */
public interface PeichangMaillogDao extends  JpaRepository<PeichangMaillog,String>{
    
    public List findByMailno(String mailno);
    
}
