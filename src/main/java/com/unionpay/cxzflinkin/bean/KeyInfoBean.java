package com.unionpay.cxzflinkin.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;

/**
 * @author: 13563
 * @time: 2020-06-17 16:55
 * @table: key_info
 * @description: 
 */
@Data
public class KeyInfoBean implements Serializable {

    /**
     * 
     * max-len: 20
     * not-null: yes 
     * default: ''
     */
    private Long id;

    /**
     * appid
     * max-len: 16
     * not-null: no 
     * default: ''
     */
    private String appId;

    /**
     * 平台私钥
     * max-len: 2048
     * not-null: no 
     * default: ''
     */
    private String priKey;

    /**
     * app公钥
     * max-len: 2048
     * not-null: no 
     * default: ''
     */
    private String pubKey;

    /**
     * 
     * max-len: 0
     * not-null: yes 
     * default: 'CURRENT_TIMESTAMP'
     */
    private Timestamp createTime;

    /**
     * 
     * max-len: 0
     * not-null: yes 
     * default: 'CURRENT_TIMESTAMP'
     */
    private Timestamp updateTime;


}
