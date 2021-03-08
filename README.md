# user-api
This User-API article will walk you through building an application that allows you to create a user, email user to verify account, verifies the account and can deactivate the user without deleting the record.

Follow each step to build and deploy the app, or skip to . 

## Prerequisites

* Basic Java knowledge, including an installed version of the JVM and Maven.
* Basic Git knowledge, including an installed version of Git.
* Postgres server installed on your device.

### How To use User-API?

Running the app from and IDE like intellij or Eclipse

    - clone the repo from https://github.com/charlie-baba/user-api.git
    - add the project to intellij or any java IDE
    - make sure to resolve all meaven dependencies (already included in the project).
    - run the UserApiApplicaiton class

    INFO: Initializing ProtocolHandler ["http://localhost:8080"]
    - The default port is 8080, it can be modified from the application.properties file (key = "server.port") 


Alternatively,
To build your application simply run:

    $ mvn package
    $ java -jar target/dependency/user-api.jar target/*.jar

That's it. Your application should start up on port 8080.

## Database Configuration

You need to have postgres server already installed on your device.

    - Create a database with name "user_data"
The default postgres port is 5432, can also be changed in the application.properties file (key = "app.jdbc.url") 
The database schema would be generated once you run the app.

## Email Configuration

The email properties are in the applicaiton.properties file (section: Email Config)
If you use a gmail account just update the "email.username" and "email.password" properties
If you use an alternate email provide also update the "email.host", and "email.port" tls properties.
The email configuration is for the source email when sending out activation and verification emails.
The email is sent to the actual email used in registering the users.


## Swagger

The swagger endpoint is "http://localhost:8080/v2/api-docs", assuming that your port is 8080.

## User endpoints

The user endpoints are listed below

Fetch Users

    GET | http://localhost:[8080]/api//users
Create user

    POST | http://localhost:[8080]/api/user
Update user

    PUT | http://localhost:[8080]/api/user/3

Delete user

    DELETE | http://localhost:[8080]/api/user/3
