package com.unionpay.cxzflinkin.service;


import com.unionpay.cxzflinkin.bean.KeyInfoBean;
import com.unionpay.cxzflinkin.dao.DynamicDao;
import com.unionpay.cxzflinkin.dao.KeyInfoDao;
import com.unionpay.cxzflinkin.mybatisMapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyInfoService {

    @Autowired
    private KeyInfoDao keyInfoDao;

    @Autowired
    private DynamicDao dynamicDao;

    @Autowired
    private TestMapper testMapper;

    public int insertKey(KeyInfoBean keyInfoBean) {
        return keyInfoDao.insert(keyInfoBean);
    }

    public KeyInfoBean findByAppId(String appId){
        return dynamicDao.findByAppId(appId);
    }

    public KeyInfoBean findByApp(String appId){
        return testMapper.findByApp(appId);
    }



}
