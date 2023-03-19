package com.hibernate.HibernateCodingChallenge.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sid;
	private String name;
	private String technology;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="homeNo")
	private Address address;
	
	@OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.PERSIST,mappedBy = "student")
	private List<Course> course;
	public int getSid() {
		return sid;
	}
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public Student(String name, String technology) {
		super();
		this.name = name;
		this.technology = technology;
	}
	public Student(String name, String technology,Address address) {
		super();
		this.name = name;
		this.technology = technology;
		this.address=address;
	}
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", name=" + name + ", technology=" + technology + "]";
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
