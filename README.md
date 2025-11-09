# Nutrition Calculator API

An in-progress API platform for nutrition tracking and meal management, built with Java 17 and the Spring Framework.

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Build Tool:** Maven
- **Database:** H2 (development), PostgreSQL (planned for production)
- **Authentication:** JWT (JSON Web Tokens)
- **Containerization:** Docker & Docker Compose
- **CI/CD:** GitHub Actions
- **Deployment:** DigitalOcean Droplet

## Features

### ✅ Implemented

- **Authentication**
  - User registration and login
  - JWT token generation and validation
  - Secure password hashing

- **Food Management**
  - CRUD operations for food items
  - Nutritional information tracking

- **Meal Management**
  - Create and manage meals
  - Associate foods with meals

- **User Management**
  - User account creation
  - Role-based access control foundation

### 🚧 In Progress

- Food controller pagination
- Image upload for food items
- User profile management
- Admin dashboard

## API Documentation

### Authentication Endpoints

```
POST /api/auth/signup    - Register a new user
POST /api/auth/signin    - Login and receive JWT token
```

### Food Endpoints

```
GET    /api/foods        - List all foods
GET    /api/foods/{id}   - Get food by ID
POST   /api/foods        - Create a new food item
PUT    /api/foods/{id}   - Update food item
DELETE /api/foods/{id}   - Delete food item
```

### Meal Endpoints

```
GET    /api/meals        - List all meals
GET    /api/meals/{id}   - Get meal by ID
POST   /api/meals        - Create a new meal
PUT    /api/meals/{id}   - Update meal
DELETE /api/meals/{id}   - Delete meal
```

## Roadmap

### Phase 1: Core Functionality ⏳

- [ ] Implement pagination for Food endpoints
- [ ] Add image upload support for food items
- [ ] Complete User Profile module
- [ ] Build Admin control panel
- [ ] Database migration to PostgreSQL

### Phase 2: Premium Features 📊

**Paywall & Subscription System**
- [ ] Implement 7-day free trial
- [ ] Free tier with 10 meal limit
- [ ] Premium tier with unlimited meals and advanced dashboards
- [ ] Subscription management and billing integration

**User Profile Enhancements**
- [ ] Profile editing (name, password, avatar)
- [ ] User preferences and settings
- [ ] Profile-to-user relationship refinement

### Phase 3: Advanced Administration 🛡️

**Admin Module**
- [ ] Role and permission management
- [ ] Food database administration
- [ ] Meal template management
- [ ] User account management
- [ ] System analytics and reporting

### Phase 4: Performance & Scale 🚀

- [ ] Flyway database migrations
- [ ] Rate limiting (NGINX integration)
- [ ] Application metrics and monitoring
- [ ] Caching layer implementation
- [ ] Event-driven architecture with messaging
- [ ] Dashboard module with analytics

### Phase 5: DevOps & Deployment 🔧

- [ ] Automated CI/CD pipeline with GitHub Actions
- [ ] PostgreSQL as Docker Compose service
- [ ] Automated deployment to production droplet
- [ ] Environment-specific configurations
- [ ] Health checks and monitoring
