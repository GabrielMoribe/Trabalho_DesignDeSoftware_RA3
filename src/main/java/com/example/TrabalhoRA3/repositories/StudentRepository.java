package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Student;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface StudentRepository extends Repository<Student, Long> {

    @Query(value = "SELECT * FROM student", nativeQuery = true)
    List<Student> findAllStudents();

    @Query(value = "SELECT * FROM student WHERE id = :id", nativeQuery = true)
    Optional<Student> findStudentById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO student (name, email) VALUES (:name, :email)", nativeQuery = true)
    void insertStudent(@Param("name") String name, @Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE student SET name = :name, email = :email WHERE id = :id", nativeQuery = true)
    void updateStudent(@Param("id") Long id, @Param("name") String name, @Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM student WHERE id = :id", nativeQuery = true)
    void deleteStudentById(@Param("id") Long id);

    @Query("SELECT s FROM Student s WHERE s.name = :name")
    List<Student> findByName(@Param("name") String name);

    @Query("SELECT s FROM Student s WHERE s.email = :email")
    Student findByEmail(@Param("email") String email);

    // Query para buscar o último student inserido
    @Query(value = "SELECT * FROM student WHERE name = :name AND email = :email ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Student> findLastInsertedStudent(@Param("name") String name, @Param("email") String email);
}
