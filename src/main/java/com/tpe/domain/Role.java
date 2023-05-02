package com.tpe.domain;

import com.tpe.domain.enums.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "tbl_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)//name isimli fieldi UserRole enumtypindan al ve typeler stiring olarak yazilsin
    @Column(length = 30,nullable = false)
    private UserRole name;

    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                '}';
    }
}
