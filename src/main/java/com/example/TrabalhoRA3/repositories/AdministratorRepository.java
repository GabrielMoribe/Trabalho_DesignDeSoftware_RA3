package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Administrator;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository // Conflitando com @Repository do Spring
public interface AdministratorRepository extends Repository<Administrator, Long> {

    @Query(value = "SELECT * FROM administrator", nativeQuery = true)
    List<Administrator> findAllAdministrators();

    @Query(value = "SELECT * FROM administrator WHERE id = :id", nativeQuery = true)
    Optional<Administrator> findAdministratorById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO administrator (name, role) VALUES (:name, :role)", nativeQuery = true)
    void insertAdministrator(@Param("name") String name, @Param("role") String role);

    @Modifying
    @Transactional
    @Query(value = "UPDATE administrator SET name = :name, role = :role WHERE id = :id", nativeQuery = true)
    void updateAdministrator(@Param("id") Long id, @Param("name") String name, @Param("role") String role);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM administrator WHERE id = :id", nativeQuery = true)
    void deleteAdministratorById(@Param("id") Long id);

    @Query("SELECT a FROM Administrator a WHERE a.name = :name")
    List<Administrator> findByName(@Param("name") String name);

    @Query("SELECT a FROM Administrator a WHERE a.role = :role")
    List<Administrator> findByRole(@Param("role") String role);

    // Query para buscar o último administrator inserido
    @Query(value = "SELECT * FROM administrator WHERE name = :name AND role = :role ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Administrator> findLastInsertedAdministrator(@Param("name") String name, @Param("role") String role);
}
