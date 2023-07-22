package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	private  StudentRepository studentRepository;
	
	@Autowired
	 public StudentService(StudentRepository studentRepository) {
	  this.studentRepository = studentRepository; }
	 
	
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}


	//Business Logic
	//Post a new student only if email id is not already taken
	public void  addStudent(Student student) {
		
		Optional<Student> studentWithSameEmail = studentRepository.findStudentByEmail(student.getEmail());
		
		if(studentWithSameEmail.isPresent()) //value is not null
		{ 			
			throw new IllegalStateException("\n\n******Email ID already taken*******\n\n");
		}
		
		studentRepository.save(student);
	}


	
	public void deleteStudent(Long studentId) {	
		
		boolean studentWithIdExists = studentRepository.existsById(studentId);
		
		if(studentWithIdExists) {
			studentRepository.deleteById(studentId);
		}
		else {
			throw new IllegalStateException("\n\n******No student with given ID*******\n\n");
		}
	}


	//By using below keyword, we do not need to access repository layer to update our student
	//The student object goes in managed state and rest is handled by Spring boot, we just need to call setters
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		
		Student existingStudent = studentRepository.findById(studentId)
																		.orElseThrow(() -> new IllegalStateException("Student with given ID does not exist"));
		
		if(name != null &&     // not null
				name.length() > 0 &&  // length != 0
				!Objects.equals(existingStudent.getName(), name)) { // name not same as already stored
			existingStudent.setName(name);
		}
		
		
		if(email != null &&
				email.length() > 0) {
			Optional<Student> studentWithSameEmail = studentRepository.findStudentByEmail(email);
			
			if(studentWithSameEmail.isPresent()) {
				throw new IllegalStateException("\n\n******Email ID already taken*******\n\n");
			}
			
			existingStudent.setEmail(email);
		}
		
	}
	
}
