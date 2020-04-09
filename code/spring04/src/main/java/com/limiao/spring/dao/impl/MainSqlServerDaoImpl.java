package com.limiao.spring.dao.impl;

import com.limiao.spring.dao.MainDao;
import com.limiao.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @ClassName MainSqlServerDaoImpl
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Repository("mainSqlServerDaoImpl")
public class MainSqlServerDaoImpl implements MainDao {

    @Autowired
    private User user;

    public User getUser() {
        System.out.println("MainSqlServerDaoImpl.....");
        return user;
    }
}
