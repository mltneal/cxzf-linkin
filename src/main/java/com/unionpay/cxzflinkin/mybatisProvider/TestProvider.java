package com.unionpay.cxzflinkin.mybatisProvider;

import org.apache.ibatis.jdbc.SQL;

public class TestProvider {

    public String findByApp(String appId){

        return new SQL() {
            {
                SELECT("*");
                FROM("key_info");
                WHERE("app_id=" + appId);
            }
        }.toString();
    }
}
