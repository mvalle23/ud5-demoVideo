package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public String getStudents(Model model) {
		model.addAttribute("students",studentRepository.findAll());
		return "students/list";
	}
	
	@GetMapping("/students/new")
	public String initCreationForm(Model model) {
		Student student = new Student();
		model.addAttribute("student",student);
		return "students/studentForm";
	}
	
	@PostMapping ("students/new/submit")
	public String submitCreationForm(@ModelAttribute Student student) {
		studentRepository.save(student);
		return "redirect:/students";
	}
	@GetMapping ("students/edit/{id}")
	public String initEditForm(@PathVariable("id")int id, Model model) {
		model.addAttribute("student", studentRepository.findById(id));
		return "students/studentForm";
	}
	
	@GetMapping ("students/delete/{id}")
	public String initDelete(@PathVariable ("id") int id) {
		studentRepository.deleteById(id);
		return "redirect:/students";
		
	}
}
