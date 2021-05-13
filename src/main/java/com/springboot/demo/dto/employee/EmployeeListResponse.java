package com.springboot.demo.dto.employee;

import com.springboot.demo.dto.BaseResponse;
import com.springboot.demo.entities.Employee;
import lombok.ToString;

import java.util.List;
public class EmployeeListResponse extends BaseResponse {

    private List<Employee> employeeList;
    private Integer size;

    public EmployeeListResponse(List<Employee> e, Integer size) {
        super(true);
        this.employeeList = e;
        this.size = size;
    }
}
