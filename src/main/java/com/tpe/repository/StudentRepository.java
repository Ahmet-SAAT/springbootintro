package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository//opsiyonel ama kod okunurlugu
public interface StudentRepository extends JpaRepository<Student, Long> {

//jpaRepository benden entity clasini ve id tipini generic icine yazmami istiyor.
    //Bu interface bize sql sorgusu yazmamamizi sagliyor

    boolean existsByEmail(String email);

    List<Student> findByLastName(String lastName);

    //JPQL
    @Query("SELECT s FROM Student s WHERE s.grade=:pGrade" )
//grade degerini alabilmek icin @Param anno ihtiyac var
    List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);

    //SQL
    @Query(value = "SELECT * FROM Student s WHERE s.grade=:pGrade",nativeQuery = true)
    List<Student> findAllEqualsGradeWihtSql(@Param("pGrade") Integer grade);

    //genelde jounli islemlerde jpql ya da sql yazacagiz.Dogrudan jpa turetme yapmaz.
    //Basit queryler icin FPA dan method turetmek daha kullanislidir.

    //JPQL mucizesi POLO=DTO donusumu//jpql servicede degil repoda kullanilabilir
    //StudentDTO icine parametre olarak Student verdik.Cons calisti.
    //maplemek için eşit field olması lazım.DTO ya istedigimiz fieldalari alirsak buradaki mapleme olmaz
    //servicede yaptigimizda istege gore setleyebilirz
 @Query("Select new com.tpe.dto.StudentDTO(s) from Student s where s.id=:id")
    Optional<StudentDTO> findStudentDTOById(@Param("id") Long id);






}
