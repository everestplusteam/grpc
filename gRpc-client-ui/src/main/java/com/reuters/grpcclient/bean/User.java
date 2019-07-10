package com.reuters.grpcclient.bean;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

/**
 * User Bean
 * 
 * @author qingqingz
 *
 */
public class User {

	private Integer id;

	@NotEmpty(message = "cannot empty")
	@Length(min = 2, max = 32, message = "length between 2 to 32")
	private String name;

	@Digits(fraction = 0, integer = 200, message = "digit only")
	private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
