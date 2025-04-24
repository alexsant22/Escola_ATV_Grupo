package com.example.Escola_Back_End.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idAluno;
    private String nomeAluno;
    @Column
    private String cpf;


}
