package com.adarsh.spring.domain;

import java.io.Serializable;

/**
 * A simple POJO representing a Person
 */
public class Person implements Serializable {

	private static final long serialVersionUID = -5527566248002296042L;
	
	private Integer id;
	private String firstName;
	private String lastName;
	private Double money;

    public Person() { }

    public Person(Integer id, String firstName, String lastName, Double money) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.money = money;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
}
