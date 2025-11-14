package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Instructor;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface InstructorRepository extends Repository<Instructor, Long> {

    @Query(value = "SELECT * FROM instructor", nativeQuery = true)
    List<Instructor> findAllInstructors();

    @Query(value = "SELECT * FROM instructor WHERE id = :id", nativeQuery = true)
    Optional<Instructor> findInstructorById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO instructor (name, department_id) VALUES (:name, :departmentId)", nativeQuery = true)
    void insertInstructor(@Param("name") String name, @Param("departmentId") Long departmentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE instructor SET name = :name, department_id = :departmentId WHERE id = :id", nativeQuery = true)
    void updateInstructor(@Param("id") Long id, @Param("name") String name, @Param("departmentId") Long departmentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM instructor WHERE id = :id", nativeQuery = true)
    void deleteInstructorById(@Param("id") Long id);

    @Query("SELECT i FROM Instructor i WHERE i.department.id = :departmentId")
    List<Instructor> findByDepartmentId(@Param("departmentId") Long departmentId);

    @Query("SELECT i FROM Instructor i WHERE i.name = :name")
    List<Instructor> findByName(@Param("name") String name);

    // Query para buscar o último instructor inserido
    @Query(value = "SELECT * FROM instructor WHERE name = :name ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Instructor> findLastInsertedInstructor(@Param("name") String name);
}
