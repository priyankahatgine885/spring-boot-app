package com.springboot.demo.test;
import com.springboot.demo.controller.EmployeeController;
import com.springboot.demo.dao.EmployeeDao;
import com.springboot.demo.dto.employee.EmployeeDTO;
import com.springboot.demo.dto.employee.EmployeeListResponse;
import com.springboot.demo.dto.employee.EmployeeResponse;
import com.springboot.demo.entities.Employee;
import com.springboot.demo.exception.EmptyListException;
import com.springboot.demo.exception.NameAlreadyExistException;
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
public class EmployeeControllerTest extends  BaseInitializer {
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void addEmployee_addNewEmployee_success() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Akshay Patil");
        employeeDTO.setSalary(23000.0f);
        ResponseEntity<EmployeeResponse> response = employeeController.addEmployee(employeeDTO);
        if(response.getStatusCode() == HttpStatus.OK) {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertEquals(response.getBody().getSuccess(), true);
            Assert.assertNotNull(response.getBody().getEmployee());
        }else{
            Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
            Assert.assertEquals(response.getBody().getSuccess(), false);
        }
    }
    @Test
    public void addEmployee_addDuplicateEmployee_nameAlreadyExistException() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Amit Patil11111");
        employeeDTO.setSalary(2222);
        ResponseEntity<EmployeeResponse> response = employeeController.addEmployee(employeeDTO);
        Exception exception = Assert.assertThrows(NameAlreadyExistException.class, () -> {
            employeeController.addEmployee(employeeDTO);
        });
        String expectedMessage = "Employee Name already exist";
        String actualMessage = exception.getMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getListEmployees_getListEmployees_emptyListException(){
        ResponseEntity<EmployeeListResponse> response = employeeController.getEmployees();
        if(response.getBody().getSize() != 0) {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertEquals(response.getBody().getSuccess(), true);
            Assert.assertNotNull(response.getBody().getEmployeeList());
        }else {
            throw new EmptyListException("List is empty");
        }
    }

    @Test
    public void updateEmployee_updateNewEmployee_success() {
        Employee employee = new Employee();
        employee.setName("Priyanka Hatgine");
        employee.setSalary(20000);
        ResponseEntity<EmployeeResponse> response = employeeController.updateEmployee(2, employee);
        if(response.getStatusCode() == HttpStatus.OK) {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertEquals(response.getBody().getSuccess(), true);
            Assert.assertEquals(response.getBody().getEmployee().getName(), employee.getName());
        }else {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
            Assert.assertEquals(response.getBody().getSuccess(), false);
        }
    }

    @Test
    public void deleteEmployee_success(){
        Employee employee = employeeDao.findById(1).get();
        ResponseEntity<EmployeeResponse> response = employeeController.deleteEmployee(employee.getId());
        if(response.getStatusCode() == HttpStatus.OK) {
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertEquals(response.getBody().getSuccess(), true);
        }else{
            Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
            Assert.assertEquals(response.getBody().getSuccess(), false);
        }
    }
}
