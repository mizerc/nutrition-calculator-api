# About

TODO

# Development

## Technical

- Java17
- Spring Boot 3
- Maven

## Issues

mvn clean install
mvn clean package

## Docker

make build

## Docker Compose

make composeup
Note: must define .env with JWT_SECRET

# Tasks

## Core

[] Refactor Food Controller to support pagination.
[] Build support to upload images when creating new food.
[] Build user profile module.
[] Build admin module.
[] Build paywall

## Paywall

[] 7 days trial
[] Max 10 meals on free tier
[] More dashboards on premium
[] Buy subscrition tiers to unlock more features

## User Profile Module

[] Update profile (name, password, avatar image)
[] Link user to profile table

## Admin Module

[] Finish roles/permission
[] CRUD foods
[] CRUD meals
[] CRUD users

## Authorization

[] CRUD roles/permission

## Authentication

[X] JWT token

## Dashboard Module

## Perfomance/Quality

[] FLyway database migration
[] Rate Limit (via NGNIX?)
[] Metrics
[] Cache
[] Pagination
[] Event/Message

## Database
[] Add postgres as docker compose service

## Deploy

[] On merge => Github Actions => Droplet deploy as docker