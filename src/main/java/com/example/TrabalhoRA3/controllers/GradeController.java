package com.example.TrabalhoRA3.controllers;

import com.example.TrabalhoRA3.model.Grade;
import com.example.TrabalhoRA3.model.Enrollment;
import com.example.TrabalhoRA3.services.GradeService;
import com.example.TrabalhoRA3.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        Optional<Grade> grade = gradeService.findById(id);
        return grade.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        // Buscar Enrollment pelo ID se fornecido
        if (grade.getEnrollmentId() != null) {
            Optional<Enrollment> enrollment = enrollmentService.findById(grade.getEnrollmentId());
            if (enrollment.isPresent()) {
                grade.setEnrollment(enrollment.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        Grade savedGrade = gradeService.save(grade);
        return ResponseEntity.ok(savedGrade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        if (!gradeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Buscar Enrollment pelo ID se fornecido
        if (grade.getEnrollmentId() != null) {
            Optional<Enrollment> enrollment = enrollmentService.findById(grade.getEnrollmentId());
            if (enrollment.isPresent()) {
                grade.setEnrollment(enrollment.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        grade.setId(id);
        return ResponseEntity.ok(gradeService.save(grade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        if (!gradeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        gradeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
