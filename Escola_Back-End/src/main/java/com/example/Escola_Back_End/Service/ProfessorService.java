package com.example.Escola_Back_End.Service;

import com.example.Escola_Back_End.Dto.ProfessorDto;
import com.example.Escola_Back_End.Entity.Professor;
import com.example.Escola_Back_End.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;


    //buscar todos os professores
    public List<Professor> getAllProfessor(){
        return professorRepository.findAll();
    }



    //buscar os professores pelo nome
    public List<Professor> getAllByNome(String nomeProfessor){
        return professorRepository.findAllByNome(nomeProfessor);
    }


    //atualizar profressor
    public Optional<ProfessorDto> updateProfessor(Long idProfessor, ProfessorDto professorDto){
        Optional<Professor> professorOptional = professorRepository.findById(idProfessor);
        if (professorOptional.isPresent()){
            Professor professor = professorOptional.get();
            professor.setNomeProfessor(professorDto.getNomeProfessor());
            professor.setSobrenome(professorDto.getSobrenome());

            professor = professorRepository.save(professor);

            return Optional.of(professorDto.fromProfessor(professor));
        } else {
            return Optional.empty();
        }
    }
}
