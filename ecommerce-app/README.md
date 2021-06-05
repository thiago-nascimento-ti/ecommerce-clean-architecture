# Ecommerce APP

Aplicação front-end para o projeto Ecommerce.

## Get Starting

Instalando as dependências
`yarn install`

Executando o APP
`yarn start`

Rodando os testes
`yarn test`

## Tecnologias utilizadas

1. [Typescript](https://www.typescriptlang.org/) como linguagem base.
2. [React](https://pt-br.reactjs.org/) como framework de componentes.
3. [Redux](https://redux.js.org/) foi utilizado para controle de estados globais da aplicação.
4. [React Router](https://reactrouter.com/) foi utilizado para controle de rotas da aplicação.
5. [React Hook Forms](https://react-hook-form.com/) utilizado para controle de estado dos formulários.
6. [Yup](https://github.com/jquense/yup) utilizado como schema para validações do formulário.
7. [Axios](https://axios-http.com/) utilizado para as chamadas HTTP.
8. [Ant Design](https://ant.design/) utilizado como biblioteca de componentes em React.
9. [Jest](https://jestjs.io/pt-BR/) utilizado para os testes unitários.

## Arquitetura

Foi utilizado clean-architecture para desacoplar as regras de negócios de dependências externas que compõem o ecossistema React, mantendo a parte da camada domain o mais pura possível apenas utilizando TypeScript e bibliotecas de testes. Esse tipo de arquitetura permite futura mudanças de stacks sem afetar o projeto como um todo.

![GitHub Logo](/images/ecommernce-app-architecture.png)

### Domain

Aqui é onde concentramos nossa camada de negócios e deve ser livre de dependências externas.

![GitHub Logo](/images/domain.png)

##### Entity

Nessa camada possuímos nossas entidades que estão divididas entre Types/Intefaces e Class. Sempre que possuímos alguma classe de negócio com métodos fugindo de [modelos anêmicos](https://www.martinfowler.com/bliki/AnemicDomainModel.html) precisamos estender de uma interface somente com atributos, isso possibilita a conversão dessas classes para um JsonObject que é utilizado para trafegar em camadas acima.

##### Repository

Interface que abstrai a manipulação (Busca, Inclusão, Alteração e Remoção) de dados, seja em banco, outro serviço ou arquivo. Essa camada deve ser implementada na camada de gateway mantendo a inversão de dependência.

##### UseCase

Representa uma ação de negócio, geralmente seguindo o [padrão de projeto Command](https://medium.com/xp-inc/design-patterns-parte-16-command-9c73af726c9c), utiliza das camadas de repository e entity.

##### BusinessException

Exceções de negócio e devem ser tratadas na camadas de gateway ou presentation.

### Gateway

É nessa camada onde implementamos as comunicações externas com aplicações Rest ou GrahpQL e também onde convertemos nossas entidades de negócios para JsonObject e vice-versa para que possam trafegar na camada de presentation.

![GitHub Logo](/images/gateway.png)

##### JsonAdapter

Conversor de entidade de negócios para JsonObject (Types ou interfaces sem métodos) e vice-versa.

##### IHttpClient

Interface de abstração do protocolo HTTP para que não fiquemos presos a implementações específicas como Axios ou Fetch.

##### AxiosHttpClient

Implementação da interface IHttpClient utilizando o Axios.

##### GraphQLClient

Implementação de client GraphQL utilizando IHttpClient como dependência para as chamadas POST.

##### GraphQLRepository

Implementação da Interface Repository da camada domain abstraindo a utilização de GraphQL.

##### Factory

Fábrica de componentes das camadas gateway ou domain. Deve retornar sempre Interfaces para componentes do gateway, facilitando a mudança de qualquer componente dessa camada.

### Presentation

É aqui onde devemos manter o ecossistema do React ou qualquer outro framework de front-end que vir a ser utilizado.

![GitHub Logo](/images/presentation.png)

##### Redux

A camada do Redux se utiliza dos UseCases para manipulação de regras de negócios, por isso não devemos ter essas regras diretamente nos reducers.

##### Hooks

Nossos Hooks que manipulam dados externos devem injetar as implementações das Interfaces de Repository para que não conheçam a camada de HTTP ou qualquer outra que possa vir a ser utilizada.

##### React Component

Componentes do React que podem representar páginas ou componentes menores. Não é aconselhável utilizar diretamente Repositories ou UseCases, prefira criar um Hook ou um Reducer para centralizar o uso desses componentes.

##### Estrutura de um React Component

Os componentes foram organizados seguindo o padrão [Presentational and Containers](https://medium.com/@dan_abramov/smart-and-dumb-components-7ca2f9a7c7d0). Além disso, foi criada outra divisão para o CSS chamando de Styled Component.

![GitHub Logo](/images/cart-review.png)

Nem sempre é necessário possuir todas as camadas, existem componentes que só possuem função de apresentação, nesse caso o "ExemploView" é exportado no index.tsx como "Exemplo" e conforme necessidade futura pode ser criado o Container Component exportando ele sem ter que alterar os demais locais onde é importado.

Também foi utilizado o conceito de [Nest Component](https://medium.com/byte-sized-react/nesting-react-components-a12a20808da7) para quebrar a página em Components que não são genéricos o suficiente para ir para a pasta de componentes globais, mas podem ser um componente da própria página.

![GitHub Logo](/images/nest-components.png)
