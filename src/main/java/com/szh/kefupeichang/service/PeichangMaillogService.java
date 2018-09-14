/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.service;

import com.szh.kefupeichang.domain.PeichangMaillog;
import java.util.List;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-12 10:11:07
 */
public interface PeichangMaillogService {

  
    public void add(PeichangMaillog peichangMaillog);
    
    public List<PeichangMaillog> findByMailno(String mailno);
    
}
