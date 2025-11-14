package com.example.TrabalhoRA3.controllers;

import com.example.TrabalhoRA3.model.Course;
import com.example.TrabalhoRA3.model.Instructor;
import com.example.TrabalhoRA3.services.CourseService;
import com.example.TrabalhoRA3.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.findById(id);
        return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        // Buscar Instructor pelo ID se fornecido
        if (course.getInstructorId() != null) {
            Optional<Instructor> instructor = instructorService.findById(course.getInstructorId());
            if (instructor.isPresent()) {
                course.setInstructor(instructor.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        Course savedCourse = courseService.save(course);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        if (!courseService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Buscar Instructor pelo ID se fornecido
        if (course.getInstructorId() != null) {
            Optional<Instructor> instructor = instructorService.findById(course.getInstructorId());
            if (instructor.isPresent()) {
                course.setInstructor(instructor.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        course.setId(id);
        return ResponseEntity.ok(courseService.save(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (!courseService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
