package com.limiao.spring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBean {
	public static void main(String[] args) {
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-service.xml");

//		PluggableSchemaResolver

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		/*Person p = (Person) ctx.getBean("person");
		Food food =  ctx.getBean("food", Food.class);
		
		food.setName("面包");
		
		p.setAge(18);
		p.setName("zhangsan");
		p.setFood(food);*/

		Person p = (Person) ctx.getBean("person");
		System.out.println(ToStringBuilder.reflectionToString(p, ToStringStyle.JSON_STYLE));
		System.out.println(ToStringBuilder.reflectionToString(ctx, ToStringStyle.MULTI_LINE_STYLE));
	}
}
