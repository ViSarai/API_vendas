
# API de Vendas

Api para gestão de produtos, clientes e pedidos.
Projeto para afins academicos com objetivo de criar uma api rest full em java com spring, autenticação e mysql.
## Tecnologias Usadas

- JAVA
- SPRINGBOOT 3
- AUTHENTICATION JWT com SPRINGBOOT SECURITY
- MYSQL
- H2(banco de dados em memória)
- REST com SPRINWEB
- SPRINGBOOT JPA
- SPRINGBOOT DOC com OPENAPI
## Inicialização/Instalação

para rodar api na sua máquina clone o projeto do meu github - https://github.com/ViSarai/API_vendas.
Para rodar perfeitamente o projeto será necessario.

- JAVA JDK 17
- INTELLIJ IDEA COMMUNITY OU ULTIMATE
- MAVEN 3.9.6 ou SUPERIOR
- POSTMAN OU INSOMINIA
## FEATURES
### Documentação da API

#### Request para autenticar-se

```http
  POST /api/auth/login/signup
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `string` |um email - **Obrigatório** |
| `password` | `string` |uma senha - **Obrigatório** |
| `role` | `string` |sua permissão ("USER" ou "ADMIN")  - **Obrigatório** |

#### Recuperar token para poder fazer request(POST,PUT,PATCH,DELETE)

```http
  POST /api/auth/login/token
```

| Parâmetro   | Tipo       |                                    |
| :---------- | :--------- | :------------------------------------------ |
| `email`      | `string` |
| `password`      | `string` |

#### Observações

request obrigatório para ter um token e poder fazer request do tipo(POST,PUT,PATCH,DELETE) e sua permissão ser "ADMIN", os GET basta esta autenticado. O restantes dos requests podem ser acessados atraves do SWAGGER - http://localhost:8081/swagger-ui/index.html#/.

## TODO
- V2
- TESTES UNITARIOS
- MICROSERVIÇOS
- DOCKER
## CONTATOS
- GITHUB - https://github.com/ViSarai/
- LINKEDIN - https://www.linkedin.com/in/viniciussarai/
- GMAIL - saraivinicius@gmail.com
