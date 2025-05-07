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

@RestController // É um componente controller por isso utilizamos essa anotação
@RequestMapping("/turma") // O '@RequesteMapping' é a anotação que define a baseURL da API
public class TurmaController { // Classe Controller de Turma
    @Autowired // A injeção de dependencias e metodos de forma mais facilitada da Classe 'TurmaService'
    private TurmaService service; // Com a anotação acima fazemos a criação de um objeto da Classe 'TurmaService' para manipulrmos de forma mais dinâmica os métodos dessa classe

    @GetMapping("/buscar") // Anotação do metodo 'GET'(buscar/pegar) da API
    public List<Turma> getAll(@RequestParam(required = false) String nome) { /* Método 'getAll' que retorna uma lista de objetos 'Turma' | Utilizamos o '@RequestParam'
    no 'false' pois estamos verificando se o usuário quis buscar pelo atributo nome e esse parametro não é obrigatório para a busca de turmas. */

        if (nome != null && !nome.isEmpty()) { /* Se o atributo/parametro 'nome' for DIFERENTE de 'null'(nulo) e(&&) NÃO for isEmpty() (Vazio) ele retorna
        o método da classe service 'getAllTurmasByNome(nome)' que irá buscar a turma pelo atributo/parametro 'nome'. */
            return service.getAllTurmasByNome(nome);
        }
        return service.getAllTurmas(); /* Se não houver a busca pelo atributo/parametro 'nome' será retornado o método 'getAllTurmas()' da 'service' q
        retorna uma lista de todos os objetos 'Turma' */
    }

    @GetMapping("/buscar/{id}")// Anotação do metodo 'GET'(buscar/pegar) da API, mas puxando o parametro 'id' pois será uma busca especificada por um parametro
    public ResponseEntity<TurmaDTO> getById(@PathVariable Long id) { /* Método 'getById' Retorna um objeto da classe 'TurmaDTO' e utliza-se do método 'ResponseEntity'
    para manipular a resposta http do método | Utiliza-se o '@PathVariable' para pegar um trecho especifico da URL, nesse casos o '/{id}' para utlizar como parametro de
    de busca no método */

        Optional<TurmaDTO> optional = service.getById(id); /* Utilizamos o método 'Optional<TurmaDTO>', pq podemos ou não ter algum retorno do objeto com base
        o método da 'service.getById(id)'  */

        if (optional.isPresent()) { /* Se o retorno do objeto 'optional' SER 'isPresent()' (Presente) será retornado o status http 'ok' juntamente com o
        objeto 'optional' (utilizando o '.ok(optional.get());' */
            return ResponseEntity.ok(optional.get());

        } else { /* Se NÃO for Presente o objeto 'optional' será retornado o status http '404' (Not Found) --> (Não Encontrado) */
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/adicionar") /* Anotação do método 'POST'(publicar/criar) da API */
    public ResponseEntity<TurmaDTO> create(@RequestBody TurmaDTO turmaDTO) { /* Método 'create' vai retornar uma response http de um objeto da classe 'TumaDTO'
    utilizando a anotação '@RequestBody', que é está enviando uma reqsição 'json' de um objeto 'turmaDTO' */

        TurmaDTO dtoSave = service.createTurma(turmaDTO); /* Criou-se um objeto 'dtoSave' da classe 'TurmaDTO' que irá receber o método 'createTurma(turmaDTO)' da
        'service', utilizando o objeto 'turmaDTO' que criará uma Turma de acordo com este método */

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoSave); /* No fim será retornado um status http 'CREATED' e aparecera no corpo da requisição o objeto 'dtoSave'
        criado no método anteriormente */
    }

    @PutMapping("/atualizar/{id}") /* Anotação do método 'PUT'(atualizar) da API, mas puxando o parametro 'id' pois será uma atualização de acordo com o 'id' da turma */
    public ResponseEntity<TurmaDTO> update(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO) { /* Método 'update' vai retornar uma response http de um objeto da
    classe 'TumaDTO' utilizando a anotação '@PathVariable' para pegar o trecho da URL '/{id}', e tambem utilizando a anotação '@RequestBody', que é está enviando uma
    reqsição 'json' de um objeto 'turmaDTO' */

        Optional<TurmaDTO> optional = service.updateTurma(id, turmaDTO); /* Utilizamos o método 'Optional<TurmaDTO>', pq podemos ou não ter algum retorno do objeto com base
        o método 'service.updateTurma(id, turmaDTO)', que está recebendo o 'id' da turma e o objeto 'turmaDTO' */

        if (optional.isPresent()) { /* Se o retorno do objeto 'optional' SER 'isPresent()' (Presente) será retornado o status http 'ok' juntamente com o
        objeto 'optional' (utilizando o '.ok(optional.get());' */
            return ResponseEntity.ok(optional.get());

        } else { /* Se NÃO for Presente o objeto 'optional' será retornado o status http '404' (Not Found) --> (Não Encontrado) */
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar o 'Aluno' na 'Turma'
    @PutMapping("/{id}/alunoAdd/{idAluno}") /* Anotação do método 'PUT'(atualizar) da API, mas puxando o parametro 'id' pois será uma atualização de acordo com o 'id' da turma
    + o 'idAluno' do aluno a ser adicionado à aquela turma */

    public ResponseEntity<String> addAlunoTurma(@PathVariable Long id, @PathVariable Long idAluno){ /* Método 'addAlunoTurma' vai retornar uma response http de uma String de
    acordo com o StatusHttp | utilizando a anotação '@PathVariable' duas vezes para pegar o trecho da URL '/{id}'(id da turma) e o '/{idAluno}'(id do aluno a ser adicionado) */

        if(service.addAlunoTurma(id, idAluno)){ /* Se no método 'service.addAlunoTurma(id, idAluno)' retornar 'true' para a atualização/adição do aluno na turma, será
        retornado junto ao StatusHttp '.ok()' a mensagem no 'body' --> "Aluno adicionado com sucesso" */
            return ResponseEntity.ok("Aluno adicionado com sucesso");

        }else { /* Se NÃO retornar 'true' do método 'service.addAlunoTurma(id, idAluno)', será retornado o StatusHttp 'NOT_FOUND'(404), junto com a mensagem no
        'body' --> "Aluno ou Professor não encontrado" */
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno ou Professor não encontrado");
        }
    }

    // Remove 'Aluno' da 'Turma'
    @PutMapping("/{id}/alunoRemove/{idAluno}") /* Anotação do método 'PUT'(atualizar) da API, mas puxando o parametro 'id' pois será uma atualização de acordo com o 'id' da
    turma + o 'idAluno' do aluno a ser removido daquela turma */
    public ResponseEntity<String> removeAlunoTurma(@PathVariable Long id, @PathVariable Long idAluno){ /* Método 'removeAlunoTurma' vai retornar uma response http de uma
    String de acordo com o StatusHttp | utilizando a anotação '@PathVariable' duas vezes para pegar o trecho da URL '/{id}'(id da turma) e o '/{idAluno}'(id do aluno a
    ser removido) */

        if(service.removeAlunoTurma(id, idAluno)){ /* Se no método 'service.removeAlunoTurma(id, idAluno)' retornar 'true' para a atualização/remoção do aluno na turma, será
        retornado junto ao StatusHttp '.ok()' a mensagem no 'body' --> "Aluno removido da turma com sucesso" */
            return ResponseEntity.ok("Aluno removido da turma com sucesso");

        }else { /* Se NÃO retornar 'true' do método 'service.removeAlunoTurma(id, idAluno)', será retornado o StatusHttp 'NOT_FOUND'(404), junto com a mensagem no
        'body' --> "Erro ao remover aluno da turma" */
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao remover aluno da turma");
        }
    }

    @DeleteMapping("/deletar/{id}") /* Anotação do método 'DELETE'(deletar/excluir) da API, mas puxando o parametro 'id' pois será exluido de acordo com o 'id' */
    public ResponseEntity<Void> delete(@PathVariable Long id) { /* Método 'delete' vai retornar uma response http de acordo com os métodos de retorno. | Utiliza-se
    o '@PathVariable' para pegar o trecho da URL '/{id}', afim de fazer a busca por 'id' e exclusão do mesmo */

        if (service.delete(id)) { /* Se o método 'service.delete(id)' utilizando o mesmo parametro de 'id' para o método de 'delete' funcionar, será retornado uma
        response http '.noContent().build()' que é o statusHttp '204' que significa que foi processado com sucesso, mas não possui conteúdo a ser exibido */
            return ResponseEntity.noContent().build();

        } else { /* Se NÃO funcionar o método proposto acima, será retornado o status http '404' (Not Found) --> (Não Encontrado) */
            return ResponseEntity.notFound().build();
        }
    }
}
