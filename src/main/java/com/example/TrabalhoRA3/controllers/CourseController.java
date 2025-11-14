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
        // Verificar se instructor existe se fornecido
        if (course.getInstructorId() != null) {
            Optional<Instructor> instructor = instructorService.findById(course.getInstructorId());
            if (!instructor.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
        }

        Course createdCourse = courseService.saveCourse(course.getName(), course.getDescription(), course.getInstructorId());
        return ResponseEntity.ok(createdCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        if (!courseService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Verificar se instructor existe se fornecido
        if (course.getInstructorId() != null) {
            Optional<Instructor> instructor = instructorService.findById(course.getInstructorId());
            if (!instructor.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
        }

        courseService.updateCourse(id, course.getName(), course.getDescription(), course.getInstructorId());
        return ResponseEntity.ok().build();
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
