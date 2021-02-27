Time Management
=====================

Task:
-----------------------------------

Time management

There are roles: administrator and user

Administrator manages:
- users;
- categories;
- activities;
- confirms (assign) an activity to the user.

Administrator can view list of activities as well, make sorting:
- by name;
- by category;
- by number of users.
Can do filtering by category of activities. 
View a report about all users, number of activities and the time marked by the user.
User must have a page. User can have one or multiple of activities.
The user notes the amount of time used for each activity.
The user can send a request to add/remove an activity.

### Requirements
    • JDK 11 or higher
    • MySQL 8.0.22 or higher
### Installation
* Clone project from GitHub (git clone https://github.com/forbrock/timeMamagementServlet)
* Go to /src/main/resources/database.properties and fill in connection parameters to your database (url/login/password). 
* Execute script /src/main/resources/script.sql to create database schema and populate database with demo data
    
### Running
* cd to root project folder and execute command *mvnw clean tomcat7:run*
* After server start, application will be available by URL [http://localhost:8080/](http://localhost:8080/) 
* Use login "**admin@gmail.com**" and password "**111**" to log in with administrator rights.
* Use login "**user1@mail.com**" and password "**111**" to log in as user.
* Use login "**user2@mail.com**" and password "**111**" to log in as user.