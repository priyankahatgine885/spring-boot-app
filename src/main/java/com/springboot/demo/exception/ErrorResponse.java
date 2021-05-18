package com.springboot.demo.exception;
import com.springboot.demo.dto.BaseResponse;
import com.springboot.demo.entities.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse extends BaseResponse {
    private Employee employee;
    public ErrorResponse(Employee employee) {
        super(true);
        this.employee = employee;
    }
}
