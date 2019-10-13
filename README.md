
=======================
A sample project following TDD with Spring Boot and Spring Data JPA
                                            
In this project I am trying to implement the follwing requests as requested:

Build the backend service needed to support a web application to that allows a user to view transactions on any of the
accounts they hold.
Required functionality:
- View account list
- View account transactions


This project uses

- JDK 8
- Maven 3
- Spring Boot 2
- Spring Data + JPA + H2 Db
- JUnit 4 + Mockito

Detailed version indications you can find in the file `pom.xml`.

By  `mvn clean test`   all necessary libraries will be fetched, the project will be compiled and the test suite will be executed.

After this is working you can import the Maven project into your Java IDE 
(Spring Tool Suite o Intellij).

You can run the application (a REST server) in your IDE by running class `com.anz.wholesale.account.AccountTransactionServiceApplication` or on the command line after `mvn package` by `java -jar target/account-transaction-servcie-0.0.1-SNAPSHOT.jar`. In the last lines of the log you will see the number of the port (usually 8080), on which the server will listen. You can stop it by typing &lt;Ctrl/c&gt;.

- An overview of the REST endpoints is given at REST-API.md].
-
- Tests are run against preloaded in-memory H2 database. This is configured in file [src/resources/application.properties]


