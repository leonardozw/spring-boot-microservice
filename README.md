# README for Spring Boot Microservice Demo

!!! README is to be updated !!!

### General

This README file contains the technologies used and a checklist of what I have done so far and what I plan to do in this project. For a brief explanation of what this microservice project is about, it is a simple order flow: a client makes an order, and the inventory service gets notified to perform its operations. When it's done, the shipping service gets notified to do its operations.

### Technologies

- Java
- Spring Boot
- PostgreSQL
- Redis
- Kafka
- Docker

### What I want to do in this project:
- [x] Create a demo of a microservice flow using Spring Boot
- [x] The project should have at least 3 microservices
- [x] One microservice will be the "Main" service of the application
- [x] The "Main" service will be a REST API
- [x] The "Main" service should be able to store responses in Redis as cache
- [x] The "Main" service should be able to send information to Kafka topics
- [x] The other services should be subscribed to those topics that the "Main" service is sending information to
- [x] The other services should listen to those topics and act based on the information received
- [ ] To keep the code DRY, a custom library containing the Kafka configuration should be created
- [ ] The services should be able to use the custom Kafka configuration library
- [ ] All services should have a logging system
- [ ] Each service should have automated tests
- [ ] Tests should be using Testcontainers
- [ ] The whole project should be containerized
- [ ] Deployment should be on AWS or Azure
