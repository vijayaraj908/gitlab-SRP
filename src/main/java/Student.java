package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Updated to match your index.html fields
    private String fullName;
    private String post;
    private int age;
    private String email;
    private String phone;
    private String course;

    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
}
