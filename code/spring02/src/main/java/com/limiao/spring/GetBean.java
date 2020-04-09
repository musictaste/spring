package com.limiao.spring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBean {


	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		/*Person p1 = (Person) ctx.getBean("person");
		Person p2 = (Person) ctx.getBean("person");

		System.out.println(p1==p2);
		System.out.println(p1.getCar()==p2.getCar());
		System.out.println(p1.getCar().getBmw()==p2.getCar().getBmw());*/

		Person p1 = ctx.getBean("person",Person.class);
		Person p2 = ctx.getBean("person",Person.class);

		Car car1 = ctx.getBean("car",Car.class);
		Car car2 = ctx.getBean("car",Car.class);

		BMW bmw1 = ctx.getBean("bmw",BMW.class);
		BMW bmw2 = ctx.getBean("bmw",BMW.class);

		System.out.println(p1==p2);
		System.out.println(car1==car2);
		System.out.println(bmw1==bmw2);

	}
}
