/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-5 16:53:49
 */
@Data
@Entity
public class PeichangUser implements Serializable{
    @Id
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String bumen;
    @Column
    private Integer quanxian;
    
}
