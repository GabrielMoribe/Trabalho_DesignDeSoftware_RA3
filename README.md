# Trabalho RA3 - Design de Software

## Descrição do Projeto
Este projeto foi desenvolvido como parte da avaliação somativa do RA3 para a disciplina de Design de Software. Ele implementa um sistema de gerenciamento acadêmico com as seguintes funcionalidades:

- Cadastro de estudantes, cursos, matrículas, instrutores, departamentos, notas e administradores.
- Operações CRUD para todas as entidades.
- Arquitetura em 3 camadas: apresentação, negócio e dados.
- Utilização de JPA com Hibernate para mapeamento objeto-relacional.

## Estrutura do Projeto
O projeto segue a seguinte estrutura:

- **Camada de Apresentação**: Controladores REST para expor os endpoints.
- **Camada de Negócio**: Serviços para encapsular a lógica de negócios.
- **Camada de Dados**: Repositórios para acesso ao banco de dados.

## Endpoints
Abaixo estão os endpoints disponíveis para cada entidade:

### Estudantes (`/students`)
- **GET /students**: Retorna todos os estudantes.
- **GET /students/{id}**: Retorna um estudante pelo ID.
- **POST /students**: Cria um novo estudante.
- **PUT /students/{id}**: Atualiza um estudante existente.
- **DELETE /students/{id}**: Remove um estudante pelo ID.

### Cursos (`/courses`)
- **GET /courses**: Retorna todos os cursos.
- **GET /courses/{id}**: Retorna um curso pelo ID.
- **POST /courses**: Cria um novo curso.
- **PUT /courses/{id}**: Atualiza um curso existente.
- **DELETE /courses/{id}**: Remove um curso pelo ID.

### Matrículas (`/enrollments`)
- **GET /enrollments**: Retorna todas as matrículas.
- **GET /enrollments/{id}**: Retorna uma matrícula pelo ID.
- **POST /enrollments**: Cria uma nova matrícula.
- **PUT /enrollments/{id}**: Atualiza uma matrícula existente.
- **DELETE /enrollments/{id}**: Remove uma matrícula pelo ID.

### Instrutores (`/instructors`)
- **GET /instructors**: Retorna todos os instrutores.
- **GET /instructors/{id}**: Retorna um instrutor pelo ID.
- **POST /instructors**: Cria um novo instrutor.
- **PUT /instructors/{id}**: Atualiza um instrutor existente.
- **DELETE /instructors/{id}**: Remove um instrutor pelo ID.

### Departamentos (`/departments`)
- **GET /departments**: Retorna todos os departamentos.
- **GET /departments/{id}**: Retorna um departamento pelo ID.
- **POST /departments**: Cria um novo departamento.
- **PUT /departments/{id}**: Atualiza um departamento existente.
- **DELETE /departments/{id}**: Remove um departamento pelo ID.

### Notas (`/grades`)
- **GET /grades**: Retorna todas as notas.
- **GET /grades/{id}**: Retorna uma nota pelo ID.
- **POST /grades**: Cria uma nova nota.
- **PUT /grades/{id}**: Atualiza uma nota existente.
- **DELETE /grades/{id}**: Remove uma nota pelo ID.

### Administradores (`/administrators`)
- **GET /administrators**: Retorna todos os administradores.
- **GET /administrators/{id}**: Retorna um administrador pelo ID.
- **POST /administrators**: Cria um novo administrador.
- **PUT /administrators/{id}**: Atualiza um administrador existente.
- **DELETE /administrators/{id}**: Remove um administrador pelo ID.

## Diagrama de Classes
O diagrama de classes abaixo representa as entidades e seus relacionamentos:

```plaintext
+----------------+       +----------------+       +----------------+
|   Student      |       |   Enrollment   |       |    Course      |
+----------------+       +----------------+       +----------------+
| - id: Long     |<------| - id: Long     |------>| - id: Long     |
| - name: String |       | - enrollmentDate|      | - name: String |
| - email: String|       |                |       | - description: |
+----------------+       +----------------+       +----------------+

+----------------+       +----------------+       +----------------+
|   Instructor   |       |   Department   |       |     Grade      |
+----------------+       +----------------+       +----------------+
| - id: Long     |<------| - id: Long     |       | - id: Long     |
| - name: String |       | - name: String |       | - value: Double|
| - expertise:   |       |                |       | - feedback:    |
+----------------+       +----------------+       +----------------+

+----------------+
| Administrator  |
+----------------+
| - id: Long     |
| - name: String |
| - role: String |
+----------------+
```

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **JPA com Hibernate**
- **Banco de Dados Relacional**

## Como Executar o Projeto
1. Clone o repositório.
2. Configure o banco de dados no arquivo `application.properties`.
3. Execute o comando `./mvnw spring-boot:run`.
4. Acesse os endpoints via Postman ou navegador.


