package com.limiao.spring.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @ClassName Pet
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/4/9
 * @Version V1.0
 **/
@Component
@Scope(value = "prototype")
public class Pet {
    @Value("猫老二")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
