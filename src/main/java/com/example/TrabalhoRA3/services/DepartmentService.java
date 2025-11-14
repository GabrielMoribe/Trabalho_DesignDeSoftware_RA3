package com.example.TrabalhoRA3.services;

import com.example.TrabalhoRA3.model.Department;
import com.example.TrabalhoRA3.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAllDepartments();
    }

    public Optional<Department> findById(Long id) {
        return departmentRepository.findDepartmentById(id);
    }

    public Department saveDepartment(String name) {
        departmentRepository.insertDepartment(name);
        return departmentRepository.findLastInsertedDepartment(name).orElse(null);
    }

    public void updateDepartment(Long id, String name) {
        departmentRepository.updateDepartment(id, name);
    }

    public void deleteById(Long id) {
        departmentRepository.deleteDepartmentById(id);
    }

    public List<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }
}
