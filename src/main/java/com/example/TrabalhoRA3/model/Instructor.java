package com.example.TrabalhoRA3.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
//@Data
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //OneToMany com Course (1 professor pode ter muitos cursos)
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonManagedReference("instructor-courses")
    private List<Course> courses;

    //ManyToOne com Department (muitos professores podem pertencer a um departamento)
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference("department-instructors")
    private Department department;

    // Campo auxiliar para receber o ID via JSON
    @Transient
    private Long departmentId;


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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    // Getter para o campo auxiliar
    @JsonProperty("departmentId")
    public Long getDepartmentId() {
        return department != null ? department.getId() : departmentId;
    }

    // Setter para o campo auxiliar
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
