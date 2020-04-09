package com.limiao.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBean {


	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Car car = ctx.getBean("car", Car.class);
		System.out.println(car.getName());
		System.out.println(car.getPrice());
	}
}
