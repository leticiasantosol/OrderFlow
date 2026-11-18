# OrderFlow API

API REST para gerenciamento e processamento de pedidos, desenvolvida em Java com Spring Boot, aplicando conceitos de Programação Orientada a Objetos e Design Patterns.

## Sobre o projeto

O OrderFlow é uma aplicação desenvolvida como parte do desafio de Design Patterns. Seu objetivo é demonstrar a aplicação prática de padrões de projeto em um sistema de gerenciamento de pedidos.

A API permite cadastrar produtos, criar pedidos, validar informações, calcular fretes e atualizar automaticamente o estoque dos produtos.

## Tecnologias utilizadas

- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- Maven
- Banco de dados H2
- Bean Validation
- Spring Web

## Funcionalidades

- Cadastro e consulta de produtos.
- Busca de produtos por ID.
- Exclusão de produtos.
- Criação de pedidos com múltiplos itens.
- Validação dos pedidos.
- Verificação de disponibilidade em estoque.
- Cálculo automático de frete.
- Atualização do estoque após a criação do pedido.
- Consulta de pedidos.

## Design Patterns aplicados

### Chain of Responsibility

Utilizado no processo de validação dos pedidos. Cada validação é executada de forma independente e sequencial.

Implementações:
- ItemValidation
- StockValidation
- TotalValueValidation
- OrderValidationHandler

### Strategy

Utilizado para implementar diferentes formas de cálculo de frete, permitindo selecionar a estratégia conforme a modalidade de entrega.

Implementações:
- ShippingStrategy
- StandardShippingStrategy
- ExpressShippingStrategy

### Factory

Utilizado para selecionar e fornecer a estratégia de entrega correspondente ao tipo de frete solicitado.

Implementação:
- ShippingStrategyFactory

## Endpoints principais

| Método | Endpoint | Descrição |
|---|---|---|
| POST | /api/products | Cadastrar produto |
| GET | /api/products | Listar produtos |
| GET | /api/products/{id} | Buscar produto |
| DELETE | /api/products/{id} | Excluir produto |
| POST | /api/orders | Criar pedido |
| GET | /api/orders | Listar pedidos |
| GET | /api/orders/{id} | Buscar pedido |

## Como executar o projeto

1. Clone o repositório.
2. Abra o projeto em uma IDE compatível com Java.
3. Certifique-se de ter o Java 21 instalado.
4. Aguarde o Maven baixar as dependências.
5. Execute a classe `OrderflowApplication`.

A aplicação será iniciada em:

`http://localhost:8080`

## Banco de dados

O projeto utiliza o H2 Database em memória.

Console do banco:

`http://localhost:8080/h2-console`

Configurações:
- JDBC URL: `jdbc:h2:mem:orderflow`
- User: `sa`
- Password: deixar em branco

## Objetivo acadêmico

Este projeto foi desenvolvido com o propósito de consolidar conhecimentos sobre Design Patterns, demonstrando como esses padrões podem contribuir para uma arquitetura mais organizada, flexível e de fácil manutenção.