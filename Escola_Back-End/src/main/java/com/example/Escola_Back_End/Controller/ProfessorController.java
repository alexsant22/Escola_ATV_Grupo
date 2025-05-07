package com.example.Escola_Back_End.Controller;

import com.example.Escola_Back_End.Entity.Professor;
import com.example.Escola_Back_End.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Escola_Back_End.DTO.ProfessorDto;

import java.util.List;
import java.util.Optional;

@RestController // É um componente controller, por isso utilizamos essa anotação
@RequestMapping("/professor") // O '@RequestMapping' define a baseURL da API, que neste caso será '/professor'
public class ProfessorController { // Classe Controller de Professor

    @Autowired // Injeção de dependência da classe 'ProfessorService' para facilitar o uso dos métodos dessa classe
    private ProfessorService professorService; // Criação de um objeto da classe 'ProfessorService' para manipular seus métodos de forma mais dinâmica

    @GetMapping("/{idProfessor}") // Anotação do método 'GET' com parâmetro 'idProfessor', usado para buscar um professor específico pelo ID
    public ResponseEntity<ProfessorDto> getById(@PathVariable Long idProfessor) { /* Método 'getById' retorna um objeto da classe 'ProfessorDto' dentro
    de uma resposta HTTP utilizando 'ResponseEntity'. Usa-se o '@PathVariable' para capturar o valor do ID na URL */

        Optional<ProfessorDto> professorDTOOptional = professorService.getById(idProfessor); // Chamada ao método da service que busca o professor por ID

        if (professorDTOOptional.isPresent()) { // Se o professor for encontrado (estiver presente), retorna status HTTP 200 (OK) com o objeto no corpo
            return ResponseEntity.ok(professorDTOOptional.get());
        } else { // Caso contrário, retorna status HTTP 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping // Anotação do método 'GET' para buscar todos os professores, ou filtrar por nome
    public List<Professor> getAll(@RequestParam(required = false) String nomeProfessor) { /* Se for passado um parâmetro opcional 'nomeProfessor',
    será feita uma busca filtrada por nome, senão, retorna todos os professores */

        if (nomeProfessor != null && !nomeProfessor.isEmpty()) { // Se o nome for informado e não estiver vazio
            return professorService.getAllByNomeProfessor(nomeProfessor); // Busca os professores pelo nome informado
        }
        return professorService.getAllProfessor(); // Caso não haja filtro, retorna todos os professores
    }

    @PostMapping // Anotação do método 'POST', usado para criar um novo professor
    public ResponseEntity<ProfessorDto> create(@RequestBody ProfessorDto professorDTO) { /* Método 'create' recebe um objeto JSON via '@RequestBody',
    cria um novo professor e retorna a entidade salva com status HTTP 201 (CREATED) */

        ProfessorDto professorDTOSave = professorService.createProfessor(professorDTO); // Chama o método de criação da service
        return ResponseEntity.status(HttpStatus.CREATED).body(professorDTOSave); // Retorna a entidade criada e o status HTTP 201
    }

    @DeleteMapping("/{idProfessor}") // Anotação do método 'DELETE' com parâmetro 'idProfessor', usado para deletar um professor específico
    public ResponseEntity<Void> delete(@PathVariable Long idProfessor) { /* Método 'delete' recebe o ID de um professor via URL e tenta excluí-lo.
    Retorna status HTTP 204 (No Content) se for bem-sucedido, ou 404 se não encontrado */

        if (professorService.delete(idProfessor)) { // Se a exclusão for bem-sucedida
            return ResponseEntity.noContent().build(); // Retorna status HTTP 204 (sem conteúdo)
        } else { // Caso contrário
            return ResponseEntity.notFound().build(); // Retorna status HTTP 404 (não encontrado)
        }
    }

    @PutMapping("/{idProfessor}") // Anotação do método 'PUT' com parâmetro 'idProfessor', usado para atualizar um professor existente
    public ResponseEntity<ProfessorDto> updateProfessor(@PathVariable Long idProfessor, @RequestBody ProfessorDto professorDto) {
        /* Método 'updateProfessor' recebe o ID do professor via URL e os novos dados via corpo da requisição.
        Retorna o professor atualizado com status HTTP 200, ou 404 se não encontrado */

        Optional<ProfessorDto> professorDtoOptional = professorService.updateProfessor(idProfessor, professorDto); // Chama o método de atualização na service

        if (professorDtoOptional.isPresent()) { // Se o professor for encontrado e atualizado
            return ResponseEntity.ok(professorDtoOptional.get()); // Retorna o professor atualizado com status 200
        } else {
            return ResponseEntity.notFound().build(); // Caso não seja encontrado, retorna status 404
        }
    }
}
