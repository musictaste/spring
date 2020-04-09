package com.limiao.spring;

/**
 * @ClassName CarFactory
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/4/8
 * @Version V1.0
 **/
public class CarFactory {
//    动态工厂
    /*public Car getCar(String name) throws Exception {
        if(name.equals("audi")){
            return new Audi();
        }else {
            throw  new Exception("暂时无法生产");
        }
    }*/

    public static Car getCar(String name) throws Exception {
        if(name.equals("audi")){
            return new Audi();
        }else {
            throw  new Exception("暂时无法生产");
        }
    }
}
