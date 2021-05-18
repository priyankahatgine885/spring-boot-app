package com.springboot.demo.dto.employee;
import com.springboot.demo.dto.BaseResponse;
import com.springboot.demo.entities.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeResponse extends BaseResponse {
    private Employee employee;
    public EmployeeResponse(Employee e) {
        super(true);
        this.employee = e;
    }
    public EmployeeResponse(boolean isSuccess) {
        super(isSuccess);
    }
}
