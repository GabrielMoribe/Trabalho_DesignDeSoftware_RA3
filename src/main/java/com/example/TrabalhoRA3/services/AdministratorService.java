package com.example.TrabalhoRA3.services;

import com.example.TrabalhoRA3.model.Administrator;
import com.example.TrabalhoRA3.model.Student;
import com.example.TrabalhoRA3.repositories.AdministratorRepository;
import com.example.TrabalhoRA3.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    public Optional<Administrator> findById(Long id) {
        return administratorRepository.findById(id);
    }

    public Administrator save(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public void deleteById(Long id) {
        administratorRepository.deleteById(id);
    }
}


