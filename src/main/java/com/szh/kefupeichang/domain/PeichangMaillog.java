/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-6 13:45:00
 */
@Data
@Entity
@IdClass(PeichangmaillogID.class)
public class PeichangMaillog implements Serializable{

    @Id
    @Column
    private String mailno;
    @Id
    @Column
    private Date shijian;
    @Column
    private String name;
    @Column
    private String bumen;
    @Column
    private Boolean zhuangtai;
    @Column
    private String yijian;
    
  
    
}
