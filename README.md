# Book Management System - Docker Setup Guide

This guide will walk you through the steps to set up and run the Book Management System using Docker.

---

## Prerequisites

1. **Install Docker and Docker-Compose**:  
   If you don't have Docker and Docker-Compose installed, please install them before proceeding.
    - [Install Docker](https://docs.docker.com/get-docker/)
    - [Install Docker-Compose](https://docs.docker.com/compose/install/)

---

## Setup Instructions

2. **Navigate to the Docker Directory**:  
   Go to the `docker` directory located in the project:
   ```sh
   cd ./docker

3. **Launch recreate.sh**:

   It will install necessary images, reinstall you all volumes and start all containers

4. To stop or to start container again, you have `stop.sh` and `start.sh` scripts respectively.

## Docker Containers

| Service  | Port | Additional Info                                 |
|----------|------|-------------------------------------------------|
| Postgres | 5432 | Database name: `db`, login and password: `root` |
| Kafka    | 9092 | Message broker                                  |
| Kafdrop  | 9000 | Kafka UI for monitoring                         |
| Kafka-UI | 9090 | Alternative Kafka UI                            |

All volumes are stored in local

---

## Spring Boot Application

6. **Start the Spring Boot Application**:  
   After running the `recreate.sh` script, start your Spring Boot application. Liquibase will automatically generate all
   the necessary database tables and preload test data for you to work with.


7. **Access Swagger UI**:  
   Once everything is up and running, you can test the APIs using Swagger UI. Open the following address in your
   browser:  
   [http://localhost:8000/swagger-ui/index.html](http://localhost:8000/swagger-ui/index.html)

---

8. **System structure**:

There are authors and book models.
Also, there is a `Library member` model who can loan books from the library.
All records of books loan stores in book_loan table.
And there are some system tables for liquibase that are not necessary for a book management model.

![db.png](/db.png)