package com.example.TrabalhoRA3.services;

import com.example.TrabalhoRA3.model.Enrollment;
import com.example.TrabalhoRA3.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAllEnrollments();
    }

    public Optional<Enrollment> findById(Long id) {
        return enrollmentRepository.findEnrollmentById(id);
    }

    public Enrollment saveEnrollment(Long studentId, Long courseId, java.time.LocalDate enrollmentDate) {
        enrollmentRepository.insertEnrollment(studentId, courseId, enrollmentDate);
        return enrollmentRepository.findLastInsertedEnrollment(studentId, courseId).orElse(null);
    }

    public void updateEnrollment(Long id, Long studentId, Long courseId, java.time.LocalDate enrollmentDate) {
        enrollmentRepository.updateEnrollment(id, studentId, courseId, enrollmentDate);
    }

    public void deleteById(Long id) {
        enrollmentRepository.deleteEnrollmentById(id);
    }

    public List<Enrollment> findByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> findByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
}
