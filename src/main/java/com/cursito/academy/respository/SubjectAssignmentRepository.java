package com.cursito.academy.respository;

import com.cursito.academy.entity.SubjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectAssignmentRepository extends JpaRepository<SubjectAssignment, Long> {
    List<SubjectAssignment> findBySubjectId(Long subjectId);
    List<SubjectAssignment> findByStudentId(Long studentId);
}

