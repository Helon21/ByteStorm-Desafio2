# API - Gestão de Cursos

API para gestão de cursos, criada para o desafio 2.

### 📋 Pré-requisitos

É necessário ter a JDK 17+ em sua máquina para rodar a API.

## 🛠️ Construído com

* [Java 17](https://www.java.com/pt-BR/) - Linguagem de programação usada para criar a API.
* [Springboot](https://spring.io) - Framework usado em conjunto com o Java.
* [Maven](https://maven.apache.org/) - Gerenciador de Dependências.
* [H2 database](https://www.h2database.com/html/main.html) - Banco de dados em memória.

## ✒️ Autores

* **Matheus Laurentino** - *desenvolvedor backend* - [desenvolvedor](https://github.com/MathLaurentino).
* **Helon Xavier** - *desenvolvedor backend* - [desenvolvedor](https://github.com/Helon21).

# Micro Serviço de Gerenciamento de Alunos

## Funcionalidades 

1. Cadastrar alunos;
2. Matricular alunos a determinados cursos;
3. Inativar a matrícula de um aluno a curso;
4. Consultar alunos matriculados em um curso;
5. Inativar um aluno.

## Documentação da API

### Lista dos tipos enumerados para criação de curso

```
ENGENHARIA_SOFTWARE, FISICA, MEDICINA
```

### Cursos

#### Cadastrar um novo curso

```http
  POST /api/v1/cursos/cadastrar
```
```
{ 
    "nome": "Teoria da Computação",
    "quantidadeHoras": 10,
    "professor": "Girafales",
    "areaConhecimento": "ENGENHARIA_SOFTWARE",
    "ativo": true (opcional, pois já é definido como true automáticamente ao criar um curso)
}
```

#### Buscar cursos por id

```http
  GET /api/v1/cursos/buscar-curso-id/{id}
```
retorno:
```
{
    "nome": "Teoria da Computação",
    "quantidadeHoras": 10,
    "professor": "Girafales",
    "areaConhecimento": "ENGENHARIA_SOFTWARE",
    "ativo": true
}
```

### Buscar todos os cursos

```http
  GET /api/v1/cursos/buscar-cursos
```
retorno:
```
{
    "nome": "Teoria da Computação",
    "quantidadeHoras": 10,
    "professor": "Girafales",
    "areaConhecimento": "ENGENHARIA_SOFTWARE",
    "ativo": true
}
```

#### Alterar Professor

```http
  PATCH /api/v1/cursos/alterar-professor/{id}
{
    "professor":"Seu Madruga"
}
```
retorno:
```
{
    "nome": "Teoria da Computação",
    "quantidadeHoras": 10,
    "professor": "Seu Madruga",
    "areaConhecimento": "ENGENHARIA_SOFTWARE",
    "ativo": true
}
```

### Inabilitar Curso

```http
  PATCH /api/v1/inabilitar-curso/{id}
```
retorno:
```
{
    "nome": "Teoria da Computação",
    "quantidadeHoras": 10,
    "professor": "Girafales",
    "areaConhecimento": "ENGENHARIA_SOFTWARE",
    "ativo": false
}
