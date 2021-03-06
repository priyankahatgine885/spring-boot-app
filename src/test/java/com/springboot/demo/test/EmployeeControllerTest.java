package com.springboot.demo.test;
import com.springboot.demo.controller.EmployeeController;
import com.springboot.demo.dao.EmployeeDao;
import com.springboot.demo.dto.employee.EmployeeDTO;
import com.springboot.demo.dto.employee.EmployeeListResponse;
import com.springboot.demo.dto.employee.EmployeeResponse;
import com.springboot.demo.entities.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest extends  BaseInitializer {
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql("/data.sql")
    public void testCreateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Gomtesh");
        employeeDTO.setSalary(25000.0f);
        ResponseEntity<EmployeeResponse> response = employeeController.addEmployee(employeeDTO);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
        Assert.assertNotNull(response.getBody().getEmployee());
    }
    @Test
    public void testDuplicateEmployeeNameNotAllowed() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Amit Patil11111");
        employeeDTO.setSalary(2222);
        ResponseEntity<EmployeeResponse> response = employeeController.addEmployee(employeeDTO);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);

    }

    @Test
    public void testListEmployees() throws Exception {
        ResponseEntity<EmployeeListResponse> response = employeeController.getEmployees();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
        Assert.assertNotNull(response.getBody().getEmployeeList());
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setName("Dipali Patil");
        employee.setSalary(20000);
        ResponseEntity<EmployeeResponse> response = employeeController.updateEmployee(1, employee);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
        Assert.assertEquals(response.getBody().getEmployee().getName(), employee.getName());
    }

    @Test
    public void testDeleteEmployee(){
        Employee employee = employeeDao.findEmployeeByName("Gomtesh");
        ResponseEntity<EmployeeResponse> response = employeeController.deleteEmployee(employee.getId());
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody().getSuccess(), true);
    }
}
