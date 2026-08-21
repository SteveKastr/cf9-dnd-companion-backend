# D&D Companion — Backend

A REST API backend for a Dungeons & Dragons 5e (2014) companion application, built as the final project for Coding Factory 9 (Athens University of Economics and Business).

Provides authenticated, role-based access to the full 5e System Reference Document (SRD) compendium — races, classes, spells, monsters, items, and more — along with user account management.

## Tech Stack

- **Java 21** with **Spring Boot 4.1**
- **MongoDB** (via Spring Data MongoDB)
- **Spring Security** with JWT (stateless authentication)
- **Docker** (for MongoDB)
- **Swagger / OpenAPI** (springdoc-openapi) for API documentation
- **Maven** for build management

## Features

- User registration and login (JWT-based authentication)
- Three roles: `ADMIN`, `GAME_MASTER`, `PLAYER`, each with different access levels
- Full SRD compendium: Races, Subraces, Traits, Classes, Subclasses, Spells, Monsters, Items, Backgrounds, Feats, Levels, Features, Rules
- Role-based visibility (e.g. only Admins/Game Masters can view Monsters and magic Items)
- Pagination, filtering, and search on larger collections (Spells, Items, Monsters)
- Admin user management (list/delete users)
- Self-service account management (view/update/delete own account)
- Centralized error handling with consistent JSON error responses

## Prerequisites

- **Java 21** or later
- **Maven** (or use the included `mvnw`/`mvnw.cmd` wrapper)
- **Docker Desktop** (for running MongoDB)

## Setup & Installation

### 1. Clone the repository

```bash
git clone <repository-url>
cd dndcompanion
```

### 2. Configure environment variables

Copy `.env.example` to `.env` and fill in your own values:

```bash
cp .env.example .env
```

```env
MONGO_USERNAME=your_mongo_username
MONGO_PASSWORD=your_mongo_password
JWT_SECRET=your_jwt_secret_at_least_32_characters
ADMIN_USERNAME=your_admin_username
ADMIN_EMAIL=admin@example.com
ADMIN_PASSWORD=your_secure_admin_password
```

**Note:** `JWT_SECRET` must be at least 32 characters long. `ADMIN_USERNAME`/`ADMIN_EMAIL`/`ADMIN_PASSWORD` define the single Admin account, which is created automatically on first startup.

In IntelliJ IDEA, set the Run Configuration to load environment variables from this `.env` file (Run → Edit Configurations → Environment variables → point to the `.env` file).

### 3. Start MongoDB via Docker

```bash
docker compose up -d
```

This starts a MongoDB 7 container on port `27017`, with data persisted in a named Docker volume.

### 4. Run the application

Using the Maven wrapper:

```bash
./mvnw spring-boot:run        # macOS/Linux
.\mvnw.cmd spring-boot:run     # Windows
```

Or run `DndcompanionApplication.java` directly from your IDE.

On first startup, the application automatically seeds the MongoDB database with the full SRD dataset (races, classes, spells, monsters, items, etc.) and creates the single Admin account. This only happens once — subsequent restarts skip seeding if data already exists.

The application will be available at `http://localhost:8080`.

## API Documentation

Once running, interactive API documentation (Swagger UI) is available at: http://localhost:8080/swagger-ui.html.

Protected endpoints can be tested directly from Swagger UI by logging in via `/api/auth/login`, copying the returned token, and clicking "Authorize" with `Bearer <token>`.

## Authentication & Authorization

- Register: `POST /api/auth/register` (role limited to `GAME_MASTER` or `PLAYER`)
- Login: `POST /api/auth/login` (returns a JWT access token)
- Include the token in subsequent requests: `Authorization: Bearer <token>`

| Resource | Public | Player | Game Master | Admin |
|---|---|---|---|---|
| Races, Classes, Spells, Backgrounds, Feats, Rules | ❌ (requires login) | ✅ | ✅ | ✅ |
| Items | ❌ | ✅ (mundane only) | ✅ (all) | ✅ (all) |
| Monsters | ❌ | ❌ | ✅ | ✅ |
| Admin user management | ❌ | ❌ | ❌ | ✅ |
| Own account (view/update/delete) | ❌ | ✅ | ✅ | ✅ (delete restricted) |

## Data Source & Attribution

This project uses data derived from the **System Reference Document 5.1 ("SRD 5.1")** by Wizards of the Coast LLC, available at https://dnd.wizards.com/resources/systems-reference-document, licensed under the [Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/legalcode).

## Project Structure

src/main/java/gr/aueb/cf9/dndcompanion/

├── config/       # Security, CORS, Swagger, pagination configuration   
├── controller/   # REST endpoints  
├── dto/          # Request/response data transfer objects  
├── exceptions/   # Custom exceptions + centralized error handling  
├── model/        # MongoDB entities (compendium data, users)   
├── repository/   # Spring Data MongoDB repositories    
├── security/     # JWT generation/validation, authentication filter    
├── seeder/       # Startup data seeders (SRD data, Admin account)  
└── service/      # Business logic


## Build & Deploy

### Build a production JAR

```bash
./mvnw clean package
```

This produces an executable JAR at `target/dndcompanion-0.0.1-SNAPSHOT.jar`.

### Run the packaged JAR

Ensure MongoDB is running (via Docker or otherwise) and the required environment variables are set, then:

```bash
java -jar target/dndcompanion-0.0.1-SNAPSHOT.jar
```

### Deployment notes

- The application reads all sensitive configuration (MongoDB credentials, JWT secret, Admin credentials) from environment variables — no secrets are hardcoded.
- CORS is currently configured to allow requests only from `http://localhost:5173` (the frontend's development URL). This should be updated in `SecurityConfig.java` if deploying to a different origin.
- MongoDB must be accessible at the URI configured in `application.properties` (`spring.mongodb.uri`).