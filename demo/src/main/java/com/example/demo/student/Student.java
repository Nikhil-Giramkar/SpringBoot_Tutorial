package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Student {

	public Student() {
	}
	
	public Student(Long id, String name, String email, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
		
	}
	
	//Constructor without id, as we'll fetch from db
	public Student(String name, String email, LocalDate dob) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;

	}


	@Id
	@SequenceGenerator(
			name="student_sequence",
			sequenceName="student_sequence",
			allocationSize=1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator="student_sequence"
			)
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	
	//We wish to calculate age instead of getting it from user
	//Transient is used to exclude a field from Serialization or going to DB
	
	@Transient
	private Integer age;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	//Calculate age from DOB
	public Integer getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age + "]";
	}
	
}
