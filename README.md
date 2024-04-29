
# Exemplo de Microsserviços com Quarkus e Angular

Bem-vindo ao projeto!
Este projeto é um exemplo de aplicação de microsserviços desenvolvido com Quarkus para o backend, Angular para o frontend e PostgreSQL como banco de dados. O objetivo deste projeto é demonstrar a arquitetura de microsserviços e a integração entre diferentes tecnologias para construir um sistema completo e funcional.

## Stack utilizada

**Back-end:** Quarkus, Framework Java para desenvolvimento de microsserviços eficientes e escaláveis. Além do PostgreSQL, Banco de dados relacional utilizado para armazenamento de dados.

**Front-end:** Angular, Framework JavaScript para construção de interfaces de usuário modernas e responsivas.


## Configuração

1. Configuração do Backend
- ms-heroes:
    - verifique se o `application.properties` esteja configurado corretamente:
  ```yml
  quarkus.hibernate-orm.database.generation=drop-and-create
  quarkus.http.port=8083
  ```
    - por fim, execute o comando:
  ```yml
  ./mvnw compile quarkus:dev
  ```
- ms-villain:
    - verifique se o `application.properties` esteja configurado    corretamente:
  ```yml
  quarkus.hibernate-orm.database.generation=drop-and-create
  quarkus.http.port=8084
  ```
    - por fim, execute o comando:
  ```yml
  ./mvnw compile quarkus:dev
  ```
- ms-fight:
    - verifique se o `application.properties` esteja configurado    corretamente:
  ```yml
  quarkus.hibernate-orm.database.generation=drop-and-create
  quarkus.http.port=8082
  ```
    - por fim, execute o comando:
  ```yml
  ./mvnw compile quarkus:dev
  ```
- ui-super-hero:
    - verifique se o `application.properties` esteja configurado    corretamente:
  ```yml
  quarkus.hibernate-orm.database.generation=drop-and-create
  quarkus.http.port=8080
  ```
    - por fim, execute o comando:
  ```yml
  ./mvnw compile quarkus:dev
  ```
## Documentação da API
- Ms-Heroes

### Recupera todas os herois disponíveis.

```http
  GET /api/heroes
```

### Recupera um heroi randomico.

```http
  GET /api/heroes/random
```

### Recupera um heroi específico com base no ID fornecido.

```http
  GET /api/heroes/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório**. O id do seu heroi  |

### Cria um novo heroi. JSON exemplo:

```http
  POST /api/heroes
```
```JSON
{
  "level": 5,
  "name": "Teste 123",
  "powers":"Agility, Longevity"
}
```

### Atualiza um heroi ja disponivel. JSON exemplo:

```http
  PUT /api/heroes
```
```JSON
{
  "id": 4,
  "level": 5,
  "name": "Chewbacca StarWars",
  "otherName": "",
  "picture": "https://www.superherodb.com/pictures2/portraits/10/050/10466.jpg",
  "powers": "Agility, Longevity, Marksmanship, Natural Weapons, Stealth, Super Strength, Weapons Master"
}
```

### Deleta um heroi.

```http
  DELETE /api/heroes/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório**. O id do seu heroi  |

- Ms-Villain

### Recupera todas os vilão disponíveis.

```http
  GET /api/villains
```

### Recupera um vilão randomico.

```http
  GET /api/villains/random
```

### Recupera um vilão específico com base no ID fornecido.

```http
  GET /api/villains/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório**. O id do seu vilão  |

### Cria um novo vilão. JSON exemplo:

```http
  POST /api/villains
```
```JSON
{
  "level": 5,
  "name": "Teste 123",
  "otherName": "",
  "picture": "https://www.superherodb.com/pictures2/portraits/10/050/10466.jpg",
  "powers": "Agility, Longevity, Marksmanship, Natural Weapons, Stealth, Super Strength, Weapons Master"
}
```

### Atualiza um vilão ja disponivel. JSON exemplo:

```http
  PUT /api/villains
```
```JSON
{
  "id": 4,
  "level": 5,
  "name": "Villain 5 test",
  "otherName": "",
  "picture": "https://www.superherodb.com/pictures2/portraits/10/050/10466.jpg",
  "powers": "Agility, Longevity, Marksmanship, Natural Weapons, Stealth, Super Strength, Weapons Master"
}
```

### Deleta um vilão.

```http
  DELETE /api/villains/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório**. O id do seu vilão  |

- Ms-fight
### Recupera todos as lutas disponíveis.

```http
  GET /api/fight
```

### Recupera um usuário específico com base no ID fornecido.

```http
  GET /api/fight/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID da luta que você quer |

### Recupera uma luta randomico.

```http
  GET /api/fight/random
```

### Cria um novo usuário. JSON exemplo:

```http
  POST /api/fight
```
```JSON
{
  "hero": {
      "name": "Chewbacca",
      "level": 5,
      "picture": "https://www.superherodb.com/pictures2/portraits/10/050/10466.jpg",
      "team": "heroes"
  },
  "villain": {
      "name": "Buuccolo",
      "level": 3,
      "picture": "https://www.superherodb.com/pictures2/portraits/11/050/15355.jpg",
      "team": "villains"
  }
}
```

### Documentação com Swagger
A documentação completa da API pode ser encontrada no Swagger também. Para acessar a documentação, siga as etapas abaixo:

1. Certifique-se de que o projeto esteja em execução.

2. Abra um navegador da web e vá para a seguinte URL:
   2.1 Para o ms-heroes: [Swagger API Heroes](http://localhost:8083/q/swagger-ui/#/)

2.2 Para o ms-villains: [Swagger API Villain](http://localhost:8084/q/swagger-ui/#/)

2.3 Para o ms-fight: [Swagger API Fights](http://localhost:8082/q/swagger-ui/#/)

2.4 Para o ui-super-hero: [Swagger API Ui Super Hero](http://localhost:8080/q/swagger-ui/#/)

3. Isso abrirá a interface do Swagger, onde você pode explorar e testar os endpoints da API, apenas se tiver autenticado.
4. Use os exemplos com JSON com que já tem disponível aqui, ficará mais fácil para testar.

Divirta-se explorando a API!

## Sobre o projeto

O projeto consiste em um pequeno CRUD de heróis e vilões, com um microsserviço dedicado para cada entidade. Além disso, há um microsserviço responsável por toda a lógica de "fights" entre heróis e vilões. Este microsserviço é responsável por receber as requisições do frontend, comunicar-se com os microsserviços de heróis e vilões, e retornar o resultado das batalhas.

A interface com o usuário é construída com Angular e se comunica com o microsserviço de "fights" para obter os dados necessários para exibição e interação com o usuário. O microsserviço de "fights" é o ponto central da aplicação, garantindo que todas as interações entre heróis, vilões e o frontend sejam gerenciadas de forma eficiente e escalável.

Este projeto é um exemplo simples, mas ilustra bem os conceitos de microsserviços e a integração entre diferentes tecnologias. Sinta-se à vontade para explorar e modificar o código-fonte de acordo com suas necessidades. Esperamos que este exemplo seja útil e inspire novos projetos baseados em microsserviços.

### Diagrama do projeto

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

## Contribuindo

Contribuições são sempre bem-vindas!

Se você encontrar problemas ou tiver sugestões de melhorias, sinta-se à vontade para abrir um problema ou enviar uma solicitação pull.
