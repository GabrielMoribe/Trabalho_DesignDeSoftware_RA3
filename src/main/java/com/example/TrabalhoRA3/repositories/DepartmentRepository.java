package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT d FROM Department d WHERE d.name = :name")
    List<Department> findByName(@Param("name") String name);

    @Query("DELETE FROM Department d WHERE d.id = :id")
    void deleteByIdWithQuery(@Param("id") Long id);
}
