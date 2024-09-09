# Projeto CRUD em Java com PostgreSQL

## Descrição 📝

Este projeto é uma aplicação simples em Java que realiza operações CRUD (Create, Read, Update, Delete) em um banco de dados PostgreSQL. A aplicação permite que os usuários sejam listados, inseridos, atualizados e excluídos de uma tabela no banco de dados.

## Estrutura do Projeto 🏗️

### 1. **User** 👤
Classe que representa um usuário com os seguintes atributos:
- `id`: Identificador único do usuário
- `nome`: Nome do usuário
- `cidade`: Cidade onde o usuário reside
- `telefone`: Número de telefone do usuário

### 2. **UserDAO** 🔧
Classe responsável pelas operações CRUD na tabela `pessoa` do banco de dados. Contém os seguintes métodos:
- `inserir(User user)`: Insere um novo usuário na tabela.
- `listar()`: Lista todos os usuários presentes na tabela.
- `atualizar(User user)`: Atualiza as informações de um usuário existente.
- `excluir(int id)`: Remove um usuário da tabela baseado no ID.

### 3. **App** 🖥️
Classe principal que contém o menu e gerencia a interação com o usuário. Permite ao usuário escolher entre listar, inserir, atualizar, excluir usuários e sair do programa.
