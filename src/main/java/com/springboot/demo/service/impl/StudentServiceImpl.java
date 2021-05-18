package com.springboot.demo.service.impl;
import com.springboot.demo.dao.StudentDao;
import com.springboot.demo.dto.student.StudentDTO;
import com.springboot.demo.dto.student.StudentListResponse;
import com.springboot.demo.dto.student.StudentResponse;
import com.springboot.demo.entities.Student;
import com.springboot.demo.exception.NameAlreadyExistException;
import com.springboot.demo.exception.ResourceNotFoundException;
import com.springboot.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private StudentDao studentDao;

    @Override
    public StudentListResponse getStudents() {
        List<Student> studentList =  studentDao.findAll();
        return new StudentListResponse(studentList, studentList.size());
    }

    @Override
    public Student getStudentById(int id) {

        return studentDao.findById(id).get();
    }

    @Override
    public StudentResponse addStudent(@RequestBody StudentDTO student)throws NameAlreadyExistException {
        if (studentDao.findStudentByName(student.getName()) != null) {
            throw new NameAlreadyExistException("Student Name already exist");
        }else {
            return new StudentResponse(studentDao.save(new Student(student)));
        }
    }

    @Override
    public StudentResponse updateStudent(int id, Student students) {
        Student student = studentDao.findById(id).get();
        student.setName(students.getName());
        student.setCity(students.getCity());
        return new StudentResponse(studentDao.save(student));
    }

    @Override
    public void deleteStudent(int id) throws ResourceNotFoundException {
        Student entity = studentDao.getOne(id);
        if(studentDao.existsById(id)){
           studentDao.delete(entity);
        }else {
            throw new ResourceNotFoundException("Student not found for this id : " + id);
        }
    }
}
