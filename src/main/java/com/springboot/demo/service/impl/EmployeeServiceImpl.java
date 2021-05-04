package com.springboot.demo.service.impl;

import com.springboot.demo.dao.EmployeeDao;
import com.springboot.demo.entities.Employee;
import com.springboot.demo.exception.NameAlreadyExistException;
import com.springboot.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.findById(id).get();
    }

    @Override
    public Employee addEmployee(@RequestBody Employee employee){
        if(employeeDao.findEmployeeByName(employee.getName()) != null){
            throw new NameAlreadyExistException("Employee Name already exist");
        }else{
            return employeeDao.save(employee);
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
