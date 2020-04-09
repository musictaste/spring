package com.limiao.spring.dao.impl;

import com.limiao.spring.dao.MainDao;
import com.limiao.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @ClassName MainOracleDaoImpl
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Repository("mainOracleDaoImpl")
public class MainOracleDaoImpl implements MainDao {

    @Autowired
    private User user;

    public User getUser() {
        System.out.println("MainOracleDaoImpl......");
        return user;
    }
}
