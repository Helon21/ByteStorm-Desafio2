# API - Gestão de Cursos

API para gestão de cursos, criada para o desafio 2.

### 📋 Pré-requisitos

É necessário ter a JDK 17+ em sua máquina para rodar a API.
Postman para testar os endpoints.

## 🛠️ Construído com

* [Java 17](https://www.java.com/pt-BR/) - Linguagem de programação usada para criar a API.
* [Springboot](https://spring.io) - Framework usado em conjunto com o Java.
* [Maven](https://maven.apache.org/) - Gerenciador de Dependências.
* [H2 database](https://www.h2database.com/html/main.html) - Banco de dados em memória.

## ✒️ Autores

* **Matheus Laurentino** - *desenvolvedor backend* - [desenvolvedor](https://github.com/MathLaurentino).
* **Helon Xavier** - *desenvolvedor backend* - [desenvolvedor](https://github.com/Helon21).

## Instalação

1. Clone este repositório:
```
  git clone https://github.com/Helon21/ByteStorm-Desafio2.git
```

2. Navegue até o diretório do projeto:
```
cd ByteStorm-Desafio2
```

3. Abra-o no IntelliJ. No menu "Project Structure", certifique-se de selecionar o
   SDK e o nível de linguagem Java 17.


4. Instale as dependências usando o Maven.


5. Após a instalação, inicie o servidor. Ele estará disponível em:
```
  http://localhost:8080/
```

6. Se você possui o Postman instalado, importe o arquivo
   "API-Cursos.postman_collection.json" que contém todas as rotas da API.


7. Acesse a documentação do Swagger através da seguinte URL:
```
  http://localhost:8080/docs-curso.html
```


# Micro Serviço de Gerenciamento de Alunos

## Funcionalidades 

1. Cadastrar curso;
2. Inabilitar curso;
3. Alterar professor de um curso já cadastrado;

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
