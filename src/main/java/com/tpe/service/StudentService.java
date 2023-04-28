package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
   return studentRepository.findAll();//jpa sayesinde methodlar hazir

    }

    public void createStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())){
            throw new ConflictException("Email is already exist !!");

        }

        studentRepository.save(student);
    }

    public Student findStudent(Long id) {

      return studentRepository.findById(id).orElseThrow(
              ()->new ResourceNotFoundException("Student not found with id : "+id));
      //optional nedir.Istedigimvarsa onu yoksa bos optional dondurur nullexception gelmez.
    }


    public void deleteStudent(Long id) {
        Student student=findStudent(id);//exception varsa burada atar
        studentRepository.delete(student);
    }

    public void updateStudent(Long id, StudentDTO studentDTO) {
        //email db de var mi cunku unique
       boolean emailExist= studentRepository.existsByEmail(studentDTO.getEmail());

        //istenilen idli student var mi
        Student student=findStudent(id);//pojo gelecek

        //1-kendi emailim ahmet .ahmet yaptim update olur
        //2-kendi emailim ahmet .mehmet yaptim dbde mehmet yok update olur
        //1-kendi emailim ahmet .ali yaptim db de ali var exception olur

  if (emailExist && ! studentDTO.getEmail().equals(student.getEmail())){
      throw new ConflictException("Email is already exist ");
  }

  student.setName(studentDTO.getFirstName());
  student.setLastName(studentDTO.getLastName());
  student.setGrade(studentDTO.getGrade());
  student.setEmail(studentDTO.getEmail());
  student.setPhoneNumber(studentDTO.getPhoneNumber());
  studentRepository.save(student);

    }

    public Page<Student> getAllWithPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }


    public List<Student> findStudent(String lastName) {
       return studentRepository.findByLastName(lastName);
    }

    public List<Student> findAllEqualsGrade(Integer grade) {

        return studentRepository.findAllEqualsGrade(grade);


    }

    public StudentDTO findStudentDTOById(Long id) {
      return   studentRepository.findStudentDTOById(id).orElseThrow(
              ()-> new ResourceNotFoundException("Student not found with id : "+id));
    }
}
