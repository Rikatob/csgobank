
# TODO

## Commands

### MySql
* ``docker run --name vault_db -e MYSQL_ROOT_PASSWORD=Marengs -p 3306:3306 -d mysql:latest``

### Consul
* ``consul agent -node=learnmicro -dev``

### RabbitMQ
* ``docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management``

### Zipkin
* ``docker run -d -p 9411:9411 openzipkin/zipkin``

## Report

* [x] Prettify report with drawings and decisions about RabbitMQ.

## Project

* [x] Vault publisher to transaction history.
* [x] Restructure project package structure.
* [ ] TODO's.
  * [ ] VaultController.
  * [x] VaultService.
  
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