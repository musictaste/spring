package com.limiao.spring;

/**
 * @ClassName Car
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/4/8
 * @Version V1.0
 **/
public class Car {
    private BMW bmw;

   /* public Car(BMW bmw) {
        this.bmw=bmw;
    }*/

    public BMW getBmw() {
        return bmw;
    }

    public void setBmw(BMW bmw) {
        this.bmw = bmw;
    }
}
