# Trabalho RA3 - Design de Software

## Descrição do Projeto
Este projeto foi desenvolvido como um projeto acadêmico para a disciplina de Design de Software. Ele implementa um sistema de gerenciamento acadêmico completo com as seguintes funcionalidades:

- Cadastro de estudantes, cursos, matrículas, professores, departamentos, notas e administradores
- Operações CRUD completas para todas as entidades
- Arquitetura em 3 camadas: apresentação, negócio e dados
- Queries SQL customizadas para todas as operações de banco de dados
- Relacionamentos bidirecionais entre entidades
- Banco de dados H2 em memória para facilitar o desenvolvimento

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Hibernate**
- **H2 Database** (banco em memória)

## Estrutura do Projeto

O projeto segue a arquitetura em 3 camadas:

- **Camada de Apresentação**: Controladores REST (`@RestController`) para expor os endpoints
- **Camada de Negócio**: Serviços (`@Service`) para encapsular a lógica de negócios
- **Camada de Dados**: Repositórios (`@Repository`) com queries SQL customizadas

## Entidades e Relacionamentos

### Diagrama de Classes Simplificado

```
┌─────────────────┐    1:N    ┌─────────────────┐    1:N    ┌─────────────────┐
│   Department    │◄──────────┤   Instructor    │◄──────────┤     Course      │
├─────────────────┤           ├─────────────────┤           ├─────────────────┤
│ - id: Long      │           │ - id: Long      │           │ - id: Long      │
│ - name: String  │           │ - name: String  │           │ - name: String  │
└─────────────────┘           │ - departmentId  │           │ - description   │
                              └─────────────────┘           │ - instructorId  │
                                                            └─────────────────┘
                                                                     │
                                                                     │ 1:N
                                                                     ▼
┌─────────────────┐    1:N    ┌─────────────────┐    1:N    ┌─────────────────┐
│    Student      │◄──────────┤   Enrollment    │◄──────────┤     Grade       │
├─────────────────┤           ├─────────────────┤           ├─────────────────┤
│ - id: Long      │           │ - id: Long      │           │ - id: Long      │
│ - name: String  │           │ - studentId     │           │ - value: Double │
│ - email: String │           │ - courseId      │           │ - feedback      │
└─────────────────┘           │ - enrollmentDate│           │ - enrollmentId  │
                              └─────────────────┘           └─────────────────┘

┌─────────────────┐
│  Administrator  │
├─────────────────┤
│ - id: Long      │
│ - name: String  │
│ - role: String  │
└─────────────────┘
```

### Relacionamentos:
- **Department** 1:N **Instructor** (Um departamento tem muitos instrutores)
- **Instructor** 1:N **Course** (Um instrutor leciona muitos cursos)
- **Student** 1:N **Enrollment** (Um estudante pode ter muitas matrículas)
- **Course** 1:N **Enrollment** (Um curso pode ter muitos estudantes matriculados)
- **Enrollment** 1:N **Grade** (Uma matrícula pode ter muitas notas)
- **Administrator** (Entidade independente para gerenciamento do sistema)

## Endpoints da API

### 1. Departments
- `GET /departments` - Lista todos os departamentos
- `GET /departments/{id}` - Busca departamento por ID
- `POST /departments` - Cria novo departamento
- `PUT /departments/{id}` - Atualiza departamento
- `DELETE /departments/{id}` - Remove departamento

### 2. Instructors
- `GET /instructors` - Lista todos os instrutores
- `GET /instructors/{id}` - Busca instrutor por ID
- `POST /instructors` - Cria novo instrutor
- `PUT /instructors/{id}` - Atualiza instrutor
- `DELETE /instructors/{id}` - Remove instrutor

### 3. Courses
- `GET /courses` - Lista todos os cursos
- `GET /courses/{id}` - Busca curso por ID
- `POST /courses` - Cria novo curso
- `PUT /courses/{id}` - Atualiza curso
- `DELETE /courses/{id}` - Remove curso

### 4. Students
- `GET /students` - Lista todos os estudantes
- `GET /students/{id}` - Busca estudante por ID
- `POST /students` - Cria novo estudante
- `PUT /students/{id}` - Atualiza estudante
- `DELETE /students/{id}` - Remove estudante

### 5. Enrollments
- `GET /enrollments` - Lista todas as matrículas
- `GET /enrollments/{id}` - Busca matrícula por ID
- `POST /enrollments` - Cria nova matrícula
- `PUT /enrollments/{id}` - Atualiza matrícula
- `DELETE /enrollments/{id}` - Remove matrícula

### 6. Grades
- `GET /grades` - Lista todas as notas
- `GET /grades/{id}` - Busca nota por ID
- `POST /grades` - Cria nova nota
- `PUT /grades/{id}` - Atualiza nota
- `DELETE /grades/{id}` - Remove nota

### 7. Administrators
- `GET /administrators` - Lista todos os administradores
- `GET /administrators/{id}` - Busca administrador por ID
- `POST /administrators` - Cria novo administrador
- `PUT /administrators/{id}` - Atualiza administrador
- `DELETE /administrators/{id}` - Remove administrador

## Ordem Recomendada para Criação das Entidades

Para respeitar os relacionamentos entre as entidades, siga esta ordem:

### 1º - Departamento
### 2º - Instrutor
### 3º - Curso
### 4º - Estudante
### 5º - Matrícula
### 6º - Nota
### 7º - Administrador (Independente)


### Banco de Dados H2
- Banco em memória para desenvolvimento
- Interface web disponível em: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (vazio)
