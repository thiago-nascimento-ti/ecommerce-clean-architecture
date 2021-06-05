# API Gateway

Gateway feito em GraphQL para abstração da camada Rest para o front-end.

## Get Starting

Instalando as dependências
`yarn install`

Executando o APP
`yarn start`

## Tecnologias utilizadas

1. [Typescript](https://www.typescriptlang.org/) como linguagem base.
2. [GraphQL](https://graphql.org/) utilizado para implementação do GraphQL.
3. [Apollo Server](https://www.apollographql.com/) uilizado para criação do servidor GraphQL.
4. [Axios](https://axios-http.com/) utilizado para as chamadas HTTP da API REST.

## Arquitetura

Foi utilizado clean-architecture para desacoplar as regras de negócios e chamadas para a API Rest das libraries utilizadas na camada de GraphQL, facilitando mudanças no futuro.

![GitHub Logo](/images/gateway-api-architecture.png)

## Organização do projeto

![GitHub Logo](/images/api-gateway.png)

### Domain

Aqui onde concentramos nossa camada de negócios e deve ser livre de dependências externas.

![GitHub Logo](/images/api-gateway-domain.png)

##### Entities

Possui a representação das nossas entidades de negócios.

##### Repositories

Interface que abstrai a manipulação (Busca, Inclusão, Alteração e Remoção) de dados, seja em banco, outro serviço ou arquivo. Essa camada deve ser implementada na camada de gateway mantendo a inversão de dependência.

### Gateway

É nessa camada onde implementamos as comunicações externas com aplicações Rest.

![GitHub Logo](/images/api-gateway-gateway.png)

##### IHttpClient

Interface de abstração do protocolo HTTP para que não fiquemos presos a implementações específicas como Axios ou Fetch.

##### AxiosHttpClient

Implementação da interface IHttpClient utilizando o Axios.

##### RestRepository

Implementação da Interface Repository da camada domain abstraindo a utilização de Rest para a camada de GraphQL.

##### Factory

Fábrica de componentes das camadas gateway ou domain. Deve retornar sempre Interfaces para componentes do gateway, facilitando a mudança de qualquer componente dessa camada.

### GraphQL

Essa camada é onde implementamos tudo relacionado a GraphQL.

![GitHub Logo](/images/api-gateway-graphql.png)

##### Resolver

Camada onde implementamos nossas Queries Resolvers ou Mutation Resolvers utilizando diretamente os RestRepository.

##### Schema

Camada onde definimos nossos types de input ou output.
