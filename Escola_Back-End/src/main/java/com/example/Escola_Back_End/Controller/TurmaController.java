package com.example.Escola_Back_End.Controller;

import com.example.Escola_Back_End.DTO.TurmaDTO;
import com.example.Escola_Back_End.Entity.Turma;
import com.example.Escola_Back_End.Service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    private TurmaService service;

    @GetMapping("/buscar")
    public List<Turma> getAll(@RequestParam(required = false) String nome) {
        if (nome != null && !nome.isEmpty()) {
            return service.getAllTurmasByNome(nome);
        }
        return service.getAllTurmas();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurmaDTO> getById(@PathVariable Long id) {
        Optional<TurmaDTO> optional = service.getById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/adicionar")
    public ResponseEntity<TurmaDTO> create(@RequestBody TurmaDTO turmaDTO) {
        TurmaDTO dtoSave = service.createTurma(turmaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSave);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TurmaDTO> update(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO) {
        Optional<TurmaDTO> optional = service.updateTurma(id, turmaDTO);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar o 'Aluno' na 'Turma'
    @PutMapping("/{id}/alunoAdd/{idAluno}")
    public ResponseEntity<String> addAlunoTurma(@PathVariable Long id, @PathVariable Long idAluno){
        if(service.addAlunoTurma(id, idAluno)){
            return ResponseEntity.ok("Aluno adicionado com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno ou Professor não encontrado");
        }
    }

    // remove aluno da turma
    @PutMapping("/{id}/alunoRemove/{idAluno}")
    public ResponseEntity<String> removeAlunoTurma(@PathVariable Long id, @PathVariable Long idAluno){
        if(service.removeAlunoTurma(id, idAluno)){
            return ResponseEntity.ok("Aluno removido da turma com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao remover aluno da turma");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
