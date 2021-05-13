package com.springboot.demo.dto.employee;

import com.springboot.demo.dto.BaseResponse;
import com.springboot.demo.entities.Employee;

public class EmployeeResponse extends BaseResponse {
    private Employee employee;
    public EmployeeResponse(Employee e) {
        super(true);
        this.employee = e;
    }
}
