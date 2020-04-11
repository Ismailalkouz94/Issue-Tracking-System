# Issue Tracking System

## Description
System to track issue, tickets and task with secured applications and api in adtional to user role and permissions. 

## Technologies
####Front-End
* Angular 8
####Back-End
* Spring boot
* Spring data jpa
* Spring security
* Restfull api
* Hibernate
* Maven


## Contents

The repository contains two folders, one for each component of the system:

* **front-end** is the backend services. It has a REST API to get and provide results.
* **fromt-end** is the UI. using angular .


## How to execute the application

These are the instructions:

**[Angular](https://angular.io/)**: to run angular on your device use the follwing steps and commands:  
 1-install [nodejs](https://nodejs.org/en/).  
 2-install angualr CLI using `npm install -g @angular/cli` [for more about angular CLI click here](https://cli.angular.io/).  
 3-go to from-end root project and run `npm install` to install needed dependencies.  
 4-to run and brows application run `ng serve --o`  
 
 **[Spring](https://spring.io/)**: to run server side application (back-end):  
 you can run spring boot application through multiple ways  
  from you preferd IDE click on run option  
  or using command  
  go to back-end root porject and run `./mvnw spring-boot:run`

### Notes
must be creating database with preferd name and credentials see `application.properties` to configuration details.  
in this application I'm used MYSQL  
you can change to any database but make sure to add database dependency in `pom` file and add other config to application.properties`
