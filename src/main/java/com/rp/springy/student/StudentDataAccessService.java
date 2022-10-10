package com.rp.springy.student;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//@Component
@Repository
public class StudentDataAccessService {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Student> selectAllStudents(){
		String sql=""+
				"SELECT "+
				" student_id, "+
				" first_name, "+
				" last_name, "+
				" email, "+
				" gender "+
				"FROM student";
		
		return jdbcTemplate.query(sql, mapStudentFromDb());
	}

	public int insertStudent(UUID studentId, Student student) {
		String sql = ""+
					"INSERT INTO student (student_id, first_name, last_name, email, gender) "+
					"VALUES (?, ?, ?, ?, ?)"+
					"";
		// This "update" variable returns 1 if success and 0 if fails
		int update = jdbcTemplate.update(
				sql,
				studentId,
				student.getFirstName(),
				student.getLastName(),
				student.getEmail(),
				student.getGender().name().toUpperCase()
				);
		return update;
	}
	
	private RowMapper<Student> mapStudentFromDb() {
		return (resultSet,i)->{
			String studentIdStr = resultSet.getString("student_id");
			UUID studentId = UUID.fromString(studentIdStr);
			
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String email = resultSet.getString("email");
			
			String genderStr = resultSet.getString("gender").toUpperCase();
			Student.Gender gender = Student.Gender.valueOf(genderStr);
			return new Student(
					studentId,
					firstName,
					lastName,
					email,
					gender
					);
			
		};
	}

	
}
