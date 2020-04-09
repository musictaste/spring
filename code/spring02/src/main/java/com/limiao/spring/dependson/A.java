package com.limiao.spring.dependson;

/**
 * @ClassName A
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/4/8
 * @Version V1.0
 **/
public class A {

    private C c; //强引用

    public void weakRefence(){
        new B().getName(); //弱引用
    }
}
