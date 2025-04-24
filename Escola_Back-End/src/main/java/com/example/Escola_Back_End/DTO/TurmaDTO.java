package com.example.Escola_Back_End.DTO;

import com.example.Escola_Back_End.Entity.Turma;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDTO implements Serializable {
    // Atributos
    private Long idTurma;
    private String sigla;
    private int numeroSala;
    private String nomeSala;

    public Turma toTurma() {
        return new Turma(
                this.idTurma,
                this.sigla,
                this.numeroSala,
                this.nomeSala
        );
    }

    public TurmaDTO fromTurma(Turma turma) {
        return new TurmaDTO(
                turma.getIdTurma(),
                turma.getSigla(),
                turma.getNumeroSala(),
                turma.getNomeSala()
        );
    }
}
