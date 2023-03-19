package com.hibernate.HibernateCodingChallenge.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int homeNo;
	private String city;
	private String country;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
	private Student student;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getHomeNo() {
		return homeNo;
	}

	public void setHomeNo(int homeNo) {
		this.homeNo = homeNo;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Address(String city, String country) {
		super();
		this.city = city;
		this.country = country;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Address [homeNo=" + homeNo + ", city=" + city + ", country=" + country + "]";
	}

}
