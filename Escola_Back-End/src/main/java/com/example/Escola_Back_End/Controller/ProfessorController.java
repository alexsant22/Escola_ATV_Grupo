package com.example.Escola_Back_End.Controller;

import com.example.Escola_Back_End.DTO.ProfessorDTO;
import com.example.Escola_Back_End.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;


    @GetMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable Long idProfessor){
        Optional<ProfessorDTO> professorDTOOptional = professorService.getById(idProfessor);
        if (professorDTOOptional.isPresent()){
            return ResponseEntity.ok(professorDTOOptional.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDTO professorDTO){
        ProfessorDTO professorDTOSave = professorService.createProfessor(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorDTOSave);
    }

    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<Void> delete(@PathVariable Long idProfessor){
        if (professorService.delete(idProfessor)){
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
