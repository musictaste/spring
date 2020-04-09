package com.limiao.spring;

public class GetBean {
	public static void main(String[] args) {
		final Girl girl = new Girl();
		Girl proxyGirl  = (Girl) new CGlibFactory(girl).createProxy();
		proxyGirl.eat();
		System.out.println("=============");
		proxyGirl.dance();
	}
}
