package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    @Query("SELECT a FROM Administrator a WHERE a.name = :name")
    List<Administrator> findByName(@Param("name") String name);

    @Query("SELECT a FROM Administrator a WHERE a.role = :role")
    List<Administrator> findByRole(@Param("role") String role);

    @Query("DELETE FROM Administrator a WHERE a.id = :id")
    void deleteByIdWithQuery(@Param("id") Long id);
}
