package com.example.Escola_Back_End.Controller;

import com.example.Escola_Back_End.DTO.AlunoDTO;
import com.example.Escola_Back_End.Entity.Aluno;
import com.example.Escola_Back_End.Service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // É um componete controller por isso utilizamos essa anotação
@RequestMapping("/aluno") // O '@RequesteMapping' é a anutação que define a baseURL da API
public class AlunoController { // Classe Controller de Aluno
    @Autowired // A injeção de dependencias e metodos de forma mais facilitada da Classe 'AlunoService'
    private AlunoService alunoService; // Com a anotação acima fazemos a criação de um obj da Classe 'AlunoService' para manipulrmos de forma mais dinâmica os métodos dessa classe

    @GetMapping // Anotação do metodo 'GET' da API
    public List<Aluno> getAll(@RequestParam(required =  false)String cpf){ //

        if(cpf != null && !cpf.isEmpty()){
            return alunoService.getAllAlunosCPF(cpf);
        }else {
            return alunoService.getAll();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getById(@PathVariable Long id){
        Optional<AlunoDTO> alunoDTO = alunoService.getById(id);
        if (alunoDTO.isPresent()){
            return ResponseEntity.ok(alunoDTO.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public  ResponseEntity<AlunoDTO> create(@RequestBody AlunoDTO alunoDTO){
        AlunoDTO alunoDTOsave = alunoService.createAluno(alunoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoDTOsave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO){
        Optional<AlunoDTO> alunoDTOOptional = alunoService.updateAluno(id, alunoDTO);
        if(alunoDTOOptional.isPresent()){
            return ResponseEntity.ok(alunoDTOOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(alunoService.delete(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
