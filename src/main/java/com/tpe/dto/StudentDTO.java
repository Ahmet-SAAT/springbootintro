package com.tpe.dto;

import com.tpe.domain.Student;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {


    private Long id;

    @NotNull(message = "fist name can not be null") //controllerda kontrol eder.
    @NotBlank(message = "fist name can not be white space") //controllerda kontrol eder.
    @Size(min = 2, max = 25, message = "first name '{validatedValue}' must be between {min} and {max} long")

    private String firstName;//POJOdaki fielderden farkli isim de olabilir

    private String lastName;

    private Integer grade;


    @Email(message = "Provide valid email")
    private String email;

    private String phoneNumber;


    private LocalDateTime createDate=LocalDateTime.now();

    public StudentDTO(Student student){
        this.id= student.getId();
        this.firstName=student.getName();
        this.lastName=student.getLastName();
        this.grade=student.getGrade();
        this.email=student.getEmail();
        this.phoneNumber=student.getPhoneNumber();
        this.createDate=student.getCreateDate();
    }


}
