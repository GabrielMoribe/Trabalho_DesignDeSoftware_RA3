package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT g FROM Grade g WHERE g.value = :value")
    List<Grade> findByValue(@Param("value") Double value);

    @Query("SELECT g FROM Grade g WHERE g.feedback LIKE %:feedback%")
    List<Grade> findByFeedbackContaining(@Param("feedback") String feedback);

    @Query("DELETE FROM Grade g WHERE g.id = :id")
    void deleteByIdWithQuery(@Param("id") Long id);
}
