package com.example.Escola_Back_End.Repository;

import com.example.Escola_Back_End.Entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
