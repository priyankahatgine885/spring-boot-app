package com.springboot.demo.service;
import com.springboot.demo.dto.student.StudentDTO;
import com.springboot.demo.dto.student.StudentListResponse;
import com.springboot.demo.dto.student.StudentResponse;
import com.springboot.demo.entities.Student;

public interface StudentService {
   StudentListResponse getStudents();
   Student getStudentById(int id);
   StudentResponse addStudent(StudentDTO student);
   StudentResponse updateStudent(int id, Student student);
   void deleteStudent(int id);
}
