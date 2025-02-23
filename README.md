## Big chat brasil
O projeto foi desenvolvido utilizando arquitetura MVC e as seguintes tecnologias:  Maven, Java 17, Spring boot,  JPA e para o banco de dados foi utilizado Postgres.

Para rodar o projeto é importante possuir o JDK e o Maven instalados:
[Download Maven](https://maven.apache.org/download.cgi)
[Download JDK](https://www.oracle.com/java/technologies/downloads/)

Além disso, o banco de dados postgres pode ser baixado em: 
[Download postgres](https://www.postgresql.org/download/)

#### Como rodar o projeto
Faça o clone do projeto:

    git clone https://github.com/luizjurazek/bigchatbrasil.git

*** 
Com todas as ferramentas instaladas e o projeto clonado vamos começar criando uma base de dados no postgres, com o postgres rodando execute o seguinte comando no terminal: 

    CREATE DATABASE bigchatbrasil;

Após isso vamos configura o arquivo application.properties, que está localizado em [src/main/resources/application.properties](https://github.com/luizjurazek/bigchatbrasil/blob/master/src/main/resources/application.properties):

o arquivo tem a seguinte estrutura:

    spring.application.name=bigchatbrasil
    
    spring.datasource.url=jdbc:postgresql://localhost:5432/bigchatbrasil
    spring.datasource.username=usuario
    spring.datasource.password=senha
    spring.datasource.driver-class-name=org.postgresql.Driver
    
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

As linhas que devem ser editadas são:
1. spring.datasource.url=jdbc:postgresql://localhost:5432/bigchatbrasil
	Insira a url para o banco de dados postgres que está rodando, caso esteja rodando no caminho e porta padrão do postgres não é necessário alterar, além disso, caso a query para criar database não tenha sido alterada, não é necessário fazer a alteração.
	
2. spring.datasource.username=usuario
	Insira aqui o nome de usuário do banco de dados postgres.
	
3. spring.datasource.password=senha
	Insira aqui a senha do usuário do banco de dados postgres. 
	
*** 

Agora que temos o banco de dados configurado, vamos habilitar o cors para fazermos as requisições aos endpoints a partir do nosso browser utilizando o front end da aplicação, no arquivo [src/main/java/com/big_chat_brasil/bigchatbrasil/config/CorsConfig.java](https://github.com/luizjurazek/bigchatbrasil/blob/master/src/main/java/com/big_chat_brasil/bigchatbrasil/config/CorsConfig.java)

o arquivo tem o seguinte padrão:

    package com.big_chat_brasil.bigchatbrasil.config;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.CorsRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
    
    @Configuration
    public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
        }
    }

Basta editar a linha .allowedOrigins("http://localhost:3000"), inserindo como propriedade a url da qual será feita as requisições através do browser, por padrão o projeto front end roda na porta 3000 do localhost, caso não tenha sido alterado não é necessário realizar esse passo.

*** 

Agora na raiz do projeto atráves do terminal vamos executar o seguinte comando para instalar as dependências:

    mvn clean install 

E agora é só rodar o projeto com o comando:

    mvn spring-boot:run





*** 

Com o projeto rodando podemos ver a documentação gerada pela swagger no caminho: http://localhost:8080/swagger-ui/index.html#/


Os endpoints do projeto estão no arquivo: https://github.com/luizjurazek/bigchatbrasil/blob/master/endpoints.json

Basta importar o json em uma ferramenta como Insomina ou Postman.
*** 

Para rodar o front end do projeto basta seguir as instruções do seguinte repositório: https://github.com/luizjurazek/bigchatbrasil-front