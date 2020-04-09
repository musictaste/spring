package com.limiao.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class GetBean {
	public static void main(String[] args) {
		final Girl girl = new Girl();
		Human prxyGirl = (Human) Proxy.newProxyInstance(Girl.class.getClassLoader(), Girl.class.getInterfaces(), new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println(method.getName()+"...方法被执行");

				if(method.getName().equals("eat")){
					System.out.println("eat before...");
					Object invoke = method.invoke(girl,args);
					System.out.println("eat after....");
					return  invoke;
				}

				System.out.println("dance before...");
				Object invoke = method.invoke(girl,args);
				System.out.println("dance after....");
				return  invoke;
			}
		});

		prxyGirl.eat();
		System.out.println("=================");
		prxyGirl.dance();
	}
}
