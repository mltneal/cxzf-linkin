package com.unionpay.cxzflinkin.mybatisMapper;

import com.unionpay.cxzflinkin.bean.KeyInfoBean;
import com.unionpay.cxzflinkin.mybatisProvider.TestProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface  TestMapper {

    @SelectProvider(type = TestProvider.class, method = "findByApp")
    @Results(
            id = "keyinfoMap" ,value={
            @Result(column="id", property="id", javaType = Long.class),
            @Result(column="app_id", property="appId", javaType = String.class),
            @Result(column="pub_key", property="pubKey", javaType = String.class),
            @Result(column="pri_key", property="priKey", javaType = String.class)
    }
    )
    KeyInfoBean findByApp(String appId);
}
