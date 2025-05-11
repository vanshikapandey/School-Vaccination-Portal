package com.vaccine.entities;

import java.time.LocalDate;

import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "vaccination_records")
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private VaccinationDrive drive;

    @Column(name = "date_given")
    private LocalDate dateGiven;

    private boolean vaccinated;
}