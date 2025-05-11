package com.vaccine.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vaccine.entities.Student;
import com.vaccine.entities.VaccinationDrive;
import com.vaccine.entities.VaccinationRecord;
import com.vaccine.repository.StudentRepository;
import com.vaccine.repository.VaccinationDriveRepository;
import com.vaccine.repository.VaccinationRecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VaccinationRecordService {

    private final VaccinationRecordRepository recordRepo;
    private final StudentRepository studentRepo;
    private final VaccinationDriveRepository driveRepo;

    public VaccinationRecord markVaccinated(Long studentId, Long driveId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        VaccinationDrive drive = driveRepo.findById(driveId).orElseThrow();

        boolean alreadyVaccinated = recordRepo
                .findByStudentId(studentId)
                .stream()
                .anyMatch(r -> r.getDrive().getId().equals(driveId));

        if (alreadyVaccinated) {
            throw new IllegalArgumentException("Student already vaccinated in this drive.");
        }

        VaccinationRecord record = new VaccinationRecord();
        record.setStudent(student);
        record.setDrive(drive);
        record.setDateGiven(LocalDate.now());
        record.setVaccinated(true);
        return recordRepo.save(record);
    }

    public List<VaccinationRecord> getAllRecords() {
        return recordRepo.findAll();
    }
}
