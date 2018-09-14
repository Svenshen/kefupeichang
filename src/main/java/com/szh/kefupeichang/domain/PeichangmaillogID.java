/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package com.szh.kefupeichang.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-9-6 13:45:32
 */
@Data
public class PeichangmaillogID implements Serializable{

    private String mailno;
    private Date shijian;
    
    
}
