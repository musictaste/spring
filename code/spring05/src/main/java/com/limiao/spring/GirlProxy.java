package com.limiao.spring;

/**
 * @ClassName GirlProxy
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/4/9
 * @Version V1.0
 **/
public class GirlProxy implements Human {
    private Human human;

    public GirlProxy() {
        super();
    }

    public GirlProxy(Human human) {
        super();
        this.human = human;
    }

    public void eat() {
        System.out.println("before....");
        human.eat();
        System.out.println("after....");
    }
}
