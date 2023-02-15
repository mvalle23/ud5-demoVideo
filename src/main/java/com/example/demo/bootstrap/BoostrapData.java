package com.example.demo.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Student;
import com.example.demo.domain.University;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UniversityRepository;

import jakarta.transaction.Transactional;

@Component
public class BoostrapData implements CommandLineRunner{

	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UniversityRepository universityRepository;
	
	@Transactional
	@Override
	public void run (String... arg0) throws Exception{
		Student student1 = new Student ("Jill", "Murray","jm@mail.com");
		University university1 = new University ("EHU");
		
		student1.setUniversity(university1);
		university1.getStudents().add(student1);
		
		universityRepository.save(university1);
		studentRepository.save(student1);
	}
}
