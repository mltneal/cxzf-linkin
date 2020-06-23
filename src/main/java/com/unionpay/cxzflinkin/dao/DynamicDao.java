package com.unionpay.cxzflinkin.dao;

import com.unionpay.cxzflinkin.bean.KeyInfoBean;
import com.unionpay.cxzflinkin.mybatisProvider.TestProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface DynamicDao {

    @Select("SELECT * FROM key_info WHERE app_id = #{appId}")
    @Results(
            id = "keyinfoMap" ,value={
            @Result(column="id", property="id", javaType = Long.class),
            @Result(column="app_id", property="appId", javaType = String.class),
            @Result(column="pub_key", property="pubKey", javaType = String.class),
            @Result(column="pri_key", property="priKey", javaType = String.class)
    }
    )
    KeyInfoBean findByAppId(String appId);




}
