package com.cursito.academy.controller;

import com.cursito.academy.entity.SubjectAssignment;
import com.cursito.academy.respository.SubjectAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subject-assignment")
public class SubjectAssignmentController {
    @Autowired
    private SubjectAssignmentRepository subjectAssignmentRepository;

    // Obtener todas las asignaciones de sujetos
    @GetMapping("/")
    public List<SubjectAssignment> getAllSubjectAssignments() {
        return subjectAssignmentRepository.findAll();
    }

    // Obtener una asignación de sujeto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<SubjectAssignment> getSubjectAssignmentById(@PathVariable(value = "id") Long subjectAssignmentId) {
        Optional<SubjectAssignment> subjectAssignment = subjectAssignmentRepository.findById(subjectAssignmentId);
        if (subjectAssignment.isPresent()) {
            return ResponseEntity.ok().body(subjectAssignment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener todas las asignaciones de sujetos para un sujeto dado
    @GetMapping("/subject/{subjectId}")
    public List<SubjectAssignment> getSubjectAssignmentsBySubject(@PathVariable(value = "subjectId") Long subjectId) {
        return subjectAssignmentRepository.findBySubjectId(subjectId);
    }

    // Obtener todas las asignaciones de sujetos para un estudiante dado
    @GetMapping("/student/{studentId}")
    public List<SubjectAssignment> getSubjectAssignmentsByStudent(@PathVariable(value = "studentId") Long studentId) {
        return subjectAssignmentRepository.findByStudentId(studentId);
    }

    // Crear una nueva asignación de sujeto
    @PostMapping("/")
    public SubjectAssignment createSubjectAssignment(@RequestBody SubjectAssignment subjectAssignment) {
        subjectAssignment.setCreatedAt(LocalDateTime.now());
        return subjectAssignmentRepository.save(subjectAssignment);
    }

    // Actualizar una asignación de sujeto
    @PutMapping("/{id}")
    public ResponseEntity<SubjectAssignment> updateSubjectAssignment(@PathVariable(value = "id") Long subjectAssignmentId,
                                                                     @RequestBody SubjectAssignment subjectAssignmentDetails) {
        Optional<SubjectAssignment> subjectAssignment = subjectAssignmentRepository.findById(subjectAssignmentId);
        if (subjectAssignment.isPresent()) {
            SubjectAssignment updatedSubjectAssignment = subjectAssignment.get();
            updatedSubjectAssignment.setSubject(subjectAssignmentDetails.getSubject());
            updatedSubjectAssignment.setStudent(subjectAssignmentDetails.getStudent());
            return ResponseEntity.ok(subjectAssignmentRepository.save(updatedSubjectAssignment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una asignación de sujeto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubjectAssignment(@PathVariable(value = "id") Long subjectAssignmentId) {
        Optional<SubjectAssignment> subjectAssignment = subjectAssignmentRepository.findById(subjectAssignmentId);
        if (subjectAssignment.isPresent()) {
            subjectAssignmentRepository.delete(subjectAssignment.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
