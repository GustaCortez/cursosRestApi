package com.cursito.academy.controller;

import com.cursito.academy.entity.Subject;
import com.cursito.academy.respository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    // Obtener todos los sujetos
    @GetMapping("/")
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // Obtener un sujeto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable(value = "id") Long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isPresent()) {
            return ResponseEntity.ok().body(subject.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo sujeto
    @PostMapping("/")
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    // Actualizar un sujeto
    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable(value = "id") Long subjectId,
                                                 @RequestBody Subject subjectDetails) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isPresent()) {
            Subject updatedSubject = subject.get();
            updatedSubject.setName(subjectDetails.getName());
            updatedSubject.setProfessorName(subjectDetails.getProfessorName());
            return ResponseEntity.ok(subjectRepository.save(updatedSubject));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un sujeto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable(value = "id") Long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isPresent()) {
            subjectRepository.delete(subject.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
