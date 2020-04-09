package com.limiao.spring.controller;

import com.limiao.spring.entity.User;
import com.limiao.spring.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @ClassName MainController
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    public String login(){
        String username = "limiao";
        String password = "123456";
        User user = mainService.login(username,password);
        if(null !=user){
            return "登录成功";
        }
        return "登录失败";
    }
}
