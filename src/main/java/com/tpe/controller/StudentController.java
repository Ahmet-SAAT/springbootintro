package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/{id}}")//http://localhost:8080/students/id
    public ResponseEntity<Student> getStudentWithPath(@PathVariable("id") Long id){
        Student student=studentService.findStudent(id);
        return ResponseEntity.ok(student);
    }

    //delete a student by Id
   @DeleteMapping("/{id}")// "/" olsa da olmasa da olur kendisi otomtikmen koyar zaten
            public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
      Map<String,String> map=new HashMap<>();
       map.put("message","Student is deleted successfuly");
       map.put("status","true");
       return new ResponseEntity<>(map, HttpStatus.OK);//201

   }

   //clientten service kadar kisim DTO DBden Service kadar pojo denir
    //DTO(data transfer object) pojo servise kadar gelir orada dto ya donusur.
    //bu islem bize hiz ve guvenlik kazandirir.
    //clientten service gelen ve serviceden cliente gelen dto olmali
    //service gelen dt serviceden dbye giderken pojo olur.dbdeb servicede pojo gider.
    //clientten dbye giderken servisede pojoya donmezse exception verir.
    //dbden cliente giderken serviste dto ya donmezse exception olmaz ama hiz ve guvenlik sorunu olur.

   //update student  endpoint+id+json(requestbody)+http metodu
    @PutMapping("/{id}")//http://localhost:8080/students/id
     public ResponseEntity<Map<String,String>> updateStudent(
             @PathVariable("id") Long id,//tek sorgu varsa parantez icine yazmayabiliriz.
                                                             @RequestBody StudentDTO studentDTO ){
            studentService.updateStudent(id,studentDTO);
        Map<String,String> map=new HashMap<>();
        map.put("message","Student is updated successfuly");
        map.put("status","true");
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    //pageable
    @GetMapping("/page")//http://localhost:8080/students/page?page=1&size=2&sort=name&direction=ASC
    public ResponseEntity<Page<Student>> getAllWithPage(
            @RequestParam ("page") int page,//kacinci sayfa gelsin
            @RequestParam("size") int size,//sayfa basi kac urun girsin
            @RequestParam("sort") String prop,//hangi fielde gore siralanacak
            @RequestParam("direction")Sort.Direction direction//siralama turu
            ){
        Pageable pageable= PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Student> studentPage=studentService.getAllWithPage(pageable);
        return ResponseEntity.ok(studentPage);
    }

    //getbylastname
    @GetMapping("/querylastname")
    public ResponseEntity<List<Student>> getStudentByLastName(@RequestParam("lastName") String lastName){
        List<Student> list=studentService.findStudent(lastName);
        return ResponseEntity.ok(list);
    }

    //getAllStudentByGrade(JPQL)->java persistence query languange
    @GetMapping("/grade/{grade}")//http://localhost:8080/students/grade/85  +get
    public ResponseEntity<List<Student>> getStudentsEqualsGrade(@PathVariable("grade") Integer grade){
        List<Student> list=studentService.findAllEqualsGrade(grade);
        return ResponseEntity.ok(list);
    }

    //DB den direkt DTO olarak datami almak istersem ne yaparim.Yani donusum repoda olsun
    @GetMapping("/query/dto")//http://localhost:8080/students/query/dto?id=1 +get
    public ResponseEntity<StudentDTO> getStudentDTO(@RequestParam("id") Long id){
       StudentDTO studentDTO= studentService.findStudentDTOById(id);
       return  ResponseEntity.ok(studentDTO);
    }


}
