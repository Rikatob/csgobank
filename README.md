
# CSGOBANK

## Commands

### MySql
* ``docker run --name vault_db -e MYSQL_ROOT_PASSWORD=Marengs -p 3306:3306 -d mysql:latest``

### Consul
* ``consul agent -node=learnmicro -dev``

### RabbitMQ
* ``docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management``

### Zipkin
* ``docker run -d -p 9411:9411 openzipkin/zipkin``


### Build consul importer image
* ``docker build -t consul-importer:1.0 .``


### Push image docker hub
* Login
  * ``docker login``
* Change tag : 
  * ``docker image tag gateway:0.0.1-SNAPSHOT rikatob/gateway:0.0.1-SNAPSHOT``
* Push that image: 
  * ``docker image push rikatob/gateway:0.0.1-SNAPSHOT``


## Report

* [x] Prettify report with drawings and decisions about RabbitMQ.
* [ ] Update report with process regarding:
  * [ ] Gateway
  * [ ] Centralised logg
  * [ ] Docker
* [ ] Create a "module" diagram of our planned project structure.

## Project

* [x] Vault publisher to transaction history.
* [x] Restructure project package structure.
* [ ] Float problems with int, cant be feks 0233 that indicates 0,0233.
      Total value in vault should not be field but a method. Should it be in Vault or VaultService?

* [ ] TODO's.
  * [ ] VaultController.
  * [x] VaultService.
  * [ ] Change from layour to encoder logback. (https://logback.qos.ch/codes.html#layoutInsteadOfEncoder)
  * [ ] Use health checks to avoid that some services start before a service it is dependent on an/or spamming repuest ? 
    * https://devops.stackexchange.com/questions/12092/docker-compose-healthcheck-for-rabbitmq?newreg=614274132fbc4f92a92ba80f6d758a76
    * https://www.rabbitmq.com/monitoring.html#health-checks
  * [ ] Frontend running on own server.
## Gateway

* [x] Gateway
    * [x] Service Discovery
        * [x] Spring-cloud-starter-consul-discovery (Discovery server)
        * [x] Spring-boot-starter-actuator (Health check)
    * [x] Load Balancer.
        * [x] Spring-cloud-load-balancer
    * [x] Routing.
        * [x] Spring-cloud-starter-gateway
* [x] Centralized Configuration.
    * [x] Consul config server. ( bootstrap.yml)


# Grading

## Required (but not sufficient) for E
* [X] Use multiple services, that fulfill different functionality and communicate with each
  other

## Required (but not sufficient) for D
* [x] At least two of the services communicate using synchronous communication (for example, direct REST calls between two services).
* [x] At least two of the services communicate using asynchronous communication (for example, using Message Queue). This will be done in accordance with event-driven architecture, as discussed in class.


## Required (but not sufficient) for C
* [x] The project uses a unique access point, that handled calls and routes them to appropriate services – Gateway
* [X] The project uses a unique access point that, in addition to routing calls, also does load balancing

## Required (but not sufficient) for B
* [X] The project has a means of centrally controlling the health of running services – health check
* [X] The project has a means of centrally controlling configurations for the services – for example, using Consul

## Required (but not sufficient) for A
* [X] The project has a means of containerization – building container images from the existing services and getting such containers running and interacting with each other