# Escola

## Contexto

O sistema é baseado em um sistema de uma escola, onde um professor pode lecionar em várias turmas e as turmas contêm vários alunos. Um aluno pode estar em apenas uma turma, e uma turma pode conter apenas um professor.

## Atividade

Os alunos deverão se dividir em grupos de 3 pessoas e desenvolver um back-end utilizando como base o diagrama apresentado.  
Os alunos devem escolher entre si a divisão das atividades abaixo.

## Requisitos

1. O sistema deve conter uma conexão com banco de dados.
2. O sistema deve conter as seguintes rotas:

### 2.1 Entidade Professor

- Cadastrar  
- Buscar todos  
- Buscar com base no ID  
- Buscar com base no Nome  
- Atualizar dados  
- Deletar  

### 2.2 Entidade Aluno

- Buscar todos  
- Buscar com base no ID  
- Buscar com base no CPF  
- Atualizar dados  
- Deletar  

### 2.3 Entidade Turma

- Buscar todos  
- Buscar com base no ID  
- Buscar com base no Nome  
- Atualizar dados  
- Deletar  

### Funcionalidades adicionais

- O sistema deve permitir inserir novos alunos em uma turma.  
- O sistema deve permitir remover os alunos de uma turma.  

## Regras de Exibição

- Ao realizar uma busca da **turma**, devem aparecer os dados do seu professor e de seus alunos vinculados.  
- Ao realizar uma busca do **professor**, devem aparecer apenas os dados do professor.  
- Ao realizar uma busca do **aluno**, devem aparecer apenas os dados do aluno.
