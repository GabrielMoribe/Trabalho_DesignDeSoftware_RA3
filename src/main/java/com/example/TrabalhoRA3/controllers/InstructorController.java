package com.example.TrabalhoRA3.controllers;

import com.example.TrabalhoRA3.model.Instructor;
import com.example.TrabalhoRA3.model.Department;
import com.example.TrabalhoRA3.services.InstructorService;
import com.example.TrabalhoRA3.services.DepartmentService;
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

    @Autowired
    private DepartmentService departmentService;

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
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        // Buscar Department pelo ID se fornecido
        if (instructor.getDepartmentId() != null) {
            Optional<Department> department = departmentService.findById(instructor.getDepartmentId());
            if (department.isPresent()) {
                instructor.setDepartment(department.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        Instructor savedInstructor = instructorService.save(instructor);
        return ResponseEntity.ok(savedInstructor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        if (!instructorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Buscar Department pelo ID se fornecido
        if (instructor.getDepartmentId() != null) {
            Optional<Department> department = departmentService.findById(instructor.getDepartmentId());
            if (department.isPresent()) {
                instructor.setDepartment(department.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
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
