# Nutrition Calculator API

A robust backend platform for nutrition tracking and meal management, built with modern Java technologies and designed for scalability.

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
  - [Running the Application](#running-the-application)
- [Features](#features)
- [API Documentation](#api-documentation)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)

## Overview

The Nutrition Calculator API is a comprehensive backend solution that provides authentication, authorization, food database management, user profiles, and administrative capabilities. Built with enterprise-grade technologies, this platform is designed to support nutrition tracking applications with features including meal planning, food management, and premium subscription tiers.

### Key Highlights

- 🔐 JWT-based authentication and authorization
- 🍎 Comprehensive food database with nutritional information
- 🍽️ Meal planning and tracking capabilities
- 👥 Multi-tier user management (Admin, Premium, Free)
- 🚀 Containerized deployment with Docker
- 📊 Planned dashboard analytics and reporting

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Build Tool:** Maven
- **Database:** H2 (development), PostgreSQL (planned for production)
- **Authentication:** JWT (JSON Web Tokens)
- **Containerization:** Docker & Docker Compose
- **CI/CD:** GitHub Actions
- **Deployment:** DigitalOcean Droplet

## Getting Started

### Prerequisites

Ensure you have the following installed on your system:

- Java 17 or higher
- Maven 3.6+
- Docker & Docker Compose (for containerized deployment)
- Git

### Installation

1. **Clone the repository**

```bash
git clone https://github.com/mauricioize/nutrition-calculator-api.git
cd nutrition-calculator-api
```

2. **Build the project**

```bash
mvn clean install
```

### Configuration

1. **Create a `.env` file** in the project root with the following variables:

```env
JWT_SECRET=your_secure_jwt_secret_here
DATABASE_URL=jdbc:postgresql://localhost:5432/nutrition_db
DATABASE_USERNAME=your_db_username
DATABASE_PASSWORD=your_db_password
```

2. **Application Properties**

The application uses profile-based configuration:
- `application.properties` - Base configuration
- `application-local.properties` - Local development settings

### Running the Application

#### Using Maven

```bash
mvn spring-boot:run
```

#### Using Docker

```bash
# Build the Docker image
make build

# Run with Docker Compose
make composeup
```

The API will be available at `http://localhost:8080`

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

*Note: Detailed API documentation will be available via Swagger/OpenAPI (coming soon)*

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

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**Maintained by:** [mauricioize](https://github.com/mauricioize)

**Last Updated:** November 2025
