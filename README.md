Project Overview
A backend platform built with Java 17 and Spring Boot 3, packaged using Maven, and deployable via Docker and Docker Compose.  
The system provides authentication, authorization, food management, user profiles, admin features, and an upcoming paywall system.

------------------------------------------------------------

TECH STACK
- Language: Java 17
- Framework: Spring Boot 3
- Build: Maven
- Container: Docker
- Deployment: GitHub Actions + Droplet
- Database: PostgreSQL (planned)

------------------------------------------------------------

DEVELOPMENT

Build & Packaging:
mvn clean install
mvn clean package

Docker:
make build

Docker Compose:
Note: .env must include:
JWT_SECRET=your_secret_here

make composeup

------------------------------------------------------------

FEATURES & ROADMAP

Core Features
[ ] Refactor Food Controller to support pagination
[ ] Add image upload support when creating food
[ ] Implement User Profile module
[ ] Implement Admin module
[ ] Implement paywall functionality

------------------------------------------------------------

PAYWALL
[ ] 7-day free trial
[ ] Free tier: max 10 meals
[ ] Premium: additional dashboards
[ ] Subscription tiers to unlock advanced capabilities

------------------------------------------------------------

USER PROFILE MODULE
[ ] Update profile (name, password, avatar image)
[ ] Link user to profile table

------------------------------------------------------------

ADMIN MODULE
[ ] Roles & permissions
[ ] CRUD Foods
[ ] CRUD Meals
[ ] CRUD Users

------------------------------------------------------------

AUTHORIZATION
[ ] CRUD roles/permissions

------------------------------------------------------------

AUTHENTICATION
[x] JWT token support

------------------------------------------------------------

DASHBOARD MODULE
Details to be defined.

------------------------------------------------------------

PERFORMANCE & QUALITY
[ ] Flyway database migrations
[ ] Rate limiting (via NGINX?)
[ ] Metrics
[ ] Caching
[ ] Pagination
[ ] Event / Messaging

------------------------------------------------------------

DATABASE
[ ] Add PostgreSQL as a Docker Compose service

------------------------------------------------------------

DEPLOYMENT
[ ] On merge → GitHub Actions → Deploy to Droplet via Docker

------------------------------------------------------------

NOTES
Further details and documentation will be added as development progresses.
