# API Gateway

Gateway feito em GraphQL para abstração da camada Rest para o front-end.

## Get Starting

Instando as dependências
`yarn install`

Executando o APP
`yarn start`

## Tecnologias utilizadas
1. [Typescript](https://www.typescriptlang.org/) Como linguágem base.
2. [GraphQL](https://graphql.org/) Utilizado para implementação dos GraphQL.
3. [Apollo Server](https://www.apollographql.com/) Utilizado para criação do servidor GraphQL
4. [Axios](https://axios-http.com/) Utilizado para as chamadas HTTP da API REST.

## Arquitetura

Foi utilizado clean-architecture para desacoplar as regras de negócios e chamadas para a API Rest das libs utilizadas na camada de GraphQL, facilitando mudanças no futuro.

![GitHub Logo](/images/gateway-api-architecture.png)

Organização do projeto

![GitHub Logo](/images/api-gateway.png)

### Domain
Aqui onde concentramos nossa camanda de negócios e deve ser livre de dependências externas.

![GitHub Logo](/images/api-gateway-domain.png)

##### Entities
Possui a representação das nossas entidades de negócios

##### Repositories
Interface que abstrai a manipulação (Busca, Inclusão, Alteração e Remoção) de dados, seja em banco, outro serviço ou arquivo. Essa camada deve ser implementada na camada de gateway mantendo a inversão de depêndecia.

### Gateway
É nessa camada onde implementamos nossa comunicações externas com aplicações Rest.

![GitHub Logo](/images/api-gateway-gateway.png)

##### IHttpClient
Interface de abstração do protocolo HTTP para que não fiquemos preso a implementações específicas como Axios ou Fetch.

##### AxiosHttpClient
Implementação da interface IHttpClient utilizando o Axios.

##### RestRepository
Implemetação da Interface Repository da camada domain abstraindo a utilização de Rest para a camada de GraphQL. 

##### Factory
Fabrica de componentes das camadas geteway ou domain, deve retornar sempre Interfaces para componentes do geteway, facilitando a mudança de qualquer componente dessa camada.

### GraphQL
Essa camada é onde implementamos tudo relacionado a GraphQL.

![GitHub Logo](/images/api-gateway-graphql.png)

##### Resolver
Camada onde implementamos nosass Queries Resolvers ou Mutation Resolvers utilizando diretamente os RestRepository.

##### Schema
Cadama definimos nossos types de input ou output.
