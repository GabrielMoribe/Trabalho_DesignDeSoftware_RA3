package com.example.TrabalhoRA3.services;

import com.example.TrabalhoRA3.model.Grade;
import com.example.TrabalhoRA3.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> findAll() {
        return gradeRepository.findAllGrades();
    }

    public Optional<Grade> findById(Long id) {
        return gradeRepository.findGradeById(id);
    }

    public Grade saveGrade(Long enrollmentId, Double value, String feedback) {
        gradeRepository.insertGrade(enrollmentId, value, feedback);
        // Buscar o grade recém-criado
        return gradeRepository.findLastInsertedGrade(enrollmentId, value).orElse(null);
    }

    public void updateGrade(Long id, Long enrollmentId, Double value, String feedback) {
        gradeRepository.updateGrade(id, enrollmentId, value, feedback);
    }

    public void deleteById(Long id) {
        gradeRepository.deleteGradeById(id);
    }

    public List<Grade> findByEnrollmentId(Long enrollmentId) {
        return gradeRepository.findByEnrollmentId(enrollmentId);
    }

    public List<Grade> findByValueGreaterThanEqual(Double minValue) {
        return gradeRepository.findByValueGreaterThanEqual(minValue);
    }
}
