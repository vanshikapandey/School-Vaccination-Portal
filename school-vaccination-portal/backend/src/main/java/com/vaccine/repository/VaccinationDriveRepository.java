package com.vaccine.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaccine.entities.VaccinationDrive;

public interface VaccinationDriveRepository extends JpaRepository<VaccinationDrive, Long> {
    List<VaccinationDrive> findByDateBetween(LocalDate start, LocalDate end);
}