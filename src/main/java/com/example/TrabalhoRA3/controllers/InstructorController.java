package com.example.TrabalhoRA3.controllers;

import com.example.TrabalhoRA3.model.Instructor;
import com.example.TrabalhoRA3.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        Optional<Instructor> instructor = instructorService.findById(id);
        return instructor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return instructorService.save(instructor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        if (!instructorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        instructor.setId(id);
        return ResponseEntity.ok(instructorService.save(instructor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        if (!instructorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        instructorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
