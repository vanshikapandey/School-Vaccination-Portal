package com.vaccine.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaccine.entities.VaccinationRecord;
import com.vaccine.service.VaccinationRecordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vaccinations")
@RequiredArgsConstructor
public class VaccinationRecordController {

    private final VaccinationRecordService recordService;

    @PostMapping("/mark")
    public ResponseEntity<VaccinationRecord> markVaccinated(@RequestParam Long studentId, @RequestParam Long driveId) {
        return ResponseEntity.ok(recordService.markVaccinated(studentId, driveId));
    }

    @GetMapping("/all")
    public List<VaccinationRecord> getAllRecords() {
        return recordService.getAllRecords();
    }
}
