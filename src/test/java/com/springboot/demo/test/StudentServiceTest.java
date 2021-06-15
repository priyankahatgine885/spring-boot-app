package com.springboot.demo.test;
import com.springboot.demo.dao.StudentDao;
import com.springboot.demo.dto.student.StudentDTO;
import com.springboot.demo.dto.student.StudentListResponse;
import com.springboot.demo.dto.student.StudentResponse;
import com.springboot.demo.entities.Student;
import com.springboot.demo.exception.EmptyListException;
import com.springboot.demo.exception.NameAlreadyExistException;
import com.springboot.demo.exception.ResourceNotFoundException;
import com.springboot.demo.service.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest extends BaseInitializer{
        @Autowired
        private StudentService studentService;
        @Autowired
        StudentDao studentDao;

        @Test
        public void addStudent_addNewStudent_success() {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName("Janavi Mali");
            studentDTO.setCity("Rukadi");
            StudentResponse studentResponse = studentService.addStudent(studentDTO);
            Assert.assertNotNull(studentResponse);

        }

        @Test
        public void addStudent_addDuplicateStudent_nameAlreadyExistException() {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName("Amit Patil");
            studentDTO.setCity("Pune");
            StudentResponse response = studentService.addStudent(studentDTO);
            Exception exception = Assert.assertThrows(NameAlreadyExistException.class, () -> {
                studentService.addStudent(studentDTO);
            });
            String expectedMessage = "Student Name already exist";
            String actualMessage = exception.getMessage();
            Assert.assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void getListStudents_getListStudents_emptyListException(){
            StudentListResponse response = studentService.getStudents();
            if(response.getSize() != 0) {
                Assert.assertNotNull(response);
            }else {
                throw new EmptyListException("List is empty");
            }
        }

        @Test
        public void updateStudent_updateNewStudent_success() {
            Student student = new Student();
            student.setName("Dipali Patil");
            student.setCity("Satara");
            StudentResponse response = studentService.updateStudent(1, student);
            Assert.assertNotNull(response);
        }

        @Test
        public void deleteStudent_success() {
           Student student = studentDao.findById(1).get();
            if(studentDao.existsById(student.getId())){
                studentService.deleteStudent(student.getId());
            }else {
                throw new ResourceNotFoundException("Student not found for this id : " + 1);
            }
        }

}
