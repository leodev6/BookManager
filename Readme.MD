# 📚 Library Manager Pro

> Application web full-stack de gestion de bibliothèque personnelle.
> **Stack** : Spring Boot 3 · Angular 17 · PostgreSQL 15

---

## Table des matières

1. [Aperçu](#aperçu)
2. [Architecture](#architecture)
3. [Prérequis](#prérequis)
4. [Installation rapide (Docker)](#installation-rapide-docker)
5. [Installation manuelle](#installation-manuelle)
   - [Backend](#backend-spring-boot)
   - [Frontend](#frontend-angular)
6. [Variables d'environnement](#variables-denvironnement)
7. [API Reference](#api-reference)
8. [Tests](#tests)
9. [Structure des dossiers](#structure-des-dossiers)

---

## Aperçu

Library Manager Pro permet de :

- **Gérer** votre catalogue de livres (CRUD complet)
- **Rechercher** par titre avec debounce (300 ms)
- **Filtrer** par catégorie et statut de lecture
- **Paginer** les résultats (5 / 10 / 25 par page)
- **Suivre** votre progression de lecture (tableau de bord statistiques)
- **Marquer** un livre comme lu / non lu en un clic

---

## Architecture

```
┌──────────────────────┐   REST/JSON   ┌──────────────────────────┐
│  Angular 17           │ ◄──────────► │  Spring Boot 3            │
│  TypeScript · SCSS    │   HTTP/CORS  │  Java 17 · Maven          │
└──────────────────────┘               └────────────┬─────────────┘
                                                    │ JPA/Hibernate
                                       ┌────────────▼─────────────┐
                                       │  PostgreSQL 15             │
                                       └──────────────────────────┘
```

---

## Prérequis

| Outil         | Version minimale |
|---------------|-----------------|
| Java (JDK)    | 17 LTS          |
| Maven         | 3.9+            |
| Node.js       | 20 LTS          |
| npm           | 9+              |
| Angular CLI   | 17+             |
| PostgreSQL    | 15+             |
| Docker        | 24+ *(optionnel)*|

---

## Installation rapide (Docker)

```bash
# Cloner le projet
git clone https://github.com/votre-user/library-manager-pro.git
cd library-manager-pro

# Démarrer toute la stack
docker compose up --build
```

| Service    | URL                                |
|------------|------------------------------------|
| Frontend   | http://localhost                   |
| Backend    | http://localhost:8080              |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| API docs   | http://localhost:8080/api-docs     |

---

## Installation manuelle

### Backend (Spring Boot)

```bash
cd library-backend

# 1. Créer la base de données PostgreSQL
psql -U postgres -c "CREATE DATABASE library_db;"

# 2. Configurer les credentials dans :
#    src/main/resources/application.properties
#    spring.datasource.username=postgres
#    spring.datasource.password=yourpassword

# 3. (Optionnel) Charger les données de test
psql -U postgres -d library_db -f src/main/resources/data.sql

# 4. Lancer le backend
./mvnw spring-boot:run
# → http://localhost:8080
```

### Frontend (Angular)

```bash
cd library-frontend

# 1. Installer les dépendances
npm install

# 2. Lancer le serveur de développement
#    (le proxy redirige /api → localhost:8080)
npm start
# → http://localhost:4200
```

---

## Variables d'environnement

### Backend — `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

### Frontend — `src/environments/environment.ts`

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

---

## API Reference

### Livres

| Méthode  | Endpoint                       | Description                  | Status    |
|----------|--------------------------------|------------------------------|-----------|
| `GET`    | `/api/books`                   | Liste paginée                | 200       |
| `GET`    | `/api/books/{id}`              | Détail d'un livre            | 200 / 404 |
| `POST`   | `/api/books`                   | Créer un livre               | 201       |
| `PUT`    | `/api/books/{id}`              | Modifier un livre            | 200 / 404 |
| `DELETE` | `/api/books/{id}`              | Supprimer un livre           | 204 / 404 |
| `PATCH`  | `/api/books/{id}/read`         | Toggle statut lu/non lu      | 200       |
| `GET`    | `/api/books/search?title=`     | Recherche par titre          | 200       |
| `GET`    | `/api/books/filter?category=&isRead=` | Filtres combinés      | 200       |
| `GET`    | `/api/books/stats`             | Statistiques globales        | 200       |

#### Paramètres de pagination

```
GET /api/books?page=0&size=10&sort=dateAdded,desc
```

#### Exemple de réponse paginée

```json
{
  "content": [{ "id": 1, "title": "Clean Code", ... }],
  "totalElements": 40,
  "totalPages": 4,
  "size": 10,
  "number": 0,
  "first": true,
  "last": false
}
```

#### Format d'erreur

```json
{
  "timestamp": "2026-04-11T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Livre non trouvé avec l'id : 99",
  "path": "/api/books/99"
}
```

### Catégories

| Méthode  | Endpoint              | Description            | Status    |
|----------|-----------------------|------------------------|-----------|
| `GET`    | `/api/categories`     | Liste des catégories   | 200       |
| `POST`   | `/api/categories`     | Créer une catégorie    | 201 / 409 |
| `DELETE` | `/api/categories/{id}`| Supprimer              | 204 / 404 |

---

## Tests

### Backend

```bash
cd library-backend

# Tous les tests (JUnit 5 + H2 in-memory)
./mvnw test

# Avec rapport de couverture
./mvnw verify
```

Couverture visée : **> 70 %** sur la couche service et controller.

### Frontend

```bash
cd library-frontend

# Tests unitaires en mode watch
npm test

# Tests avec couverture (rapport dans /coverage)
npm test -- --code-coverage --watch=false
```

---

## Structure des dossiers

```
library-manager-pro/
├── library-backend/
│   └── src/main/java/com/library/
│       ├── entity/          # Entités JPA (Book, Category)
│       ├── dto/             # Records de transfert (Request/Response)
│       ├── repository/      # Interfaces Spring Data JPA
│       ├── service/         # Interfaces + implémentations métier
│       ├── controller/      # REST Controllers (@RestController)
│       ├── exception/       # Exceptions custom + GlobalExceptionHandler
│       ├── mapper/          # Conversions Entity ↔ DTO
│       └── config/          # CORS, OpenAPI
│
└── library-frontend/
    └── src/app/
        ├── core/
        │   ├── models/      # Interfaces TypeScript
        │   ├── services/    # BookService, CategoryService
        │   └── interceptors/# ErrorInterceptor
        ├── features/
        │   ├── book-list/   # Liste avec filtres + pagination
        │   ├── book-form/   # Formulaire création/édition
        │   ├── book-detail/ # Page détail
        │   └── stats/       # Tableau de bord
        └── shared/
            ├── components/  # Loader, EmptyState, ConfirmDialog
            └── pipes/       # ReadStatusPipe
```

---

## Fonctionnalités bonus implémentées

- [x] Thème editorial luxury (Playfair Display + DM Sans, palette ivoire/bordeaux/or)
- [x] Layout responsive avec sidebar mobile
- [x] Donut SVG animé dans les stats
- [x] Barre de progression dégradée
- [x] Animations `fadeUp` + `stagger` sur les grilles de cartes
- [x] Proxy Angular → Spring Boot en développement
- [x] Nginx SPA-ready avec cache long terme pour la production
- [x] Docker multi-stage (build + runtime Nginx)

---

*© 2026 Library Manager Pro — Document de référence*