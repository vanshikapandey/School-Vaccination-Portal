package com.vaccine.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vaccination_drives")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class VaccinationDrive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vaccine_name")
    private String vaccineName;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "available_doses")
    private int availableDoses;
}
