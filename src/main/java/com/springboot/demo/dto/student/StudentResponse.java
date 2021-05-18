package com.springboot.demo.dto.student;
import com.springboot.demo.dto.BaseResponse;
import com.springboot.demo.entities.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StudentResponse extends BaseResponse {
    private Student student;
    public StudentResponse(Student student) {
        super(true);
        this.student = student;
    }
    public StudentResponse(boolean isSuccess) {
        super(isSuccess);
    }
}
