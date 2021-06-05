# Ecommerce API

### Rodando o projeto com docker-compose

Com essa opção não é necesário instalar o Java, gradle e nem o MongoDB, basta ter o docker compose, o dados de produtos iniciais já serão carregados no banco.

```
docker-compose build && docker-compose up
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
### Tecnologias utilizadas
1. Java 11 como linguágem base.
2. [Spring](https://spring.io/) utilizando SpringMVC como framework mvc, SpringData como ORM, SpringBoot para setup. 
3. [MongoDB](https://www.mongodb.com/) como banco de dados no SQL.
4. [Gradle](https://gradle.org/) foi utilizado como gerenciador de depêndecias e plugins.
5. [Docker](https://www.docker.com/) foi utilizando para criar um container para facilitar o startup do projeto evitando ter que instalar Java, MongoDB e Gradle.
6. [Testcontainers](https://www.testcontainers.org/) Utilizado para criação de teste de integração possíbilitando subir um mongodb embedded e executar testes de ciclo completo.
7. [JUnit 5](https://junit.org/junit5/) Utilizado para os testes unitários.
8. [Mockito](https://site.mockito.org/) Utilizado para criar mock nos teste unitários.


### Arquitetura
Foi utilizado clean-architecture para desacopalhar as regras de negócios de depêndecias externas como banco de dados, frameworks (Spring) e biblíotecas, mantendo a parte a camada core o mais pura possível apenas utilizando de Java puro e bibliotecas de testes.

![GitHub Logo](/images/ecommerce-api-architecture.png)

Dentro dessa arquitetura temos as seguintes camadas

#### Core
Essa camada deve ser livre de depêndecias externas, dentro dela temos as classes:

###### Entity
Representa uma entidade de negócio sem vinculos com banco de dados, pode possúir regras de negócios mais genéricas e atríbutos sem a necessídade de ser um [modelo anemico](https://www.martinfowler.com/bliki/AnemicDomainModel.html)

###### Repository
Interface que abstrai a manipulação(Busca, Inclusão, Alteração e remoção) de dados seja em banco, outro serviço ou arquivo, essa camada deve ser implementada na camada de infrastructure mantendo a [inversão de depêndecia](https://medium.com/xp-inc/os-princ%C3%ADpios-do-solid-dip-princ%C3%ADpio-de-invers%C3%A3o-de-depend%C3%AAncia-7e110cfcc3e5). 

###### UseCase
Representa uma ação de negócio, geralmente seguindo o [padrão de projeto Command](https://medium.com/xp-inc/design-patterns-parte-16-command-9c73af726c9c), utiliza das camadas de reposítory e entity.

###### Service
Utilizado para orquestrar os use cases e expô-los nas camadas acima, como uma decisão minha preferi não utilizá-los diretamente em outras camadas facilitando a injeção de depêndencia.

###### BussinessException
Exceções de negócio que estendem apenas exceções do próprio Java e devem ser tratadas na camada de infraestructure.

#### Adapters
Essa camada trata de converter as entidades de neǵocios em modelos de banco e DTOs utilizados nas camadas de infrastructure, nessa camada já possuímos um mínimo de influência de frameworks, geralmente de anotações de ORM.

###### Model
Modelo que representa uma dados de um reposítorio, nesse projeto foi utilizado para mapear as entidades do MongoDB.

###### ModelAdapter
Conversor de Modelo para Entity e vice-versa.

###### RepositoryAdapter
Classe abastrata que estende das interfaces Repository utilizando os ModelAdapter para conversão da entidade para modelo e vice-versa, ainda não implementa a lógica de reposítory real, por isso utiliza o [padrão de projeto template](https://medium.com/xp-inc/design-patterns-parte-24-template-method-69e3a7927dcd) criando métodos que serão implementados na camada de infrastructure.

###### OutputData
Representa um DTO de sáida de dados e estende de OutputMapper implementando a lógica de conversão de entidade de negócio para o OutputData. 

###### InputData
Representa um DTO de entrada de dados e estende de InputMapper implementando a lógica de conversão de InputData para a entidade de negócio.

#### Infrastructure

###### MongoRepository
Interface contendo os métodos de acesso ao banco de dados, pode estender do MongoRepository do SpringBoot que possui diversos métodos implementados. A implementação dessa classe é de responsábilidade do [SpringData](https://spring.io/projects/spring-data) e é feito em tempo de execução de acordo com o padrão de nome dos métodos e argumentos. 

###### RepositoryBridge
Implementa os métodos criados com o pedrão de projeto template do nosso RepositoryAdapter chamando o MongoRepository, tem objetivo criar uma ponte entre essas duas partes.

###### ServiceFactory
Camada que contém nossas fabricas de services, atráves dela que o Spring irá fazer a injeção de depêndencia desses modulos.

###### ExceptionGlobalHandler
Responsável por tratar as exceptions de negócios lançando exceções que o spring entenda, com isso conseguimos definir corretamente o retorno de status e corpo nos nossos endpoints rest.

###### Resources
É onde definimos nossos endpoints rest utlizando o SpringMVC e todas as camadas acima.





