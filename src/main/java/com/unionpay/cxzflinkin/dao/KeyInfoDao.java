package com.unionpay.cxzflinkin.dao;

import com.unionpay.cxzflinkin.bean.KeyInfoBean;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
* @author: 13563
* @time: 2020-06-17 16:55
* @table: key_info
* @description: 
*/

@Mapper
@Component
public interface KeyInfoDao {

    /**
     * @param  appId  appid
     * @return List<KeyInfoBean>
     */
    List<KeyInfoBean> getByAppId(@Param("appId") String appId);

    /**
     * @param  entity  table entity
     * @return List<KeyInfoBean>
     */
    List<KeyInfoBean> getByCondition(KeyInfoBean entity);

    /**
     * @param  id  
     * @return KeyInfoBean
     */
    KeyInfoBean getById(@Param("id") Long id);

    /**
     * @param  entity  table entity
     * @return int
     */
    int insert(KeyInfoBean entity);

    /**
     * @param entity  table entity
     * @return int
     */
    int updateById(KeyInfoBean entity);

}