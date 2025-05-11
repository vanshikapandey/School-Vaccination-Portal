package com.vaccine.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "student_class")
    private String studentClass;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<VaccinationRecord> vaccinations;
}
