package com.springboot.demo.dto.student;
import com.springboot.demo.dto.BaseResponse;
import com.springboot.demo.entities.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class StudentListResponse extends BaseResponse {
    private List<Student> studentList;
    private Integer size;
    public StudentListResponse(List<Student> studentList, Integer size) {
        super(true);
        this.studentList = studentList;
        this.size = size;
    }
}
