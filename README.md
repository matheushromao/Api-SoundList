# 🎵 SoundList API

API REST para gerenciamento de **playlists** e **músicas**, construída com **Spring Boot** e **MySQL**. Permite criar playlists, cadastrar músicas associadas a elas e realizar operações completas de CRUD com paginação, validação de dados e tratamento centralizado de erros.

---

## 📋 Índice

- [Sobre o projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Modelo de dados](#-modelo-de-dados)
- [Pré-requisitos](#-pré-requisitos)
- [Configuração](#-configuração)
- [Como executar](#-como-executar)
- [Endpoints da API](#-endpoints-da-api)
- [Exemplos de requisição](#-exemplos-de-requisição)
- [Tratamento de erros](#-tratamento-de-erros)
- [Estrutura de pastas](#-estrutura-de-pastas)

---

## 📖 Sobre o projeto

O **SoundList** é uma API back-end que organiza músicas dentro de playlists. Cada playlist pode conter diversas músicas, e cada música pertence obrigatoriamente a uma playlist (relacionamento **One-to-Many**). A aplicação segue uma arquitetura em camadas (Controller → Service → Repository) com uso de DTOs e Mappers para isolar a camada de persistência da camada de exposição.

---

## 🛠 Tecnologias

| Tecnologia | Versão |
|------------|--------|
| Java | 25 |
| Spring Boot | 4.1.0 |
| Spring Web MVC | — |
| Spring Data JPA | — |
| Spring Validation (Bean Validation) | — |
| MySQL | 8+ |
| Lombok | — |
| Maven | (wrapper incluído) |

---

## 🏗 Arquitetura

A aplicação está organizada em camadas bem definidas:

```
Controller  →  recebe as requisições HTTP e expõe os endpoints REST
Service     →  contém as regras de negócio
Mapper      →  converte entre Entidades e DTOs
Repository  →  acesso a dados (Spring Data JPA)
Model       →  entidades JPA mapeadas para o banco
DTO         →  objetos de transferência (request/response)
Exception   →  tratamento global de erros (@ControllerAdvice)
```

---

## 🗃 Modelo de dados

### Playlist
| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | Long | Identificador único (auto-incremento) |
| `name` | String | Nome da playlist (obrigatório) |
| `description` | String | Descrição da playlist |
| `musicas` | List\<Musica\> | Músicas associadas |

### Musica
| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | Long | Identificador único (auto-incremento) |
| `title` | String | Título da música (obrigatório) |
| `artist` | String | Nome do artista (obrigatório) |
| `genre` | String | Gênero musical (obrigatório) |
| `duration` | Integer | Duração em segundos (obrigatório, > 0) |
| `playlistId` | Long | Playlist à qual pertence (obrigatório) |

> Relacionamento: uma **Playlist** possui muitas **Músicas** (`@OneToMany` / `@ManyToOne`), com `cascade = ALL` e `orphanRemoval = true`.

---

## ✅ Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- [Java JDK 25](https://www.oracle.com/java/technologies/downloads/)
- [MySQL 8+](https://dev.mysql.com/downloads/)
- [Maven](https://maven.apache.org/) *(opcional — o projeto inclui o Maven Wrapper)*

---

## ⚙ Configuração

As configurações ficam em `src/main/resources/application.yaml`:

```yaml
spring:
  application:
    name: SoundList
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/soundlist-db?createDatabaseIfNotExist=true
    username: root
    password: sua-senha
  jpa:
    hibernate:
      ddl-auto: update
    generate-ddl: true
    show-sql: true
```

> ⚠️ **Importante:** ajuste `username` e `password` de acordo com a sua instalação do MySQL. O banco `soundlist-db` é criado automaticamente caso não exista (`createDatabaseIfNotExist=true`).
>
> 💡 Recomenda-se mover as credenciais para variáveis de ambiente antes de subir o projeto para produção ou versionar publicamente.

---

## ▶ Como executar

Clone o repositório:

```bash
git clone https://github.com/matheushromao/SoundList.git
cd SoundList
```

Execute com o Maven Wrapper:

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

A aplicação ficará disponível em:

```
http://localhost:8080
```

---

## 📡 Endpoints da API

### 🎧 Playlists — `/playlists`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/playlists` | Lista todas as playlists (paginado) |
| `GET` | `/playlists/{id}` | Busca uma playlist por ID |
| `POST` | `/playlists` | Cria uma nova playlist |
| `PUT` | `/playlists/{id}` | Atualiza uma playlist existente |
| `DELETE` | `/playlists/{id}` | Remove uma playlist |

### 🎵 Músicas — `/musics`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/musics` | Lista todas as músicas (paginado) |
| `GET` | `/musics/{id}` | Busca uma música por ID |
| `POST` | `/musics` | Cria uma nova música |
| `PUT` | `/musics/{id}` | Atualiza uma música existente |
| `DELETE` | `/musics/{id}` | Remove uma música |

> 📄 **Paginação:** os endpoints `GET` de listagem aceitam os parâmetros `page`, `size` e `sort`.
> Padrões: `size=10`, ordenado por `name` (playlists) ou `title` (músicas), em ordem ascendente.
> Exemplo: `GET /musics?page=0&size=20&sort=artist,desc`

---

## 📤 Exemplos de requisição

### Criar uma playlist

```http
POST /playlists
Content-Type: application/json
```
```json
{
  "name": "Rock Clássico",
  "description": "As melhores do rock dos anos 70 e 80"
}
```

**Resposta `200 OK`:**
```json
{
  "id": 1,
  "name": "Rock Clássico",
  "description": "As melhores do rock dos anos 70 e 80",
  "musicas": []
}
```

### Criar uma música

```http
POST /musics
Content-Type: application/json
```
```json
{
  "title": "Stairway to Heaven",
  "artist": "Led Zeppelin",
  "genre": "Rock",
  "duration": 482,
  "playlistId": 1
}
```

**Resposta `200 OK`:**
```json
{
  "id": 1,
  "title": "Stairway to Heaven",
  "artist": "Led Zeppelin",
  "genre": "Rock",
  "duration": 482,
  "playlistId": 1
}
```

---

## 🚨 Tratamento de erros

A API possui um handler global (`@ControllerAdvice`) que padroniza as respostas de erro:

| Status | Quando ocorre |
|--------|---------------|
| `400 Bad Request` | Dados inválidos na requisição (falha de validação) |
| `404 Not Found` | Recurso (playlist ou música) não encontrado |
| `500 Internal Server Error` | Erro inesperado no servidor |

**Exemplo de resposta de erro de validação (`400`):**
```json
{
  "status": "400",
  "errors": {
    "title": "O título não pode ser vazio!",
    "duration": "Duração deve ser maior que zero"
  },
  "timestamp": "2026-06-15T14:30:00.123"
}
```

---

## 📁 Estrutura de pastas

```
src/main/java/com/api_soundlist/SoundList
├── controller      # Endpoints REST (Musica, Playlist)
├── service         # Regras de negócio
├── repository      # Interfaces Spring Data JPA
├── model           # Entidades JPA (Musica, Playlist)
├── dto             # Request/Response DTOs
├── mapper          # Conversão entre entidades e DTOs
├── exception       # Tratamento global de exceções
└── SoundListApplication.java   # Classe principal
```

---

## 👤 Autor

**Matheus Romão**
[GitHub](https://github.com/matheushromao)

---

<p align="center">Feito com ☕ e 🎵</p>
