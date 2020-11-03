# waes

## Prerequisite
- Java JDK, Favourite IDE
- Maven
- Make sure Port 8081 is available. 

### Run 

```
on bash, project root folder

- mvn clean install
- jar will be generated.

java -jar target/web-1.0-SNAPSHOT.jar or
mvn spring-boot:run

- You can make calls to endpoints from **http://localhost:8081/v1/diff/**

```
## Tools & Frameworks
- Spring Framework 2.3.4
- Java 11
- Maven 3.0.0
- H2 Database 
- Hibernate
- Lombok 

For testing purposes;
- JUnit
- Spring Runner

## Solution

Small application includes internal h2 database with 3 endpoints which sits on 2 controller class. 

1) BinaryDataRecordController; which has 2 endpoints that takes BinaryDataRecordRequest object which is base64 data should be set. 
2) BinaryDataSummaryController: which has 1 endpoint that displays the final solution. 

### Testing

both web layer and service layer are tested with covarage of 85% classes with 85% lines covered. 





