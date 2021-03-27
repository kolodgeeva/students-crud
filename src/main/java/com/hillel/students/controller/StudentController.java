package com.hillel.students.controller;

import com.hillel.students.domain.Student;
import com.hillel.students.domain.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

  @PostMapping("/add-student")
  public String addStudent(@Validated Student student, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      return "add-student";
    }

    studentRepository.save(student);
    return "redirect:/students";

  }

  @GetMapping("/students")
  public String getStudents(Model model) {
    model.addAttribute("students", studentRepository.findAll());
    return "students";
  }

  @GetMapping("/students/delete/{id}")
  public String deleteStudent(@PathVariable("id") long id, Model model) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));

    studentRepository.delete(student);

    return "redirect:/students";
  }

  @GetMapping("/students/edit/{id}")
  public String editStudent(@PathVariable("id") long id, Model model) {

    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));

    model.addAttribute("student", student);
    return "edit-student";

  }

  @PostMapping("/students/update/{id}")
  public String updateStudent(@PathVariable("id") long id,
      Student student,
      BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      student.setId(id);
      return "edit-student";
    }

    studentRepository.save(student);

    return "redirect:/students";

  }

}
