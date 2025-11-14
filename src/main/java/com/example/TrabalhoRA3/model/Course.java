package com.example.TrabalhoRA3.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
//@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    //OneToMany com Matricul (1 curso pode ter muitas matrículas)
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference("course-enrollments")
    private List<Enrollment> enrollments;

    //ManyToOne com Instructor (muitos cursos podem ter um instrutor)
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonBackReference("instructor-courses")
    private Instructor instructor;

    // Campo auxiliar para receber o ID via JSON
    @Transient
    private Long instructorId;



    // Getter para o campo auxiliar
    @JsonProperty("instructorId")
    public Long getInstructorId() {
        return instructor != null ? instructor.getId() : instructorId;
    }
    // Setter para o campo auxiliar
    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


}
