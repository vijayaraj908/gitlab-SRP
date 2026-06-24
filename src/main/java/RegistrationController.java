package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    	List<Student> students = studentRepository.findAll(); // Assuming you have a JpaRepository
    	model.addAttribute("students", students);
    	return "admin-dashboard";
    }
    
    @GetMapping("/admin/students/filter")
    @ResponseBody
    public List<Student> filterStudents(@RequestParam(value = "courses", required = false) List<String> 	courses) {
        if (courses == null || courses.isEmpty()) {
            return studentRepository.findAll();
        }
        return studentRepository.findByCourseIn(courses);
    }
    
    @GetMapping("/api/students/{id}")
    @ResponseBody
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
