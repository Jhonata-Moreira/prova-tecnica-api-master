# Prova técnica API - Jhonata Filizola

Este é o projeto de API que deve ser automatizado para o desafio.

##  Requisitos
 * Java 8 JDK deve estar instalado
 * Maven deve estar instalado e configurado no path da aplicação
 
## Como subir a aplicação localmente?

Na raiz do projeto, através de seu Prompt de Commando/Terminal/Console execute o comando 

```bash
mvn clean spring-boot:run
```

A aplicação estará disponível através da URL [http://localhost:8080](http://localhost:8080)

Você pode trocar a porta da aplicação, caso a _8080_ já estiver em uso, adicionando a propriedade JVM `server.port`.

## Como executar os testes automatizados?

Para executar os testes será necessário, estar com a aplicação rodando localmente. E será necessário ir na pasta test> java > simulacaoCredito > runner, depois abrir o arquivo "TestRunner" e clicar para rodar a classe de testes.

## Observação:

Alguns testes não irão passar, por motivos, de não estarem de acordo com as regras de negócios exigidas na documentação e nos detalhes do desafio passado.