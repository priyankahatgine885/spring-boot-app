package com.springboot.demo.service;

import com.springboot.demo.dto.employee.EmployeeDTO;
import com.springboot.demo.dto.employee.EmployeeListResponse;
import com.springboot.demo.dto.employee.EmployeeResponse;
import com.springboot.demo.entities.Employee;
import com.springboot.demo.dto.BaseResponse;

public interface EmployeeService {
    EmployeeListResponse getEmployees();
    Employee getEmployeeById(int id);
    EmployeeResponse addEmployee(EmployeeDTO employee);
    Employee updateEmployee(int id, Employee employee);
    void deleteEmployee(int id);
}
