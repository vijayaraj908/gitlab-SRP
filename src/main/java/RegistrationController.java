package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "index";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "success";
    }
    
    @GetMapping("/admin/students")
    public String listStudents(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "admin-dashboard";
    }
    
    // Endpoint for "Remove Filter" - returns ALL students
    @GetMapping("/admin/students/all")
    @ResponseBody
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    // Endpoint for Filter functionality
    @GetMapping("/admin/students/filter")
    @ResponseBody
    public List<Student> filterStudents(@RequestParam(value = "courses", required = false) String coursesParam) {
        if (coursesParam == null || coursesParam.isEmpty()) {
            return studentRepository.findAll();
        }
        // Convert comma-separated string to List
        List<String> courses = List.of(coursesParam.split(","));
        return studentRepository.findByCourseIn(courses);
    }
    
    @GetMapping("/api/students/{id}")
    @ResponseBody
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(null);
    }

}
