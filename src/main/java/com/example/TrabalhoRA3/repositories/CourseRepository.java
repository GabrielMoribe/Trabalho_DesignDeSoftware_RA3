package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.name = :name")
    List<Course> findByName(@Param("name") String name);

    @Query("SELECT c FROM Course c WHERE c.description LIKE %:keyword%")
    List<Course> findByDescriptionContaining(@Param("keyword") String keyword);

    @Query("DELETE FROM Course c WHERE c.id = :id")
    void deleteByIdWithQuery(@Param("id") Long id);
}
