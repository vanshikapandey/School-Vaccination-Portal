package com.vaccine.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaccine.entities.VaccinationDrive;
import com.vaccine.repository.StudentRepository;
import com.vaccine.repository.VaccinationDriveRepository;
import com.vaccine.repository.VaccinationRecordRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final StudentRepository studentRepo;
    private final VaccinationRecordRepository recordRepo;
    private final VaccinationDriveRepository driveRepo;

    @GetMapping
    public Map<String, Object> getSummary() {
        long totalStudents = studentRepo.count();
        long vaccinated = recordRepo.count();

        List<VaccinationDrive> upcoming = driveRepo.findByDateBetween(
                LocalDate.now(), LocalDate.now().plusDays(30));

        Map<String, Object> data = new HashMap<>();
        data.put("totalStudents", totalStudents);
        data.put("vaccinatedCount", vaccinated);
        data.put("vaccinationPercentage", totalStudents == 0 ? 0 : (vaccinated * 100) / totalStudents);
        data.put("upcomingDrives", upcoming);
        return data;
    }
}
