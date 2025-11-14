package com.example.TrabalhoRA3.services;

import com.example.TrabalhoRA3.model.Student;
import com.example.TrabalhoRA3.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAllStudents();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findStudentById(id);
    }

    public Student saveStudent(String name, String email) {
        studentRepository.insertStudent(name, email);
        return studentRepository.findLastInsertedStudent(name, email).orElse(null);
    }

    public void updateStudent(Long id, String name, String email) {
        studentRepository.updateStudent(id, name, email);
    }

    public void deleteById(Long id) {
        studentRepository.deleteStudentById(id);
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}