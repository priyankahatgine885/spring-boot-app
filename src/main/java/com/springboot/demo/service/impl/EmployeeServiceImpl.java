package com.springboot.demo.service.impl;

import com.springboot.demo.dao.EmployeeDao;
import com.springboot.demo.dto.employee.EmployeeDTO;
import com.springboot.demo.dto.employee.EmployeeListResponse;
import com.springboot.demo.dto.employee.EmployeeResponse;
import com.springboot.demo.entities.Employee;
import com.springboot.demo.exception.NameAlreadyExistException;
import com.springboot.demo.dto.BaseResponse;
import com.springboot.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public EmployeeListResponse getEmployees() {
        List<Employee> employeeList = employeeDao.findAll();
        return new EmployeeListResponse(employeeList, employeeList.size()) ;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.findById(id).get();
    }

    @Override
    public EmployeeResponse addEmployee(@RequestBody EmployeeDTO employee){
        if(employeeDao.findEmployeeByName(employee.getName()) != null){
            throw new NameAlreadyExistException("Employee Name already exist");
        }else{
//            Employee emp = new Employee(employee);
//            Employee e = employeeDao.save(emp);
            return  new EmployeeResponse(employeeDao.save(new Employee(employee)));
        }
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Employee employees = employeeDao.findById(id).get();
        employees.setName(employee.getName());
        employees.setSalary(employee.getSalary());
        employeeDao.save(employees);
        return employees;
    }

    @Override
    public void deleteEmployee(int id) {
        Employee entity = employeeDao.getOne(id);
        employeeDao.delete(entity);
    }
}
