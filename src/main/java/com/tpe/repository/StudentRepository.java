package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//opsiyonel ama kod okunurlugu
public interface StudentRepository extends JpaRepository<Student,Long> {

//jpaRepository benden entity clasini ve id tipini generic icine yazmami istiyor.
    //Bu interface bize sql sorgusu yazmamamizi sagliyor

    boolean existsByEmail(String email);
}
