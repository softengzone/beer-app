---------------------
-     Beer App      - 
---------------------

1. Basic functionality
	- on page load a service must load random beer
	- on clicking on the 'Show Another Beer' button, details of another random beer must be displayed

2. RESTful API description
2.1 Beers
	URL: http://localhost:8080/BeerApp/rest
	- /beer/{id}   GET    - return Beer by ID
	- /beer        GET    - return list of all Beers
	- /beer        POST   - create a new Beer
	- /beer/{id}   PUT    - update an existing Beer
	- /beer/{id}   DELETE - delete a Beer
	- /beer        DELETE - delete all Beers
	- /beer/total  GET    - return total number of Beers
	
2.2 Breweries
	URL: http://locahost:8080/BeerApp/rest
	- /brewery/{id} GET    - return Brewery by ID
	- /brewery      GET    - return all Breweries
	
3. Technologies stack used
	- Spring Boot 2.0.0.RELEASE
	- Hibernate 5.2.14.Final
	- H2 database 1.4.196
	- HikariCP 2.7.8
	- JDK 1.8
	- Maven 3.1
	- AngularJS 1.6.10
	- Bootstrap 4
	- Eclipse Oxygen
	
4. Service components
	- Back-end RESTful service responsible for data persistence and maintenance. The service is implemented using Spring Boot,
	  JPA, Hibernate and H2 in-memory database. Thanks to Spring Boot, the system can be run without having any external 
	  servlet container. The H2 database was used as it comes with an application and it doesn't require to 
	  setup any standalone database servers. 
	  This part has two RESTful services, one to manage the Beers and the other to provide access to Brewery Location information.
	- Front-end solution was implemented using AngularJS 1.6.10 as it's very powerful and easy to integrate with RESTful 
	  based web services.
	  
5. Installation
   Build project with maven and run java -jar beer-app-1.0.0.jar
   
6. Database
   The H2 database is accessible with the following URL http://8080/BeerApp/h2-console
   JDBC URL: jdbc:h2:mem:test
   User Name: sa
   Password: