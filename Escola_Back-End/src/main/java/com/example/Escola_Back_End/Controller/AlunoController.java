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

@RestController // É um componente controller por isso utilizamos essa anotação
@RequestMapping("/aluno") // O '@RequestMapping' é a anotação que define a baseURL da API
public class AlunoController { // Classe Controller de Aluno

    @Autowired // A injeção de dependências e métodos de forma mais facilitada da Classe 'AlunoService'
    private AlunoService alunoService; // Com a anotação acima fazemos a criação de um objeto da Classe 'AlunoService' para manipulamos de forma mais dinâmica os métodos dessa classe

    @GetMapping // Anotação do método 'GET' da API
    public List<Aluno> getAll(@RequestParam(required = false) String cpf) { /* Método 'getAll' que retorna uma lista de objetos 'Aluno' | Utilizamos o '@RequestParam'
    com 'required = false' pois estamos verificando se o usuário quis buscar pelo atributo 'cpf', e esse parâmetro não é obrigatório para a busca de alunos. */

        if (cpf != null && !cpf.isEmpty()) { /* Se o atributo/parâmetro 'cpf' for DIFERENTE de 'null' e(&&) NÃO for isEmpty() (Vazio), ele retorna
        o método da classe service 'getAllAlunosCPF(cpf)' que irá buscar os alunos pelo atributo/parâmetro 'cpf'. */
            return alunoService.getAllAlunosCPF(cpf);
        } else {
            return alunoService.getAll(); /* Se não houver a busca pelo atributo/parâmetro 'cpf', será retornado o método 'getAll()' da 'service'
            que retorna uma lista de todos os objetos 'Aluno' */
        }
    }

    @GetMapping("/{id}") // Anotação do método 'GET' da API puxando o parâmetro 'id' pois será uma busca especificada por esse parâmetro
    public ResponseEntity<AlunoDTO> getById(@PathVariable Long id) { /* Método 'getById' retorna um objeto da classe 'AlunoDTO' e utiliza o método 'ResponseEntity'
    para manipular a resposta http do método | Utiliza-se o '@PathVariable' para pegar um trecho específico da URL, nesse caso o '/{id}' para utilizar como parâmetro de
    busca no método */

        Optional<AlunoDTO> alunoDTO = alunoService.getById(id); /* Utilizamos o método 'Optional<AlunoDTO>' pois podemos ou não ter algum retorno do objeto com base
        no método da 'service.getById(id)' */

        if (alunoDTO.isPresent()) { /* Se o retorno do objeto 'alunoDTO' for 'isPresent()' (Presente), será retornado o status http 'ok' juntamente com o
        objeto 'alunoDTO' (utilizando o '.ok(alunoDTO.get());' */
            return ResponseEntity.ok(alunoDTO.get());
        } else { /* Se NÃO for presente o objeto 'alunoDTO', será retornado o status http '404' (Not Found) --> (Não Encontrado) */
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping // Anotação do método 'POST' (publicar/criar) da API
    public ResponseEntity<AlunoDTO> create(@RequestBody AlunoDTO alunoDTO) { /* Método 'create' vai retornar uma response http de um objeto da classe 'AlunoDTO'
    utilizando a anotação '@RequestBody', que está enviando uma requisição 'json' de um objeto 'alunoDTO' */

        AlunoDTO alunoDTOsave = alunoService.createAluno(alunoDTO); /* Criou-se um objeto 'alunoDTOsave' da classe 'AlunoDTO' que irá receber o método
        'createAluno(alunoDTO)' da 'service', utilizando o objeto 'alunoDTO' que criará um Aluno de acordo com este método */

        return ResponseEntity.status(HttpStatus.CREATED).body(alunoDTOsave); /* No fim será retornado um status http 'CREATED' e aparecerá no corpo da requisição
        o objeto 'alunoDTOsave' criado no método anteriormente */
    }

    @PutMapping("/{id}") // Anotação do método 'PUT' (atualizar) da API, utilizando o parâmetro 'id' para atualizar um aluno específico
    public ResponseEntity<AlunoDTO> update(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) { /* Método 'update' vai retornar uma response http de um objeto da
    classe 'AlunoDTO' utilizando a anotação '@PathVariable' para pegar o trecho da URL '/{id}', e também utilizando a anotação '@RequestBody', que está enviando uma
    requisição 'json' de um objeto 'alunoDTO' */

        Optional<AlunoDTO> alunoDTOOptional = alunoService.updateAluno(id, alunoDTO); /* Utilizamos o método 'Optional<AlunoDTO>' pois podemos ou não ter algum retorno
        do objeto com base no método 'service.updateAluno(id, alunoDTO)', que está recebendo o 'id' do aluno e o objeto 'alunoDTO' */

        if (alunoDTOOptional.isPresent()) { /* Se o retorno do objeto 'alunoDTOOptional' for 'isPresent()' (Presente), será retornado o status http 'ok' juntamente com o
        objeto 'alunoDTOOptional' (utilizando o '.ok(alunoDTOOptional.get());' */
            return ResponseEntity.ok(alunoDTOOptional.get());
        } else { /* Se NÃO for presente o objeto 'alunoDTOOptional', será retornado o status http '404' (Not Found) --> (Não Encontrado) */
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") // Anotação do método 'DELETE' (deletar/excluir) da API, puxando o parâmetro 'id' pois será excluído de acordo com esse 'id'
    public ResponseEntity<Void> delete(@PathVariable Long id) { /* Método 'delete' vai retornar uma response http de acordo com os métodos de retorno.
    Utiliza-se o '@PathVariable' para pegar o trecho da URL '/{id}', a fim de fazer a busca por 'id' e exclusão do mesmo */

        if (alunoService.delete(id)) { /* Se o método 'service.delete(id)' utilizando o mesmo parâmetro de 'id' funcionar, será retornado uma
        response http '.noContent().build()' que é o statusHttp '204' (processado com sucesso, mas não possui conteúdo a ser exibido) */
            return ResponseEntity.noContent().build();
        } else { /* Se NÃO funcionar o método proposto acima, será retornado o status http '404' (Not Found) --> (Não Encontrado) */
            return ResponseEntity.notFound().build();
        }
    }
}
