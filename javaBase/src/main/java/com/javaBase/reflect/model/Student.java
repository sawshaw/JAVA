package com.javaBase.reflect.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_Student")
public class Student {
	private int age;
	private String name;
	private int id;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="student_age")
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Column(name="student_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
