package com.tpe.controller;



import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//contreller ile restcontroller farki ne restful api arki ne
//restful apide status code gelir ve controller ozellesmis olur.
//rest mimarisine uygun controller clasidir bu

@RequestMapping("/students")//http://localhost:8080/students
public class StudentController {

    @Autowired
    private StudentService studentService;

//butun ogrencileri getirelim endpoint+http method
    @GetMapping //http://localhost:8080/students
    public ResponseEntity<List<Student>>getAll(){//responseentity sonuc ve http status doner
        List<Student> students=studentService.getAll();
        return ResponseEntity.ok(students);//studentslari gonderir+ http.statuscode=200 gonderir
    }//students+statuscode gelecek

}
