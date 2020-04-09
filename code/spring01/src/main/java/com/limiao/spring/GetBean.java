package com.limiao.spring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBean {


	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Person p1 = (Person) ctx.getBean("person");
//		Person p2 = (Person) ctx.getBean("people2");

//		System.out.println(p1.getAge());
//		System.out.println(p2);

		System.out.println(ToStringBuilder.reflectionToString(p1, ToStringStyle.JSON_STYLE));
	}
}
