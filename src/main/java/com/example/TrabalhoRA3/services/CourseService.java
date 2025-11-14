package com.example.TrabalhoRA3.services;

import com.example.TrabalhoRA3.model.Course;
import com.example.TrabalhoRA3.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAllCourses();
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findCourseById(id);
    }

    public Course saveCourse(String name, String description, Long instructorId) {
        courseRepository.insertCourse(name, description, instructorId);
        return courseRepository.findLastInsertedCourse(name, description).orElse(null);
    }

    public void updateCourse(Long id, String name, String description, Long instructorId) {
        courseRepository.updateCourse(id, name, description, instructorId);
    }

    public void deleteById(Long id) {
        courseRepository.deleteCourseById(id);
    }

    public List<Course> findByInstructorId(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    public List<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }
}
