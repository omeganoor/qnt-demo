# qnt-demo
Demo project for QNT using Spring Boot

Please develop restful api application with Java Spring Boot:

- Create restful Api with CRUD operation on one entity, Contact, that contains name, email, phone and address with
h2/mysql/postgres using Spring Data JPA/JDBC or other ORM you're familiar with.
  for this problem you can find the solution on ContactController.java.

- Create an endpoint that fetch 2 data concurrently from https://jsonplaceholder.org JSONPlaceholder | JSON Placeholder - www.jsonplaceholder.org, for example fetch Users data and Posts data. Calculated the total time it needs
to completed the request. 
  and for this you can find the solution on JSONPlaceholderController.java and for the rest template api you can find it on `client` package

- Add rate-limiter to those endpoint for example, that limit to 10 request in 60 seconds. The 11th request within 60 seconds window will return 429 status code and an error message. Use your own algorithm. 
  you can find rate limiting config on class RateLimiterConfiguration.java

- Add JWT validation for those endpoints.
  for JWT its not implement yet because time limitation to take the test, but i attach some source code for JWT token validation from my previous project. 
you can find it on `config.security.auth.jtw` package

## Development server

To run the apps, first u need to run `mvn clean install` then need to run `mvn spring-boot:run` command in your terminal or cmd using root of this folder.
Navigate to `http://localhost:8080/docs/swagger-ui.html`. The app will automatically redirect to swagger ui for this apps. u can access
all the end point directly from there.

## Build
To build the apps, you can just run `mvn package` command to build the project. The build artifacts will be stored in
the `target/` directory.

## Deploy Apps
To run apps from the terminal , you can just run `java -jar target/demo-0.0.1-SNAPSHOT.jar` from your ide terminal.

##

NB : You need to install Project Lombok plugin in your intellij idea to avoid error builder on the object entity.