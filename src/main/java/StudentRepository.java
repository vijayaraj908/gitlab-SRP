package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // This allows you to find students where the 'course' field matches any in the list
    List<Student> findByCourseIn(List<String> courses);
}
