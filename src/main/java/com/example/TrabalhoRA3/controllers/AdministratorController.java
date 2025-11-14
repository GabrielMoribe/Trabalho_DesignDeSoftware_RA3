package com.example.TrabalhoRA3.controllers;

import com.example.TrabalhoRA3.model.Administrator;
import com.example.TrabalhoRA3.services.AdministratorService;
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
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator) {
        Administrator createdAdmin = administratorService.saveAdministrator(administrator.getName(), administrator.getRole());
        return ResponseEntity.ok(createdAdmin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdministrator(@PathVariable Long id, @RequestBody Administrator administrator) {
        if (!administratorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        administratorService.updateAdministrator(id, administrator.getName(), administrator.getRole());
        return ResponseEntity.ok().build();
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
