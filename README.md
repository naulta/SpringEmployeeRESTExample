# Spring Boot Rest API Example
This is a simple Sprint Boot Rest API using Java 1.8 and Spring Boot v2.0.4. The application uses H2 and JPA to store Employee data in memory.

## API Behavior
Using the default properties, this API will launch at the web server located at http://localhost:8080. 
The API is hosted with the path /employees. All responses are provided in Json format. 
The table below lists all the API functions:

|  Path           | Method   | Comments                                                              |
|:----------------|:--------:|:----------------------------------------------------------------------|
| /employees      | GET      | Lists all active employees (Inactive are filetered).                  |
| /employees/{id} | GET      | Lists single employe (if active and actually exists)                  |
| /employees/{id} | PUT      | Updates single employee (if active and actually exists).              |
| /employees      | POST     | Creates an active employ with provided information                    |
| /employees/{id} | DELETE   | Updates an employee to inactive status. (Requires admin authorization)|

## API Interaction Example
```
curl -i -X GET localhost:8080/employee
curl -i -X GET localhost:8080/employee/1
curl -i -X DELETE localhost:8080/employee -u admin (password: abc123)
```
## Auhtorization (DELETE)
The API was written to require authorization for delete requests. This was implemented by requiring basic authentication as the admin user for this call. All other calls do not require this authentication.

``` user: admin password: abc123```

## Source Code Tree
The source code was written with the following structure. This is a basic pattern to implement a REST web service.

```
├── main
│   ├── java
│   │   └── com
│   │       └── kenzanexample
│   │           └── employeedemo
│   │               ├── EmployeeApplication.java
│   │               ├── EmployeeWebSecurityConfigurerAdapter.java
│   │               ├── controller
│   │               │   └── EmployeeController.java
│   │               ├── domain
│   │               │   └── Employee.java
│   │               ├── repository
│   │               │   └── EmployeeRepository.java
│   │               └── service
│   │                   └── EmployeeService.java
│   └── resources
│       ├── application.properties
│       ├── data.sql
│       ├── static
│       └── templates
└── test
    └── java
        └── com
            └── kenzanexample
                └── employeedemo
                    ├── EmployeeApplicationTests.java
                    └── controller
                        └── EmployeeControllerTest.java
```

## Running in a Docker container
A Dockerfile is included to be able to build a docker container for this application. The Dockerfile assumes the jar file to be built in the `/target` directory. The exposed port has been mapped to port 5000 on the host OS.

## Running from Dockerhub
This image can be found on Dockerhub as `anault/employee-demo`.
To run this follow the following commands:
```docker run -it anault/employee-demo```

This uses port 5000 for the web server. So all postman, curl, etc. calls would need to use 5000 instead of 8080.
