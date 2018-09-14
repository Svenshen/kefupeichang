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
import javax.persistence.Id;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-6 13:37:31
 */
@Data
@Entity
public class PeichangMail implements Serializable{
    @Id
    @Column
    @NotEmpty(message="邮件号不能为空")
    private String mailno;
    @Column
    private String name;
    @Column
    private String bumen;
    @Column
    @NotEmpty(message="寄件人姓名不能为空")
    private String jijianrenxingming;
    @Column
    @NotEmpty(message="寄件人电话不能为空")
    private String jijianrendianhua;
    @Column
    @NotEmpty(message="寄件人地址不能为空")
    private String jijianrendizhi;
    @Column
    @NotEmpty(message="收件人姓名不能为空")
    private String shoujianrenxingming;
    @Column
    @NotEmpty(message="收件人电话不能为空")
    private String shoujianrendianhua;
    @Column
    @NotEmpty(message="收件人地址不能为空")
    private String shoujianrendizhi;
    @Column    
    @NotNull(message="邮件价值不能为空")
    private Double youjianjiazhi;
    @Column
    @NotNull(message="索赔金额不能为空")
    private Double suopeijine;
    
    @Column
    private Boolean yunxuxiaohui;
    @Column
    private Double shijipeichang;
    @Column
    private  Boolean isshouhui;
    @Column
    private  Integer zhuangtai;
    @Column
    private  Date shijian;
    @Column
    private  Boolean isdel;
    
    
}
