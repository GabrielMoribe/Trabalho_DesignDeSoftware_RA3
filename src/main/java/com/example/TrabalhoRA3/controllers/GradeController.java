package com.example.TrabalhoRA3.controllers;

import com.example.TrabalhoRA3.model.Grade;
import com.example.TrabalhoRA3.services.GradeService;
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
    public Grade createGrade(@RequestBody Grade grade) {
        return gradeService.save(grade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        if (!gradeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
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
