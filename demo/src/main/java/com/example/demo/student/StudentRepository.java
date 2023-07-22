package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository 
			extends JpaRepository<Student, Long>{
	
	//Select s from Student where s.email = ?
	Optional<Student> findStudentByEmail(String email);

}
