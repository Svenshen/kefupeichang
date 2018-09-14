/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.dao;

import com.szh.kefupeichang.domain.PeichangMail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-10 15:29:23
 */
@Repository
public interface PeichangMailDao  extends  JpaRepository<PeichangMail,String>{

    public PeichangMail findByMailnoAndIsdel(String mailno,Boolean isdel);
    
    public List<PeichangMail> findByZhuangtaiAndIsdelOrderByShijianDesc(Integer username,Boolean isdel);
    
    public List<PeichangMail> findByZhuangtaiAndBumenAndIsdelOrderByShijianDesc(Integer zhuangtai,String Bumen,Boolean isdel);
    
    public List<PeichangMail> findByBumenAndIsdelOrderByShijianDesc(String Bumen,Boolean isdel);
    
    
}
