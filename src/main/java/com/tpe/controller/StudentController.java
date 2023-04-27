package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//contreller ile restcontroller farki ne restful api arki ne
//restful apide status code gelir ve controller ozellesmis olur.
//rest mimarisine uygun controller clasidir bu

@RequestMapping("/students")//http://localhost:8080/students
public class StudentController {

    @Autowired
    private StudentService studentService;

    //butun ogrencileri getirelim endpoint+http method
    @GetMapping //http://localhost:8080/students +get
    public ResponseEntity<List<Student>> getAll() {//responseentity sonuc ve http status doner
        List<Student> students = studentService.getAll();
        return ResponseEntity.ok(students);//studentslari gonderir+ http.statuscode=200 gonderir
    }//students+statuscode gelecek


    //create new student
    @PostMapping//http://localhost:8080/students +post +json //farkli methodlarin ayni endpointi olabilir
    public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student) {
        //student create edecegim.Bana studentin datalari gelmeli.Nail alacagiz bunlari.
//json dosyasi reguestinbodysi ile geliyor @requestboty ile bu jsonu studentla mapledik.
// her bir fieldi jsona bakarap maplar.Jsonda eslescek fialder valid olmayabilir.Notblank,notempty vs
// @valid ile controllerda validationa bakalim.Repoya kadar gidip validationu orada yapip hata vermesin.
        studentService.createStudent(student);//exception varsa burada firlar
       Map<String,String> map=new HashMap<>();
       //nicin enjekte yapmadim da newledim.Bir ya da iki kez kullanacagim obje icin enjekteye gerek yok.
        //Fazla kullanacagimiz objeler icin enjection yapmaliyiz.
       map.put("message","Student is created successfuly");
       map.put("status","true");
       return new ResponseEntity<>(map, HttpStatus.CREATED);//201
    }

    //get a student by Id via RequestParam
    @GetMapping("/query")////http://localhost:8080/students/query?id=id
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id){
        Student student=studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    //get a student by Id via PathParam

    @GetMapping("{id}}")////http://localhost:8080/students/id
    public ResponseEntity<Student> getStudentWithPath(@PathVariable("id") Long id){
        Student student=studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    //delete a student by Id
   @DeleteMapping("/{id}")// "/" olsa da olmasa da olur kendisi otomtikmen koyar zaten
            public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
      Map<String,String> map=new HashMap<>();
       map.put("message","Student is created successfuly");
       map.put("status","true");
       return new ResponseEntity<>(map, HttpStatus.OK);//201

   }



}
