package com.example.TrabalhoRA3.controllers;

import com.example.TrabalhoRA3.model.Enrollment;
import com.example.TrabalhoRA3.model.Student;
import com.example.TrabalhoRA3.model.Course;
import com.example.TrabalhoRA3.services.EnrollmentService;
import com.example.TrabalhoRA3.services.StudentService;
import com.example.TrabalhoRA3.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        Optional<Enrollment> enrollment = enrollmentService.findById(id);
        return enrollment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        // Buscar Student pelo ID se fornecido
        if (enrollment.getStudentId() != null) {
            Optional<Student> student = studentService.findById(enrollment.getStudentId());
            if (student.isPresent()) {
                enrollment.setStudent(student.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        // Buscar Course pelo ID se fornecido
        if (enrollment.getCourseId() != null) {
            Optional<Course> course = courseService.findById(enrollment.getCourseId());
            if (course.isPresent()) {
                enrollment.setCourse(course.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        Enrollment savedEnrollment = enrollmentService.save(enrollment);
        return ResponseEntity.ok(savedEnrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        if (!enrollmentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Buscar Student pelo ID se fornecido
        if (enrollment.getStudentId() != null) {
            Optional<Student> student = studentService.findById(enrollment.getStudentId());
            if (student.isPresent()) {
                enrollment.setStudent(student.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        // Buscar Course pelo ID se fornecido
        if (enrollment.getCourseId() != null) {
            Optional<Course> course = courseService.findById(enrollment.getCourseId());
            if (course.isPresent()) {
                enrollment.setCourse(course.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        enrollment.setId(id);
        return ResponseEntity.ok(enrollmentService.save(enrollment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        if (!enrollmentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        enrollmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
