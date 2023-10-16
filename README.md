
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

