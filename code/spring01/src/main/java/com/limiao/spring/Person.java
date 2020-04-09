package com.limiao.spring;


import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Person {

	static {
		System.out.println("Person init--");
	}

	private String name;
	private int age;
	private Food food;
	private Properties others;
	private List<String> list;
	private Map<String,String> map;
	private String testNull;
	private String testEmpty;

	public String getTestEmpty() {
		return testEmpty;
	}

	public void setTestEmpty(String testEmpty) {
		this.testEmpty = testEmpty;
	}

	public String getTestNull() {
		return testNull;
	}

	public void setTestNull(String testNull) {
		this.testNull = testNull;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Properties getOthers() {
		return others;
	}

	public void setOthers(Properties others) {
		this.others = others;
	}

	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
