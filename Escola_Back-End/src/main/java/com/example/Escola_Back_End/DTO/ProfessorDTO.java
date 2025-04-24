package com.example.Escola_Back_End.DTO;

import com.example.Escola_Back_End.Entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO implements Serializable {

    private Long idProfessor;
    private String nomeProfessor;
    private String sobrenome;

    public Professor toProfessor(){
        return new Professor(
                this.idProfessor,
                this.nomeProfessor,
                this.sobrenome
        );
    }

    public ProfessorDTO fromProfessor(Professor professor){
        return new ProfessorDTO(
                professor.getIdProfessor(),
                professor.getNomeProfessor(),
                professor.getSobrenome()
        );
    }
}
