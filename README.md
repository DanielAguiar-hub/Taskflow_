# TaskFlow — API REST de Gerenciamento de Tarefas

API REST desenvolvida com Java e Spring Boot para cadastrar, consultar, atualizar e excluir tarefas.

O projeto foi desenvolvido como parte dos meus estudos de desenvolvimento backend Java, com foco em construção de APIs REST, persistência de dados e organização em camadas.

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Bean Validation
- JUnit e Mockito

## Funcionalidades

- Criar tarefas.
- Listar todas as tarefas.
- Consultar uma tarefa por ID.
- Atualizar uma tarefa.
- Excluir uma tarefa.
- Validar os dados recebidos nas requisições.
- Tratar erros de validação.
- Retornar erro 404 quando uma tarefa não é encontrada.
- Testar a camada de serviço.

## Endpoints

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/tarefas` | Criar uma tarefa |
| GET | `/tarefas` | Listar tarefas |
| GET | `/tarefas/{id}` | Buscar tarefa por ID |
| PUT | `/tarefas/{id}` | Atualizar tarefa |
| DELETE | `/tarefas/{id}` | Excluir tarefa |

### Exemplo de requisição

Para criar uma tarefa, envie um `POST` para `/tarefas` com o seguinte JSON:

```json
{
  "titulo": "Estudar Spring Boot",
  "concluida": false
}
```

## Como executar o projeto

### Pré-requisitos

- JDK compatível com a versão Java configurada no projeto.
- PostgreSQL.
- Maven, ou utilização do Maven Wrapper incluído no repositório.

### Configuração

1. Crie um banco de dados PostgreSQL para o projeto.
2. Configure a conexão com o banco usando suas próprias credenciais locais.
3. Defina as propriedades de conexão em `application.properties`, que não deve ser publicado com senhas reais.
4. Execute a aplicação pelo IntelliJ ou pelo Maven Wrapper.

No Windows, você pode executar os testes com:

```bash
.\mvnw.cmd test
```

## Estrutura do projeto

- `controller`: recebe e responde às requisições HTTP.
- `service`: contém a lógica da aplicação.
- `repository`: realiza o acesso aos dados com Spring Data JPA.
- `domain`: contém a entidade `Tarefa`.
- `dto`: define os objetos usados na entrada e saída da API.
- `exception`: centraliza o tratamento de erros.

## Objetivo

Projeto de portfólio para praticar desenvolvimento backend com Java, Spring Boot, APIs REST, banco de dados relacional e testes automatizados.