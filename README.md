# 💙💜 BlueAndPurple – Arquitetura de Microserviços

Projeto backend desenvolvido com **arquitetura de microserviços**, simulando um ambiente real de sistemas distribuídos, comunicação assíncrona com **Kafka**, persistência em **PostgreSQL**, geração de documentos e orquestração com **Docker**.

Este projeto foi criado com foco em **boas práticas**, **escalabilidade** e **organização de serviços independentes**, sendo ideal para estudos avançados e portfólio profissional.

---

## 🧱 Arquitetura do Projeto

O sistema é composto por múltiplos microserviços independentes, cada um com sua própria responsabilidade, comunicando-se de forma desacoplada.

### 📦 Microserviços

* **orders**
  Responsável pelo gerenciamento de pedidos.

* **invoicing**
  Responsável pelo faturamento dos pedidos e geração de nota fiscal.

* **bucket**
  Responsável pelo armazenamento de arquivos (ex: notas fiscais).

* **broker**
  Infraestrutura de mensageria utilizando **Apache Kafka**.

* **gateway** *(se aplicável)*
  Centraliza e gerencia o acesso aos serviços.

---

## 🔁 Comunicação entre os serviços

* Comunicação **assíncrona** utilizando **Apache Kafka**
* Eventos publicados e consumidos entre os serviços
* Redução de acoplamento e maior escalabilidade

---

## 🐳 Executando o projeto com Docker

### 📋 Pré-requisitos

* Docker
* Docker Compose

### ▶️ Subindo todos os microserviços

Na raiz do projeto, execute:

```bash
docker compose up -d
```

Isso irá:

* Subir todos os microserviços
* Subir banco de dados PostgreSQL
* Subir Kafka e Zookeeper

### 📄 Ver logs dos serviços

```bash
docker compose logs -f
```

---

## 🗂 Estrutura do Projeto

```text
blue-and-purple/
│
├── services/
│   ├── orders/
│   ├── invoicing/
│   ├── bucket/
│   ├── broker/
│   └── gateway/
│
├── docker-compose.yml
├── .gitignore
└── README.md
```

---

## 🛠 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **Apache Kafka**
* **PostgreSQL**
* **Docker & Docker Compose**
* **Maven**
* **Lombok**
* **Jackson**

---

## 🎯 Objetivos do Projeto

* Aplicar arquitetura de microserviços na prática
* Trabalhar com mensageria assíncrona
* Simular fluxo real de pedidos e faturamento
* Utilizar Docker para padronização de ambiente
* Desenvolver um projeto alinhado ao mercado

---

## 👩‍💻 Desenvolvedora

**Maria Déborah Gomides Silva**
Desenvolvedora Backend | Java | Spring Boot | Microserviços

🔗 GitHub: [https://github.com/mariadeb28](https://github.com/mariadeb28)

---

✨ Projeto desenvolvido para fins de estudo, aprendizado e portfólio profissional.
