package com.hillel.students.controller;

import com.hillel.students.domain.Student;
import com.hillel.students.domain.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

  StudentRepository studentRepository;

  public StudentController(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @GetMapping("/add-student")
  public String showStudentForm(Student student) {
    return "add-student";
  }



}
