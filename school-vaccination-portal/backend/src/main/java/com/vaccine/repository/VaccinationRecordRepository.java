package com.vaccine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaccine.entities.VaccinationRecord;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
    List<VaccinationRecord> findByStudentId(Long studentId);
}