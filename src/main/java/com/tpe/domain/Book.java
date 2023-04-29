package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @JsonProperty("bookName")//bu classin sadece json ciktisinda name, bookName olarak gorunur.Javada Db ise name dir
    //coogu classda name olacagi icin json ciktisinda karisikligi engellemis oluruz.
    private String name;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore//buraya gelirsen asagida verilen  clasa tekrar gitme diyorum.Gitse gelecek gidecek dongu olusacak
    private Student student;

    //getter
    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public Student getStudent() {
        return student;
    }
}
