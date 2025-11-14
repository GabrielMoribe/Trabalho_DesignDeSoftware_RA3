package com.example.TrabalhoRA3.repositories;

import com.example.TrabalhoRA3.model.Enrollment;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface EnrollmentRepository extends Repository<Enrollment, Long> {

    @Query(value = "SELECT * FROM enrollment", nativeQuery = true)
    List<Enrollment> findAllEnrollments();

    @Query(value = "SELECT * FROM enrollment WHERE id = :id", nativeQuery = true)
    Optional<Enrollment> findEnrollmentById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO enrollment (student_id, course_id, enrollment_date) VALUES (:studentId, :courseId, :enrollmentDate)", nativeQuery = true)
    void insertEnrollment(@Param("studentId") Long studentId, @Param("courseId") Long courseId, @Param("enrollmentDate") LocalDate enrollmentDate);

    @Modifying
    @Transactional
    @Query(value = "UPDATE enrollment SET student_id = :studentId, course_id = :courseId, enrollment_date = :enrollmentDate WHERE id = :id", nativeQuery = true)
    void updateEnrollment(@Param("id") Long id, @Param("studentId") Long studentId, @Param("courseId") Long courseId, @Param("enrollmentDate") LocalDate enrollmentDate);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM enrollment WHERE id = :id", nativeQuery = true)
    void deleteEnrollmentById(@Param("id") Long id);

    @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId")
    List<Enrollment> findByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT e FROM Enrollment e WHERE e.course.id = :courseId")
    List<Enrollment> findByCourseId(@Param("courseId") Long courseId);

    // Query para buscar o último enrollment inserido
    @Query(value = "SELECT * FROM enrollment WHERE student_id = :studentId AND course_id = :courseId ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<Enrollment> findLastInsertedEnrollment(@Param("studentId") Long studentId, @Param("courseId") Long courseId);
}
