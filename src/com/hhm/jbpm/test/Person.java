package com.hhm.jbpm.test;

import java.io.Serializable;

/**
 * 这是person类，用于测试jbpm的全局变量的类型的设置。一定要实现Serializable可序列化接口
 * @author 黄帅哥
 *
 */
public class Person implements Serializable{
	private String name;
	private int age;
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
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Person() {
		super();
	}
	
	
}
