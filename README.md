# MagicFridgeAI-Java10x

**MagicFridgeAI** é um projeto desenvolvido como parte do curso **Java 10x**. O sistema utiliza a **API Gemini** para sugerir receitas baseadas nos itens cadastrados pelo usuário no banco de dados. 

## Índice

- [Descrição do Projeto](#descrição-do-projeto)
- [Objetivo](#objetivo)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Rodando o Projeto](#rodando-o-projeto)
- [Contribuições](#contribuições)

## Descrição do Projeto

O **MagicFridgeAI** é um sistema inteligente de gerenciamento de alimentos que utiliza a **API Gemini** para sugerir receitas baseadas nos ingredientes cadastrados pelo usuário. O sistema armazena os alimentos no banco de dados, onde o usuário pode cadastrar os itens presentes em sua geladeira ou despensa. A partir disso, o sistema usa a API Gemini para gerar sugestões de receitas, ajudando a otimizar o uso dos ingredientes e reduzir desperdícios.

### Funcionalidades

- **Cadastro de alimentos**: O usuário pode adicionar os itens presentes em sua geladeira ou despensa ao banco de dados.
- **Sugestão de receitas**: Através da API Gemini, o sistema sugere receitas baseadas nos ingredientes cadastrados.
- **Integração com a API Gemini**: A API Gemini é utilizada para gerar receitas com base na lista de itens fornecidos.

## Objetivo

O principal objetivo deste projeto é permitir que o usuário, com base nos ingredientes que possui em estoque, receba sugestões de receitas de maneira automatizada. Além disso, o sistema visa otimizar o uso dos alimentos e reduzir desperdícios, ao sugerir receitas viáveis com os ingredientes disponíveis.

## Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias e dependências:

- **Java 17**: A versão do Java utilizada para o desenvolvimento.
- **Spring Boot 3.5.6**: Framework para desenvolvimento de aplicações Java baseadas em microserviços.
  - **spring-boot-starter-web**: Para desenvolvimento de APIs RESTful.
  - **spring-boot-starter-data-jpa**: Para interação com bancos de dados usando JPA (Java Persistence API).
  - **spring-boot-starter-webflux**: Para suporte a APIs reativas.
  - **spring-boot-devtools**: Para facilitar o desenvolvimento com recarregamento automático.
  - **spring-boot-starter-test**: Para realizar testes unitários e de integração.
- **Google Gemini (google-genai)**: Utilizado para geração de receitas através da API **Google GenAI**.
- **H2 Database**: Banco de dados em memória para testes (pode ser substituído por um banco de dados real como MySQL ou PostgreSQL).
- **Flyway**: Para gerenciamento de migrações de banco de dados.
- **Lombok**: Para simplificar o código, gerando automaticamente getters, setters, e construtores.
- **Reactor-Test**: Ferramentas para teste de código reativo, utilizadas com o **spring-boot-starter-webflux**.

## Dependências e Plugins do Maven

- **Maven Compiler Plugin**: Para compilar o código-fonte e garantir compatibilidade com a versão do Java.
- **Spring Boot Maven Plugin**: Para empacotar e executar o aplicativo Spring Boot.

Essas dependências e tecnologias ajudam a criar um sistema robusto, eficiente e fácil de manter.


## Pré-requisitos

Antes de rodar o projeto, você precisará ter as seguintes ferramentas instaladas:

- **Java 10** ou superior
- **Maven** (para gerenciar dependências e executar o projeto)
- **MySQL** (ou outro banco de dados compatível)
- **API Gemini** (para sugestões de receitas)
- **IDE** (opcional, mas recomendado para desenvolvimento):
  - [IntelliJ IDEA](https://www.jetbrains.com/idea/) 
  - [Eclipse](https://www.eclipse.org/)

## Instalação

1. **Clone o repositório**:
    ```bash
    git clone https://github.com/seu-usuario/MagicFridgeAI-Java10x.git
    ```

2. **Instale as dependências**:
    Navegue até o diretório do projeto e execute o Maven para baixar as dependências:
    ```bash
    cd MagicFridgeAI-Java10x
    mvn clean install
    ```

3. **Configuração do Banco de Dados**:
    - Crie um banco de dados MySQL (ou outro banco de dados compatível).
    - Configure o arquivo `application.properties` dentro da pasta `src/main/resources` com os dados do seu banco de dados:
    
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/magicfridgeai
    spring.datasource.username=root
    spring.datasource.password=senha_do_banco
    ```

4. **Configuração da API Gemini**:
    - Você precisará de uma chave de API para a **API Gemini**. Registre-se na plataforma Gemini para obter sua chave de API.
    - Adicione a chave de API no arquivo `application.properties`:
    
    ```properties
    gemini.api.key=SuaChaveAPI
    ```



## Rodando o Projeto

1. **Compilando e Executando**:
    Após configurar o banco de dados e as dependências, você pode rodar o projeto usando o seguinte comando:
    ```bash
    mvn spring-boot:run
    ```

2. **Acessando o Sistema**:
    Após rodar o projeto, a aplicação estará disponível em `http://localhost:8080`. A partir dessa URL, você poderá interagir com o sistema, cadastrar alimentos, verificar validade e receber sugestões de receitas.

## Contribuições

Contribuições são bem-vindas! Se você deseja melhorar o projeto, siga os seguintes passos:

1. Faça um **fork** deste repositório.
2. Crie uma **branch** com a sua feature (`git checkout -b feature/nome-da-feature`).
3. Faça as modificações necessárias.
4. Submeta um **pull request** com uma descrição clara do que foi alterado.




