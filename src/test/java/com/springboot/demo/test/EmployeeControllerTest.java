package com.springboot.demo.test;
import com.springboot.demo.dao.EmployeeDao;
import com.springboot.demo.entities.Employee;
import com.springboot.demo.exception.NameAlreadyExistException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testCreateEmployee()
    {
            Employee employee = new Employee();
            employee.setName("Gomtesh1111");
            employee.setSalary(25000.0f);
            Employee e = employeeDao.save(employee);
            assertEquals(e.getName(), employee.getName());
    }

    @Test
    @ExceptionHandler(NameAlreadyExistException.class)
    public void testDuplicateEmployeeNameNotAllowed()
    {
        try {
            Employee employee = new Employee();
            employee.setName("Gomtesh1111");
            employee.setSalary(2222);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }





    public void getEmployeesTest() throws Exception {
       List<Employee> employeeList = employeeDao.findAll();
    }



}
