# API para gerenciamento de reservas de livros

API Rest desenvolvida para melhorar os conhecimentos sobre Java Web.

É um sistema de gerenciamento de reservas de livros que contempla o CRUD completo de usuários e reservas, buscas, estatísticas e bloqueio de livros.

A lista de livros para buscar reserva é obtida a partir da API livre da [OpenLibrary](https://openlibrary.org/developers/api), sendo o primeiro resultado da pesquisa o livro que será incluído na reserva.

A documentação dos endpoints é feita via Swagger e pode ser acessada no link abaixo após o start do servidor:

> http://localhost:80/swagger-ui.html

## 🛠️ Desenvolvida com

* [IntelliJ](http://www.dropwizard.io/1.0.2/docs/) - A IDE do ❤️
* [Maven](https://maven.apache.org/) - Gerenciador de dependências
* [SpringBoot](https://start.spring.io/) - Framework para aplicações web
* [H2 DataBase](https://www.h2database.com/html/main.html) - Banco de dados em memória
* [Lombok](https://projectlombok.org/) - Facilitar a criação de construtores
* [Swagger](https://swagger.io/tools/open-source/open-source-integrations/) - Listagem endpoints da API
* [WebClient](https://spring.io/guides/gs/reactive-rest-service/) - Consumir API externa
* [JSON-Java](https://www.baeldung.com/java-org-json) - Trabalhar com Json

## 📄 Licença

The MIT License (MIT)

---
⌨️ com ❤️ por [Allan Garcia Ferreira](https://github.com/allan201gf) 