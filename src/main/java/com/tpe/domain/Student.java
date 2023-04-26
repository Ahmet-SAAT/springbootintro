package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter//getterlar yapildi
//class seviyesinden ziyade variabler uzerine getter setter yazabiliriz ama uzun surer.Hepine yapmak lazim
//istemedigimiz variable icin @Setter(AccessLevel.NONE) yapariz.
@Setter//setterlar yapildi
@AllArgsConstructor//tum variablelerle cons yapildi
@NoArgsConstructor//prametresiz cons yapildi
//@RequiredArgsConstructor//sadece final olan variabler ile parametreli cons olusturur.final olmayan olmaz.

//@Data ise yukaridakilerin hepsini kapsayan pakettir.

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Setter(AccessLevel.NONE)//setteri olmasin dedik
    private Long id;

    @NotNull(message = "fist name can not be null") //controllerda kontrol eder.
    @NotBlank(message = "fist name can not be white space") //controllerda kontrol eder.
    @Size(min = 2, max = 25, message = "first name '{validatedValue}' must be between {min} and {max} long")
    @Column(nullable = false,length = 25)//kontrolu repoda yapar
    private String name;

    @Column(nullable = false,length = 25)//kontrolu repoda yapar
    private String lastName;

    private Integer grade;

    @Column(nullable = false,unique = true)
    @Email(message = "Provide valid email")//@isareti var mi . var mi buna bakiyor
    private String email;

    private String phoneNumber;

    @Setter(AccessLevel.NONE)//setteri olmasin dedik
    private LocalDateTime createDate=LocalDateTime.now();


   /* public Student() {
    }


    public Student(Long id, String name, String lastName, Integer grade, String email, String phoneNumber, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.grade = grade;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grade=" + grade +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createDate=" + createDate +
                '}';
    }*/
}
