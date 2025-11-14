package com.example.TrabalhoRA3.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
//@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feedback;

    //ManyToOne com Enrollment (várias notas podem pertencer a uma matrícula)
    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    @JsonBackReference("enrollment-grades")
    private Enrollment enrollment;

    //Estava dando conflito com palavra reservada do SQL
    @Column(name = "grade_value")
    private Double value;

    // Campo auxiliar para receber ID via JSON
    @Transient
    @JsonProperty("enrollmentId")
    private Long enrollmentId;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    // Getters e Setters para campo auxiliar
    public Long getEnrollmentId() {
        return enrollment != null ? enrollment.getId() : enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }
}
