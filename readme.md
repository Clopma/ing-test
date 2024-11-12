Spring Boot Java 19 application that exposes a REST api consisting of two endpoints:

* GET /api/interest-rates (get a list of current interest rates)
* POST /api/mortgage-check (post the parameters to calculate for a mortgage check)

Run the application by running IngTestApplication.java, no parameters required.

A Open API codegen generated API will be exposed at localhost:8080/api/ and can be accessed through SwaggerUI on 
http://localhost:8080/api/swagger-ui/index.html

~~A valid JWT token is required to access the resources, you can generate yours using the secret on application.yml~~

The security part is not completed as I didn't have time, but the implementation was working except for the Swagger UI.
I commented the security on the config class so you can check it working on Postman.

The data is stored on a H2 database accesible from: http://localhost:8080/api/h2-console/ using:
* user: sa
* password: password
* url: jdbc:h2:mem:localdb
