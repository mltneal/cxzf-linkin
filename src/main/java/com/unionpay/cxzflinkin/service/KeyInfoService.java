package com.unionpay.cxzflinkin.service;


import com.unionpay.cxzflinkin.bean.KeyInfoBean;
import com.unionpay.cxzflinkin.dao.KeyInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyInfoService {

    @Autowired
    private KeyInfoDao keyInfoDao;

    public int insertKey(KeyInfoBean keyInfoBean) {
        return keyInfoDao.insert(keyInfoBean);
    }
}
