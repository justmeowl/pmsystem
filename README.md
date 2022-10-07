# pmsystem

PM System is a Master's Thesis devoted to the design and 
development of software for managing project executors based 
on network planning. The presented software contains useful 
optimizations that were developed during the work. They make it 
possible to simplify and speed up the design of employees. 
The system offers the user several options for the distribution 
of employees, which allows to speed up the project execution time
or avoid an excessive number of employees

# Build with
- **Java 8**
- **Maven** to build and run the project
- **Spring Web** to serve HTTP requests
- **Spring Security** for access-control
- **Spring JPA** for database access
- **MySQL** to create a database
- **Thymeleaf** for HTML-templating
- **JUnit** to test the project

<h2>Installation and Getting Started</h2>
<h3>Requirements:</h3>

- JDK 1.8 +
- Maven 3 +
- MySQL

<h3>Installation</h3>
1. Clone this repository
    ```shell
    git clone https://github.com/justmeowl/pmsystem.git
2. Package the application
    ```shell
   mvn package
3. Run MySQL and edit [application.properties](\src\main\resources\application.properties) file.
Change **spring.datasource.*** data so that it matches your database.
4. Execute the Jar
   ```shell
   java -jar ./target/pmsystem-0.0.1-SNAPSHOT.jar
5. Access the application at localhost:8080