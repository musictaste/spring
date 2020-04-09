package com.limiao.spring;

import com.limiao.spring.controller.MainController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBean {


	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		MainController controller = ctx.getBean("mainController", MainController.class);
		String result = controller.login();
		System.out.println(result);
	}
}
