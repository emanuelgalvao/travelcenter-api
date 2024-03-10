# TravelCenter API

### Sobre o projeto

Projeto desenvolvido para a disciplina de Webservices e MBaaS, presente na Pós-Graduação de Desenvolvimento de Aplicativos Móveis da PUC-PR.

Objetivo do projeto era realizar o desenvolvimento de uma API ou MBaaS com uso de banco de dados, autenticação e realizar os testes dos endpoint utilizando o Postman.

Projeto desenvolvido utilizando SpringBoot com a linguagem Kotlin e banco de dados MySQL.

### Endpoints disponíveis

- Após clonar o projeto e executar a API, você pode realizar o teste dos endpoints utilizando [essa collection do postman](https://github.com/emanuelgalvao/travelcenter-api/blob/main/readme/travelcenter.postman_collection.json).

#### App

- Endpoints destinados ao consumo por aplicativos, com objetivo de realizar a autenticação, construção das telas e interação com a aplicação.

  | Endpoint                                | Método HTTP | Observação                                                                                    |
  |-----------------------------------------|-------------|-----------------------------------------------------------------------------------------------|
  | /app/register                           | POST        | Destinado ao cadastro de usuários via app                                                     |
  | /app/login                              | POST        | Destinado ao login de usuários já cadastrados no app                                          |
  | /app/home                               | GET         | Destinado a obter os dados necessários para construção da tela inicial do app                 |
  | /app/search?term={term}                 | GET         | Destinado a realizar a busca de destinos com base em um termo de busca                        |
  | /app/destinationDetails/{destinationId} | GET         | Destinado a obter os dados necessários para construção da tela de detalhes de uma localização |
  | /app/destinationRating                  | POST        | Destinado a adicionar a avaliação de um usuário em relação a um destino                       |
  | /app/favorites/{userId}                 | GET         | Destinado a buscar todas os destinos favoritados pelo usuário                                 |
  | /app/addFavorite                        | POST        | Destinado a adicionar um destino nos favoritos do usuário                                     |
  | /app/removeFavorite/{favoriteId}        | DELETE      | Destinado a remover um destino dos favoritos do usuário                                       |

#### Admin

- Endpoints destinados ao consumo por um sistema administrativo, com objetivo de realizar buscas, cadastrar, atualizações e exclusões de registros.

  | Endpoint                     | Método HTTP | Observação                                                              |
  |------------------------------|-------------|-------------------------------------------------------------------------|
  | /auth                        | POST        | Destinado a realizar a autenticação de usuários no admin                |
  | /countries                   | GET         | Destinado a buscar todos os países cadastrados                          |
  | /destinations                | GET         | Destinado a obter todos os destinos cadastrados                         |
  | /destination/{id}            | GET         | Destinado a realizar a busca dos dados de um destino específico         |
  | /destination                 | POST        | Destinado a cadastrar um novo destino                                   |
  | /destination/{id}            | PUT         | Destinado a atualizar os dados de um destino                            |
  | /destination/{id}            | DELETE      | Destinado a deletar um destino                                          |
  | /destinationTypes            | GET         | Destinado a buscar todos os tipos de destinos cadastrados               |
  | /destinationType/{id}        | GET         | Destinado a realizar a busca dos dados de um tipo de destino específico |
  | /destinationType             | POST        | Destinado a cadastrar um novo tipo de destino                           |
  | /destinationType/{id}        | PUT         | Destinado a atualizar os dados de um tipo de destino                    |
  | /destinationType/{id}        | DELETE      | Destinado a deletar um tipo de destino                                  |
  | /destinationAttractions      | GET         | Destinado a buscar todas as atrações cadastradas no destino             |
  | /destinationAttraction/{id}  | GET         | Destinado a buscar dos dados de uma atração específica                  |
  | /destinationAttraction       | POST        | Destinado a cadastrar uma nova atração em um destino                    |
  | /destinationAttraction/{id}  | PUT         | Destinado a atualizar os dados de uma atração em um destino             |
  | /destinationAttraction/{id}  | DELETE      | Destinado a deletar uma atração de um destino                           |
  | /users                       | GET         | Destinado a buscar todos os usuários cadastrados                        |
  | /user                        | POST        | Destinado a cadastrar um novo usuário                                   |

### Como executar a API

- Após realizar o clone do projeto, aba ele no IntelliJ e adicione as suas configurações relacionadas ao banco de dados no arquivo `application.properties` e execute o projeto:

```
spring.datasource.url=jdbc:mysql://HOST:PORTA/NOMEDB
spring.datasource.username=USUARIO
spring.datasource.password=SENHA
```


