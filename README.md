## Sumary 

- [Trail Backend Ascan](#trail-backend-ascan)
  - [About the project](#about-the-project)
  - [Technologies](#technologies)
  - [Building](#building)
    - [How to build](#how-to-build)
    - [Building with Docker Compose](#building-with-docker-compose)
  - [Docker](#docker)
    - [Get Started](#get-started)
    - [Useful Docker Commands](#useful-docker-commands)
- [My Extra Goals](#my-extra-goals)
- [Credits](#scroll-credits)

# Trail Backend Ascan

This is an open project to conclude a trail for a Intern formation at
[Instituto Altântico](https://www.atlantico.com.br/).


## About the project

This project achitecture
![img.png](Assets/img.png)

### Technologies

- RabbitMQ
- SpringBoot
- Postgres

## Building

```bash
$ mvn clean install
```

### How to build

After building the project using Maven, you'll need to build the container using Docker or Docker Compose.

#### Building with Docker Compose

To build the project withh all its linked services (Postgres and Microservice):

```bash
$ docker-compose up --build
```

## Docker

### Get Started

* [Docker](https://docs.docker.com/userguide)
* [Dockerfile](https://docs.docker.com/reference/builder)

### Useful Docker commands

```
docker images
docker ps
docker exec -it ${containerName} bash
```

# My Extra Goals

- [ ] Swagger API Documentation
- [ ] Unit tests
- [ ] Functional tests
- [ ] Elastic Stack for Logging
- [ ] CI/CD Pipelines

<br><br><br><br>
<h2 align="center">Breno Araripe</strong>
<h6 align="center">"Some people feel the rain. Others just get wet."</h4>

[![GitHub Badge](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/brenoma)
[![LinkedIn Badge](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/brenoma)