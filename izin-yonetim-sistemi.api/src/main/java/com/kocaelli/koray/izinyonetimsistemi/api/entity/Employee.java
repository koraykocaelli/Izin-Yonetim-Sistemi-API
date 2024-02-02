package com.kocaelli.koray.izinyonetimsistemi.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CALISANLAR")
@Data
public class Employee extends BaseEntity {
    @Id
    @SequenceGenerator(name = "employee_seq_gen",sequenceName = "employee_gen",initialValue = 100,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_seq_gen")
    @Column(name = "ID")
    private Long id;
    @Column(name = "ISIM",length = 100)
    private String firstName;
    @Column(name = "SOYISIM",length = 100)
    private String lastName;
    @Column(name = "EMAIL",length = 100)
    private String email;
    @Column(name = "DEPARTMAN",length = 100)
    private String department;
    @Column(name = "IZIN_GUNLERI")
    private int dayOff = 15;
    @Column(name = "KULLANILAN_IZIN_GUNLERI")
    private int usedDayOff = 0;

}
