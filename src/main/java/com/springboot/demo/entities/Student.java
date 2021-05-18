package com.springboot.demo.entities;
import com.springboot.demo.dto.student.StudentDTO;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, name = "name")
    @NotBlank
    private String name;

    @Column(name = "city")
    @NotBlank
    private String city;

    public Student(StudentDTO studentDTO){
        this.setName(studentDTO.getName());
        this.setCity(studentDTO.getCity());
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", city=" + city + "]";
    }
}
