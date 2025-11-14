package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Course;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CourseRepository extends Repository<Course, Long> {

    @Query(value = "SELECT * FROM course", nativeQuery = true)
    List<Course> findAllCourses();

    @Query(value = "SELECT * FROM course WHERE id = :id", nativeQuery = true)
    Optional<Course> findCourseById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO course (name, description, instructor_id) VALUES (:name, :description, :instructorId)", nativeQuery = true)
    void insertCourse(@Param("name") String name, @Param("description") String description, @Param("instructorId") Long instructorId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE course SET name = :name, description = :description, instructor_id = :instructorId WHERE id = :id", nativeQuery = true)
    void updateCourse(@Param("id") Long id, @Param("name") String name, @Param("description") String description, @Param("instructorId") Long instructorId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM course WHERE id = :id", nativeQuery = true)
    void deleteCourseById(@Param("id") Long id);

    @Query("SELECT c FROM Course c WHERE c.instructor.id = :instructorId")
    List<Course> findByInstructorId(@Param("instructorId") Long instructorId);

    @Query("SELECT c FROM Course c WHERE c.name = :name")
    List<Course> findByName(@Param("name") String name);

    // Query para buscar o último course inserido
    @Query(value = "SELECT * FROM course WHERE name = :name AND description = :description ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Course> findLastInsertedCourse(@Param("name") String name, @Param("description") String description);
}
