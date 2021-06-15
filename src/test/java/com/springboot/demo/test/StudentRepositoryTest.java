package com.springboot.demo.test;
import com.springboot.demo.dao.StudentDao;
import com.springboot.demo.dto.student.StudentDTO;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest extends BaseInitializer{
        @Autowired
        private StudentDao studentDao;
        @Autowired
        private StudentService studentService;

        @Test
        public void addStudent_addNewStudent_success() {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName("Janavi Mali");
            studentDTO.setCity("Pune");
            Student student = studentDao.save(new Student(studentDTO));
            Assert.assertNotNull(student);
            Assert.assertTrue(student.getId() > 0);
        }
        @Test
        public void addStudent_addDuplicateStudent_nameAlreadyExistException() {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName("Amit Patil11111");
            studentDTO.setCity("Satara");
            Student student = studentDao.save(new Student(studentDTO));
            Exception exception = Assert.assertThrows(NameAlreadyExistException.class, () -> {
                studentDao.save(new Student(studentDTO));
            });
            String expectedMessage = "Student Name already exist";
            String actualMessage = exception.getMessage();
            Assert.assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void getListStudents_getListStudents_emptyListException(){
            List<Student> studentList = studentDao.findAll();
            if(studentList.size() != 0) {
                Assert.assertNotNull(studentList);
            }else {
                throw new EmptyListException("List is empty");
            }
        }

        @Test
        public void updateStudent_updateNewStudent_success() {
            Student students = studentDao.findById(1).get();
            students.setName("Dipali Patil");
            students.setCity("Kolhapur");
            Student student= studentDao.save(students);
            Assert.assertNotNull(student);
        }

        @Test
        public void deleteStudent_ResourceNotFoundException(){
            Student entity = studentDao.getOne(1);
            if(studentDao.existsById(entity.getId())){
                studentDao.delete(entity);
            }else {
                throw new ResourceNotFoundException("Employee not found for this id : " + 1);
            }
        }
}
