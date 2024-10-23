
# Microservices Architecture with Spring Cloud

Este projeto implementa uma arquitetura de microserviços usando **Spring Cloud**, **Eureka** para descoberta de serviços e um **API Gateway** para roteamento inteligente. Os microserviços são construídos como contêineres Docker e orquestrados usando o **Docker Compose**.

## Arquitetura de Microserviços

Os microserviços disponíveis nesta aplicação incluem:

1. **Eureka Server**: Responsável pelo registro e descoberta dos serviços.
2. **API Gateway**: Atua como ponto de entrada para todos os serviços, fazendo roteamento de requisições para os microserviços registrados no Eureka.
3. **User Service**: Um serviço de usuários registrado no Eureka e acessível via API Gateway.
4. **Notification Service**: Um serviço responsável por enviar notificações, também registrado no Eureka.
5. **Task Service**: Um serviço responsável por tarefas, integrado ao sistema por meio do Eureka.

## Pré-requisitos

- **Docker** (versão 20.x ou superior)
- **Docker Compose** (versão 1.29 ou superior)

## Estrutura do Projeto

Cada microserviço é implementado como um contêiner Docker, com configuração específica em arquivos **application.properties** ou **application.yml**. As imagens Docker para cada serviço são referenciadas no arquivo `docker-compose.yml` e configuradas para se registrarem automaticamente no **Eureka Server**.

### Portas Utilizadas

- **Eureka Server**: `8761`
- **API Gateway**: `8080`
- **User Service**: `8081`
- **Task Service**: `8082`
- **Notification Service**: `8084`

## Como Executar o Projeto

### Passo 1: Clonar o Repositório

Clone este repositório para a sua máquina local:

```bash
git clone <URL_DO_REPOSITORIO>
cd <NOME_DO_DIRETORIO>
```

### Passo 2: Compilar os Microserviços

Antes de executar o Docker Compose, certifique-se de que todos os microserviços estão devidamente compilados. Se você estiver utilizando Maven, pode usar o seguinte comando no diretório de cada microserviço:

```bash
mvn clean package
```

### Passo 3: Executar com Docker Compose

Agora, você pode iniciar todos os serviços utilizando o Docker Compose. Navegue até o diretório do projeto onde o arquivo `docker-compose.yml` está localizado e execute:

```bash
docker-compose up --build
```

Este comando irá construir as imagens (se necessário) e iniciar todos os contêineres.

### Passo 4: Verificar os Serviços

- **Eureka Server** pode ser acessado via `http://localhost:8761`
- Os microserviços podem ser acessados através do **API Gateway** em `http://localhost:8080`

### Passo 5: Monitorar a Saúde dos Serviços

Cada microserviço está configurado com um **healthcheck** que pode ser verificado acessando os seguintes endpoints:

- **Eureka Server**: `http://localhost:8761/actuator/health`
- **API Gateway**: `http://localhost:8080/actuator/health`
- **User Service**: `http://localhost:8081/actuator/health`
- **Notification Service**: `http://localhost:8084/actuator/health`
- **Task Service**: `http://localhost:8082/actuator/health`

### Parar os Serviços

Para parar todos os serviços, utilize o seguinte comando:

```bash
docker-compose down
```

## Detalhes dos Microserviços

### Eureka Server

O **Eureka Server** é o registro de serviços da aplicação, que permite aos microserviços se registrar e descobrir outros serviços dinamicamente.

- Porta: `8761`
- Configuração específica:
  - `EUREKA_CLIENT_REGISTER_WITH_EUREKA=false`
  - `EUREKA_CLIENT_FETCH_REGISTRY=false`

### API Gateway

O **API Gateway** utiliza o **Spring Cloud Gateway** para rotear requisições para os microserviços registrados no Eureka.

- Porta: `8080`
- Configuração específica:
  - `EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE=http://eureka-server:8761/eureka`

### User Service

O **User Service** é um serviço registrado no **Eureka** que fornece operações relacionadas a usuários.

- Porta: `8081`
- Replicado em 3 instâncias (configuração no `docker-compose.yml`).
- Configuração específica:
  - `SPRING_APPLICATION_NAME=user-service`
  - `SERVER_PORT=8081`

### Notification Service

O **Notification Service** é responsável por enviar notificações e está registrado no **Eureka**.

- Porta: `8084`
- Configuração específica:
  - `SPRING_APPLICATION_NAME=notification-service`
  - `SERVER_PORT=8084`

### Task Service

O **Task Service** gerencia tarefas e também está registrado no **Eureka**.

- Porta: `8082`
- Configuração específica:
  - `SPRING_APPLICATION_NAME=task-service`
  - `SERVER_PORT=8082`

## Problemas Comuns

- **Exited (143)**: Se algum dos contêineres estiver parando inesperadamente com este código de saída, pode ser útil verificar os logs usando `docker logs <container_name>`.
  
- **Problemas de Conexão com o Eureka**: Certifique-se de que o Eureka está funcionando corretamente antes de iniciar os outros serviços. Eles dependem da saúde do Eureka Server para se registrar corretamente.

## Autor

Desenvolvido por [Guilherme da Silveira Cantoni].
