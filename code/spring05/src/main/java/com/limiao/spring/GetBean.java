package com.limiao.spring;

public class GetBean {


	public static void main(String[] args) {
		Girl girl = new Girl();
		GirlProxy girlProxy = new GirlProxy(girl);
		girlProxy.eat();
	}
}
