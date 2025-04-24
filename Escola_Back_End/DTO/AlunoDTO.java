package com.example.Escola_Back_End.DTO;

import com.example.Escola_Back_End.Entity.Aluno;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO implements Serializable {

    private String nomeAluno;
    @Column(unique = true)
    private String cpf;
    private Long idAluno;

    public Aluno toAluno(){
               return new Aluno(
                       this.idAluno,
                       this.nomeAluno,
                       this.cpf

               );
    }

    public AlunoDTO fromAluno(Aluno aluno){
        return new AlunoDTO(
                aluno.getNomeAluno(),
                aluno.getCpf(),
                aluno.getIdAluno()

        );
    }

}
