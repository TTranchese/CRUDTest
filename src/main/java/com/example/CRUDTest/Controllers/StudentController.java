package com.example.CRUDTest.Controllers;

import com.example.CRUDTest.Entities.Student;
import com.example.CRUDTest.Repositories.StudentRepository;
import com.example.CRUDTest.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getById(@PathVariable Long id) {
		if (studentRepository.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(studentRepository.findById(id).get(), HttpStatus.FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public Student update(@PathVariable long id, @RequestBody Student student) {
		student.setId(id);
		return studentRepository.save(student);
	}
	
	
	@PatchMapping("/{id}/work")
	public ResponseEntity<Student> toggleIsWorking(@PathVariable Long id) {
		if (studentRepository.findById(id).isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(studentService.toggleWorking(studentRepository.findById(id).get().getId()), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		studentRepository.deleteById(id);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
}
