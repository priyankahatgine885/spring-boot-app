package com.springboot.demo.test;
import com.springboot.demo.dao.EmployeeDao;
import com.springboot.demo.dto.employee.EmployeeDTO;
import com.springboot.demo.entities.Employee;
import com.springboot.demo.exception.NameAlreadyExistException;
import com.springboot.demo.exception.ResourceNotFoundException;
import com.springboot.demo.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepository extends  BaseInitializer{
        @Autowired
        private EmployeeDao employeeDao;
        @Autowired
        private EmployeeService employeeService;

        @Test
        public void addEmployee_addNewEmployee_success() {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setName("Janavi Mali");
            employeeDTO.setSalary(23000.0f);
            Employee employee = employeeDao.save(new Employee(employeeDTO));
            Assert.assertNotNull(employee);
            Assert.assertTrue(employee.getId() > 0);

        }
        @Test
        public void addEmployee_addDuplicateEmployee_nameAlreadyExistException() {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setName("Amit Patil11111");
            employeeDTO.setSalary(2222);
            Employee employee = employeeDao.save(new Employee(employeeDTO));
            Exception exception = Assert.assertThrows(NameAlreadyExistException.class, () -> {
                employeeDao.save(new Employee(employeeDTO));
            });
            String expectedMessage = "Employee Name already exist";
            String actualMessage = exception.getMessage();
            Assert.assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void getListEmployees_success() throws Exception {
            List<Employee> employeeList = employeeDao.findAll();
            Assert.assertNotNull(employeeList);
        }

        @Test
        public void updateEmployee_updateNewEmployee_success() {
            Employee employees = employeeDao.findById(1).get();
            employees.setName("Dipali Patil");
            employees.setSalary(20000);
            Employee employee = employeeDao.save(employees);
            Assert.assertNotNull(employee);

        }

        @Test
        public void deleteEmployee_ResourceNotFoundException(){
            Employee entity = employeeDao.getOne(1);
            if(employeeDao.existsById(entity.getId())){
                 employeeDao.delete(entity);
            }else {
                throw new ResourceNotFoundException("Employee not found for this id : " + 1);
            }
        }
}
