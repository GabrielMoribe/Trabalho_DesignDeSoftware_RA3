package com.example.TrabalhoRA3.services;

import com.example.TrabalhoRA3.model.Instructor;
import com.example.TrabalhoRA3.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> findAll() {
        return instructorRepository.findAllInstructors();
    }

    public Optional<Instructor> findById(Long id) {
        return instructorRepository.findInstructorById(id);
    }

    public Instructor saveInstructor(String name, Long departmentId) {
        instructorRepository.insertInstructor(name, departmentId);
        return instructorRepository.findLastInsertedInstructor(name).orElse(null);
    }

    public void updateInstructor(Long id, String name, Long departmentId) {
        instructorRepository.updateInstructor(id, name, departmentId);
    }

    public void deleteById(Long id) {
        instructorRepository.deleteInstructorById(id);
    }

    public List<Instructor> findByDepartmentId(Long departmentId) {
        return instructorRepository.findByDepartmentId(departmentId);
    }

    public List<Instructor> findByName(String name) {
        return instructorRepository.findByName(name);
    }
}
