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
        return instructorRepository.findAll();
    }

    public Optional<Instructor> findById(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }
}
