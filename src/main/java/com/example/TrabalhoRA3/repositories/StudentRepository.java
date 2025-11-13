package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.name = :name")
    List<Student> findByName(@Param("name") String name);

    @Query("SELECT s FROM Student s WHERE s.email = :email")
    List<Student> findByEmail(@Param("email") String email);

    @Query("DELETE FROM Student s WHERE s.id = :id")
    void deleteByIdWithQuery(@Param("id") Long id);
}
