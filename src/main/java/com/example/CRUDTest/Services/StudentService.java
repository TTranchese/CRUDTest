package com.example.CRUDTest.Services;

import com.example.CRUDTest.Entities.Student;
import com.example.CRUDTest.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	
	public Student toggleWorking(Long id) {
		Student student = studentRepository.findById(id).get();
		student.setWorking(!student.isWorking());
		studentRepository.save(student);
		return studentRepository.findById(id).get();
	}
}
