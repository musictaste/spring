package com.limiao.spring.service;

import com.limiao.spring.dao.MainDao;
import com.limiao.spring.entity.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MainService
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Service("mainService")
public class MainService {
    @Autowired
    private MainDao mainSqlServerDaoImpl;

    public User login(String username,String password){
        System.out.println("username:"+username+",password:"+password);
        User user = mainSqlServerDaoImpl.getUser();
        System.out.println(ToStringBuilder.reflectionToString(user, ToStringStyle.JSON_STYLE));
        return user;
    }
}
