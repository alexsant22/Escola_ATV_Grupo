package com.example.Escola_Back_End.Controller;

import com.example.Escola_Back_End.Dto.ProfessorDto;
import com.example.Escola_Back_End.Entity.Professor;
import com.example.Escola_Back_End.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> getAll(@RequestParam(required = false) String nomeProfessor){
        if (nomeProfessor != null && !nomeProfessor.isEmpty()){
            return professorService.getAllByNomeProfessor(nomeProfessor);
        }
        return professorService.getAllProfessor();
    }

    @PutMapping("/{idProfessor}")
    public ResponseEntity<ProfessorDto> updateProfessor(@PathVariable Long idProfessor, @RequestBody ProfessorDto professorDto){
        Optional<ProfessorDto> professorDtoOptional = professorService.updateProfessor(idProfessor, professorDto);
         if (professorDtoOptional.isPresent()){
             return ResponseEntity.ok(professorDtoOptional.get());
         } else {
             return ResponseEntity.notFound().build();
         }
    }
}
