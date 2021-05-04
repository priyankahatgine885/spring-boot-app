package com.springboot.demo.dao;

import com.springboot.demo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    Employee findEmployeeByName(String name);
}
