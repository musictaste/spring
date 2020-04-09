package com.limiao.spring;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName CGlibProxy
 * @Description: TODO
 * @Author 李淼
 * @Date 2020/4/9
 * @Version V1.0
 **/
public class CGlibFactory implements MethodInterceptor {
    private Object target;

    public CGlibFactory(Object target) {
        super();
        this.target = target;
    }

    public CGlibFactory() {
        super();
    }

    public Object createProxy(){
        //增强器
        Enhancer enhancer = new Enhancer();
        //创建子类，作为代理类
        enhancer.setSuperclass(Girl.class);
        //设置回调类
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before....");
        method.invoke(target,objects);
        System.out.println("after....");
        return null;
    }
}
