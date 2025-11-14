package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Department;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface DepartmentRepository extends Repository<Department, Long> {

    @Query(value = "SELECT * FROM department", nativeQuery = true)
    List<Department> findAllDepartments();

    @Query(value = "SELECT * FROM department WHERE id = :id", nativeQuery = true)
    Optional<Department> findDepartmentById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO department (name) VALUES (:name)", nativeQuery = true)
    void insertDepartment(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE department SET name = :name WHERE id = :id", nativeQuery = true)
    void updateDepartment(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM department WHERE id = :id", nativeQuery = true)
    void deleteDepartmentById(@Param("id") Long id);

    @Query("SELECT d FROM Department d WHERE d.name = :name")
    List<Department> findByName(@Param("name") String name);

    // Query para buscar o último department inserido
    @Query(value = "SELECT * FROM department WHERE name = :name ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Department> findLastInsertedDepartment(@Param("name") String name);
}
