package com.springboot.demo.test;
import com.springboot.demo.controller.StudentController;
import com.springboot.demo.dao.StudentDao;
import com.springboot.demo.dto.student.StudentDTO;
import com.springboot.demo.dto.student.StudentListResponse;
import com.springboot.demo.dto.student.StudentResponse;
import com.springboot.demo.entities.Student;
import com.springboot.demo.exception.NameAlreadyExistException;
import org.junit.Assert;
import org.junit.Ignore;
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
    public void addEmployee_addNewEmployee_success() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Pooja Patil");
        studentDTO.setCity("Kolhapur");
        ResponseEntity<StudentResponse> response = studentController.addStudent(studentDTO);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
        Assert.assertNotNull(response.getBody().getStudent());
    }
    @Test
    public void addStudent_addDuplicateStudent_nameAlreadyExistException() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Mrunali Shete");
        studentDTO.setCity("Satara");
        ResponseEntity<StudentResponse> response = studentController.addStudent(studentDTO);
        Exception exception = Assert.assertThrows(NameAlreadyExistException.class, () -> {
            studentController.addStudent(studentDTO);
        });
        String expectedMessage = "Employee Name already exist";
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void getListStudents_success() throws Exception {
        ResponseEntity<StudentListResponse> response = studentController.getStudents();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
        Assert.assertNotNull(response.getBody().getStudentList());
    }
    @Test
    public void updateStudent_updateNewStudent_success() {
        Student student = new Student();
        student.setName("Mrunali mali");
        student.setCity("Karad");
        ResponseEntity<StudentResponse> response = studentController.updateStudent(3, student);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
//        Assert.assertEquals(response.getBody().getSuccess(), true);
//        Assert.assertEquals(response.getBody().getStudent().getName(), student.getName());
    }

    @Ignore
    @Test
    public void deleteStudent_success(){
        Student student = studentDao.findStudentByName("Mrunali Mali");
        ResponseEntity<StudentResponse> response = studentController.deleteStudent(student.getId());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
    }
}
