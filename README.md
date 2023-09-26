docker run --name vault_db -e MYSQL_ROOT_PASSWORD=Marengs -p 3306:3306 -d mysql:latest
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management
# TODO

## Report

* [x] Prettify report with drawings and decisions about RabbitMQ.

## Project

* [x] Vault publisher to transaction history.
* [x] Restructure project package structure.
* [ ] TODO's.
  * [ ] VaultController.
  * [x] VaultService.
  
## Gateway

* [ ] Gateway
    * [ ] Service Discovery
        * [ ] Spring-cloud-starter-consul-discovery (Discovery server)
        * [ ] Spring-boot-starter-actuator (Health check)
    * [ ] Load Balancer.
        * [ ] Spring-cloud-load-balancer
    * [ ] Routing.
        * [ ] Spring-cloud-starter-gateway
* [ ] Centralized Configuration.
    * [ ] Consul config server. ( bootstrap.yml)