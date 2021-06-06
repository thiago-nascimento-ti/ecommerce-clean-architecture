# Ecommerce clean architecture

Esse projeto foi desenvolvido em três partes, todas utilizando os conceitos de clean architecture.

1. [Ecommerce APP](/ecommerce-app)
2. [API Gateway](/api-gateway)
3. [Ecommerce API](/ecommerce-api)

![GitHub Logo](/images/project-architecture.png)

Os detalhes de arquitetura e organização de cada projeto estão em seus respectivos readme.

## Recursos

Após subir os três projetos, terá acesso a [Home](http://localhost:3000/).

### Home

Nesta temos alguns recursos implementados.

1. Ao clicar em `adicionar ao carrinho` deve incluir o produto no carrinho, lançando um popup de confirmação e trocando a badge de quantidade sobre o botão `Carrinho` do header.
2. Caso não possua mais estoque, será apresentado um modal informando ao usuário, sem que o produto seja incluído no carrinho.
3. A paginação no rodapé da página deve alterar a listagem de produtos conforme a página selecionada.
4. Ao clicar no `card do produto` o usuário deve ser levado a página com os detalhes do mesmo.

### Página do produto

Na página de produtos temos alguns recursos implementados.

1. Ao pressionar o botão de `adicionar ao carrinho` deve seguir o mesmo padrão de regras da home validando o estoque e informando o usuário conforme a ação.
2. Ao pressionar o botão `Escolher outros produtos` deve voltar para a home.

### Página do carrinho

##### Minha Cesta

1. Uma listagem com paginação deve ser apresentada com a foto, descrição, quantidade e preço total de cada produto. Essa listagem mostra apenas 4 produtos por vez e também é paginada.
2. Ao pressionar na `imagem do produto` um preview será apresentado com a imagem em um tamanho maior.
3. Na coluna `Qtd.` temos o botão `+` e `-` e devem adicionar ou remover o produto da cesta conforme clicado, as mesmas regras da home em questão de estoque devem ser aplicadas, validando estoque e notificando o usuário.
4. Quando a quantidade do produto é diminuída a 0, o produto deve sair do carrinho, atualizando a listagem.
5. Na coluna `Qtd.` também temos um input de número e deve permitir que o usuário digite qualquer número que queira, exceto 0. Caso digitado um número maior que a quantidade de estoque, o usuário deve ser informado com as mesmas regras da tela de home.
6. Na coluna `Qtd.` temos um botão `Remover produto` e quando pressionado deve remover o produto do carrinho.

##### Resumo do pedido

1. Deve apresentar a quantidade de produtos e o valor total.
2. Irá apresentar frete sempre 0 pois não foi implementado.
3. Deve apresentar o total geral e logo abaixo a quantidade de vezes que é possível parcelar. Cada produto possui a quantidade de parcelas possíveis, a regra aqui é sempre a maior quantidade entre os produtos.
4. Ao pressionar `Adicionar mais produtos` deve voltar para home.
5. Ao pressionar `Finalizar compra` deve levar o usuário a página de checkout caso possua produto, caso não tiver, deve apresentar um modal para o usuário e fazer o redirecionamento para a home.

### Checkout

1. Caso o usuário acessar diretamente a rota /checkout sem produtos no carrinho, deve fazer o redirecionamento para a home.

##### Resumo do pedido

1. Deve apresentar a quantidade de produtos e o valor total.
2. Irá apresentar frete sempre 0 pois não foi implementado.
3. Deve apresentar o total geral e logo abaixo a quantidade de vezes que é possível parcelar. Cada produto possui a quantidade de parcelas possíveis, a regra aqui é sempre a maior quantidade entre os produtos.

##### Forma de Pagamento

1. O campo `número do cartão` deve aceitar apenas números, formatando com espaço a cada 4 números. É validado como um campo obrigatório e deve conter exatamente os 16 dígitos.
2. O campo `nome impresso no cartão` é um campo de texto livre e é validado como um campo obrigatório.
3. O campo `validade do cartão` é um pickup de mês e é validado como um campo obrigatório.
4. O campo `cvv` deve aceitar apenas números. É validado como um campo obrigatório e deve conter exatamente 3 dígitos.
5. O select `parcelas` sempre vem selecionado padrão como 1 e deve apresentar todas as opções conforme os produtos selecionados.
6. Após pressionar `Fechar pedido` as validações devem ser aplicadas e qualquer alteração no campo será atualizada nas mensagens de validação.
7. Após pressionar `Fechar pedido` com todos os campos corretos o pedido será enviado e armazenado no banco de dados, um modal confirmando a compra será mostrado, o carrinho será zerado e o usuário redirecionado para a home.

##### Validações de Backend

1. o `número do cartão` `1234 1234 1234 1234` será sempre considerado inválido, qualquer outro número com 16 dígitos será considerado correto.
2. o `cvv` `123` será sempre considerado inválido, qualquer outro número com 3 dígitos será considerado correto.
3. a `validade do cartão` será considerado válido desde que seja do mês atual ou futuro, qualquer mês/ano passado será considerado errado.
4. O backend irá validar o estoque dos produtos novamente no processo de checkout, caso 2 usuários simultâneos coloquem os mesmos produtos no carrinho, `Fechar pedido` será apresentado informando que alguns produtos estão com estoque insuficiente.

### Outros recursos

1. O botão `carrinho` será apresentado em todas as páginas e ao ser pressionado redireciona o usuário para a página de carrinho.
2. Um breadcrumb é criado dinamicamente em cada página e é possível pressionar em `home` para voltar à página anterior.
3. Os dados do carrinho são armazenados no Redux e LocalStorage, ao acessar a URL diretamente o Redux carrega o LocalStorage com os dados anteriores.
4. O layout foi desenvolvido em forma responsiva utilizando os grid do Ant Design.
