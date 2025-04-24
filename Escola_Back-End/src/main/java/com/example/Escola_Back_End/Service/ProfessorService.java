package com.example.Escola_Back_End.Service;

import com.example.Escola_Back_End.DTO.ProfessorDTO;
import com.example.Escola_Back_End.Entity.Professor;
import com.example.Escola_Back_End.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Optional<ProfessorDTO> getById(Long id){
        Optional<Professor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isPresent()){
            ProfessorDTO professorDTO = new ProfessorDTO();
            return Optional.of(professorDTO.fromProfessor(professorOptional.get()));
        } else{
            return Optional.empty();
        }
    }

    public ProfessorDTO createProfessor(ProfessorDTO professorDTO){
        Professor professor = professorDTO.toProfessor();
        professor = professorRepository.save(professor);
        return professorDTO.fromProfessor(professor);
    }

    public boolean delete(Long id){
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }
}
