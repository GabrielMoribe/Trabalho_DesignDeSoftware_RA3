package com.example.TrabalhoRA3.services;

import com.example.TrabalhoRA3.model.Administrator;
import com.example.TrabalhoRA3.repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public List<Administrator> findAll() {
        return administratorRepository.findAllAdministrators();
    }

    public Optional<Administrator> findById(Long id) {
        return administratorRepository.findAdministratorById(id);
    }

    public Administrator saveAdministrator(String name, String role) {
        administratorRepository.insertAdministrator(name, role);
        return administratorRepository.findLastInsertedAdministrator(name, role).orElse(null);
    }

    public void updateAdministrator(Long id, String name, String role) {
        administratorRepository.updateAdministrator(id, name, role);
    }

    public void deleteById(Long id) {
        administratorRepository.deleteAdministratorById(id);
    }

    public List<Administrator> findByName(String name) {
        return administratorRepository.findByName(name);
    }

    public List<Administrator> findByRole(String role) {
        return administratorRepository.findByRole(role);
    }
}


