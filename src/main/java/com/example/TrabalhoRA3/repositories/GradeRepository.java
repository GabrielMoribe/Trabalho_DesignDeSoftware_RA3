package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Grade;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface GradeRepository extends Repository<Grade, Long> {

    @Query(value = "SELECT * FROM grade", nativeQuery = true)
    List<Grade> findAllGrades();

    @Query(value = "SELECT * FROM grade WHERE id = :id", nativeQuery = true)
    Optional<Grade> findGradeById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO grade (enrollment_id, grade_value, feedback) VALUES (:enrollmentId, :value, :feedback)", nativeQuery = true)
    void insertGrade(@Param("enrollmentId") Long enrollmentId, @Param("value") Double value, @Param("feedback") String feedback);

    @Modifying
    @Transactional
    @Query(value = "UPDATE grade SET enrollment_id = :enrollmentId, grade_value = :value, feedback = :feedback WHERE id = :id", nativeQuery = true)
    void updateGrade(@Param("id") Long id, @Param("enrollmentId") Long enrollmentId, @Param("value") Double value, @Param("feedback") String feedback);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM grade WHERE id = :id", nativeQuery = true)
    void deleteGradeById(@Param("id") Long id);

    @Query("SELECT g FROM Grade g WHERE g.enrollment.id = :enrollmentId")
    List<Grade> findByEnrollmentId(@Param("enrollmentId") Long enrollmentId);

    @Query("SELECT g FROM Grade g WHERE g.value >= :minValue")
    List<Grade> findByValueGreaterThanEqual(@Param("minValue") Double minValue);

    // Query para buscar o último grade inserido
    @Query(value = "SELECT * FROM grade WHERE enrollment_id = :enrollmentId AND grade_value = :value ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Grade> findLastInsertedGrade(@Param("enrollmentId") Long enrollmentId, @Param("value") Double value);
}
