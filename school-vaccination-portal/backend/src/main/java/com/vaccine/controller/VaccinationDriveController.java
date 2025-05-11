package com.vaccine.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaccine.entities.VaccinationDrive;
import com.vaccine.service.VaccinationDriveService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/drives")
@RequiredArgsConstructor
public class VaccinationDriveController {

    private final VaccinationDriveService driveService;

    @GetMapping
    public List<VaccinationDrive> getAll() {
        return driveService.getAll();
    }

    @PostMapping
    public ResponseEntity<VaccinationDrive> create(@RequestBody VaccinationDrive drive) {
        return ResponseEntity.ok(driveService.createDrive(drive));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccinationDrive> update(@PathVariable Long id, @RequestBody VaccinationDrive drive) {
        return ResponseEntity.ok(driveService.updateDrive(id, drive));
    }
}
