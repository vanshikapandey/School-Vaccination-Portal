package com.vaccine.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vaccine.entities.Student;
import com.vaccine.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getById(Long id) {
        return studentRepository.findById(id);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> search(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    public void importStudentsFromCSV(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            for (CSVRecord record : records) {
                String studentId = record.get("studentId");
                String name = record.get("name");
                String studentClass = record.get("studentClass");

                // Avoid duplicate studentIds
                if (studentRepository.findByStudentId(studentId).isEmpty()) {
                    Student s = new Student();
                    s.setStudentId(studentId);
                    s.setName(name);
                    s.setStudentClass(studentClass);
                    studentRepository.save(s);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

}
