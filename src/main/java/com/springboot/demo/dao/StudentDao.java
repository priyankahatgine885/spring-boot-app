package com.springboot.demo.dao;
import com.springboot.demo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Integer> {
    Student findStudentByName(String name);
}
