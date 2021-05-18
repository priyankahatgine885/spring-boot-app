package com.springboot.demo.controller;
import com.springboot.demo.dto.student.StudentDTO;
import com.springboot.demo.dto.student.StudentListResponse;
import com.springboot.demo.dto.student.StudentResponse;
import com.springboot.demo.entities.Student;
import com.springboot.demo.exception.RecordNotFoundException;
import com.springboot.demo.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@Tag(name = "Student", description = "API for Student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public ResponseEntity<StudentListResponse> getStudents() {
        StudentListResponse studentListResponse = this.studentService.getStudents();
        HttpStatus statusCode = studentListResponse.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(studentListResponse, statusCode);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) throws RecordNotFoundException {
        if ((id <= 0)) {
            throw new RecordNotFoundException("Invalid student id : " + id);
        }
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentDTO student) {
        StudentResponse studentResponse = this.studentService.addStudent(student);
        studentResponse.setMessage("Student created successfully");
        final HttpStatus statusCode = studentResponse.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(studentResponse, statusCode);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable int id, @RequestBody Student student) {
        try {
            StudentResponse studentResponse = this.studentService.updateStudent(id, student);
            studentResponse.setMessage("Student updated successfully");
            final HttpStatus statusCode = studentResponse.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(studentResponse, statusCode);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<StudentResponse> deleteStudent(@PathVariable int id) {
        this.studentService.deleteStudent(id);
        StudentResponse studentResponse = new StudentResponse(true);
        studentResponse.setMessage("Student deleted successfully");
        final HttpStatus statusCode = studentResponse.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(studentResponse, statusCode);
    }
}
