package com.springboot.demo.test;
import com.springboot.demo.controller.StudentController;
import com.springboot.demo.dao.StudentDao;
import com.springboot.demo.dto.student.StudentDTO;
import com.springboot.demo.dto.student.StudentListResponse;
import com.springboot.demo.dto.student.StudentResponse;
import com.springboot.demo.entities.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {
    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentDao studentDao;
    @Test
    public void testCreateStudent() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Sachin Patil");
        studentDTO.setCity("Kolhapur");
        ResponseEntity<StudentResponse> response = studentController.addStudent(studentDTO);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
        Assert.assertNotNull(response.getBody().getStudent());
    }
    @Test
    public void testDuplicateStudentNameNotAllowed() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Amit Shete");
        studentDTO.setCity("Satara");
        ResponseEntity<StudentResponse> response = studentController.addStudent(studentDTO);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);

    }
    @Test
    public void testListStudents() throws Exception {
        ResponseEntity<StudentListResponse> response = studentController.getStudents();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
        Assert.assertNotNull(response.getBody().getStudentList());
    }
    @Test
    public void testUpdateStudent() {
        Student student = new Student();
        student.setName("Mrunali mali");
        student.setCity("Karad");
        ResponseEntity<StudentResponse> response = studentController.updateStudent(3, student);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
        Assert.assertEquals(response.getBody().getStudent().getName(), student.getName());
    }
    @Test
    public void testDeleteStudent(){
        Student student = studentDao.findStudentByName("Mrunali Mali");
        ResponseEntity<StudentResponse> response = studentController.deleteStudent(student.getId());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
    }
}
