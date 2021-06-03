# Ecommerce API

### Rodando o projeto com docker-compose

Com essa opção não é necesário instalar o Java, gradle e nem o MongoDB, basta ter o docker compose.

```
docker-compose up
```

### Rodando o projeto com gradle (Opcional)

É necesário instalar

1. Java 11
2. Gradle 7.0.2
3. MongoDB 4.4.6 (ou docker-compose up -d mongo)

Rodando a aplicação

```
./gradlew clean build && ./gradlew bootRun
```

Rodando os testes

```
./gradlew clean test
```

Rorando o checkstyle

```
./gradlew checkstyleMain checkstyleTest
```