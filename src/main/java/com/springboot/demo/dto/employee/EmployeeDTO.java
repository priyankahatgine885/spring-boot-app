package com.springboot.demo.dto.employee;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {
    private int id;
    private String name;
    private float salary;
}
