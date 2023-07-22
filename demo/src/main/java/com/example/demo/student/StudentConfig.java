package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
					Student nikhil = new Student(
							1L, 
							"Nikhil", 
							"nikhil@gmail.com", 
							LocalDate.of(2000, 12, 29)

							);
					
					Student ansh = new Student(
							"Ansh", 
							"ansh@gmail.com", 
							LocalDate.of(2010, 07, 14)

							);
					
					//Default values stored
					studentRepository.saveAll(
							List.of(nikhil, ansh)
							);
		};
	}
}
