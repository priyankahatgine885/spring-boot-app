package com.springboot.demo.service;
import com.springboot.demo.entities.Student;

import java.util.List;

public interface StudentService {
   List<Student> getStudents();
   Student getStudentById(int id);
   Student addStudents(Student student);
   Student updateStudents(int id, Student student);
   void deleteStudents(int id);
}
