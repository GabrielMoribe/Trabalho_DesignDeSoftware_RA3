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
        // Verificar se Enrollment existe
        if (grade.getEnrollmentId() != null) {
            Optional<Enrollment> enrollment = enrollmentService.findById(grade.getEnrollmentId());
            if (!enrollment.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
        }

        Grade createdGrade = gradeService.saveGrade(grade.getEnrollmentId(), grade.getValue(), grade.getFeedback());
        return ResponseEntity.ok(createdGrade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        if (!gradeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Verificar se Enrollment existe
        if (grade.getEnrollmentId() != null) {
            Optional<Enrollment> enrollment = enrollmentService.findById(grade.getEnrollmentId());
            if (!enrollment.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
        }

        gradeService.updateGrade(id, grade.getEnrollmentId(), grade.getValue(), grade.getFeedback());
        return ResponseEntity.ok().build();
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
