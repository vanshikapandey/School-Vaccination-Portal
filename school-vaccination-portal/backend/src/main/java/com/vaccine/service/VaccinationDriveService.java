package com.vaccine.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vaccine.entities.VaccinationDrive;
import com.vaccine.repository.VaccinationDriveRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VaccinationDriveService {

    private final VaccinationDriveRepository driveRepo;

    public List<VaccinationDrive> getAll() {
        return driveRepo.findAll();
    }

    public VaccinationDrive createDrive(VaccinationDrive drive) {
        if (drive.getDate().isBefore(LocalDate.now().plusDays(15))) {
            throw new IllegalArgumentException("Drive must be scheduled at least 15 days in advance.");
        }
        return driveRepo.save(drive);
    }

    public Optional<VaccinationDrive> getById(Long id) {
        return driveRepo.findById(id);
    }

    public VaccinationDrive updateDrive(Long id, VaccinationDrive updated) {

        VaccinationDrive drive = driveRepo.findById(id).orElseThrow();
        drive.setVaccineName(updated.getVaccineName());
        drive.setDate(updated.getDate());
        drive.setAvailableDoses(updated.getAvailableDoses());
        return driveRepo.save(drive);
    }
}
