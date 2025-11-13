package com.example.TrabalhoRA3.controllers;

import com.example.TrabalhoRA3.model.Administrator;
import com.example.TrabalhoRA3.model.Department;
import com.example.TrabalhoRA3.services.AdministratorService;
import com.example.TrabalhoRA3.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping
    public List<Administrator> getAllAdministrators() {
        return administratorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getAdministratorById(@PathVariable Long id) {
        Optional<Administrator> administrator = administratorService.findById(id);
        return administrator.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Administrator createAdministrator(@RequestBody Administrator administrator) {
        return administratorService.save(administrator);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Administrator> updateAdministrator(@PathVariable Long id, @RequestBody Administrator administrator) {
        if (!administratorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        administrator.setId(id);
        return ResponseEntity.ok(administratorService.save(administrator));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Long id) {
        if (!administratorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        administratorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
