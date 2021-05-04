package com.springboot.demo.service.impl;

import com.springboot.demo.dao.StudentDao;
import com.springboot.demo.entities.Student;
import com.springboot.demo.exception.NameAlreadyExistException;
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
    public List<Student> getStudents() {
        return studentDao.findAll();
    }

    @Override
    public Student getStudentById(int id) {

        return studentDao.findById(id).get();
    }
    @Override
    public Student addStudents(@RequestBody Student student)throws NameAlreadyExistException {
        if (studentDao.findStudentByName(student.getName()) != null) {
            throw new NameAlreadyExistException("Student Name already exist");
        }else {
            try {
                studentDao.save(student);
            }catch (Exception ex){
               logger.error("Error while saving student", ex);

            }
        }
        return student;
    }
    @Override
    public Student updateStudents(int id, Student students) {
        Student student = studentDao.findById(id).get();
        student.setName(students.getName());
        student.setCity(students.getCity());
        studentDao.save(student);
        return student;
    }

    @Override
    public void deleteStudents(int id) {
        Student entity = studentDao.getOne(id);
        studentDao.delete(entity);
    }
}
