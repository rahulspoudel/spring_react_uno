package com.rp.springy.student;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/students")
public class StudentController {
	
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@GetMapping
	public List<Student> getAllStudents(){
		throw new IllegalStateException("Cannot return all students");
//		return studentService.getAllStudents();
	}
	
	@PostMapping
	public void addNewStudents(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	
}
