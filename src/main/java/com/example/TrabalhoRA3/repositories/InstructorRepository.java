package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    @Query("SELECT i FROM Instructor i WHERE i.name = :name")
    List<Instructor> findByName(@Param("name") String name);

    @Query("SELECT i FROM Instructor i WHERE i.expertise LIKE %:expertise%")
    List<Instructor> findByExpertiseContaining(@Param("expertise") String expertise);

    @Query("DELETE FROM Instructor i WHERE i.id = :id")
    void deleteByIdWithQuery(@Param("id") Long id);
}
