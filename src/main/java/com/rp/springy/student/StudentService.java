package com.rp.springy.student;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {
	private final StudentDataAccessService studentDataAccessService;
	
	@Autowired
	public StudentService(StudentDataAccessService studentDataAccessService) {
		super();
		this.studentDataAccessService = studentDataAccessService;
	}



	public List<Student> getAllStudents() {
		return studentDataAccessService.selectAllStudents();
	}
}
