[![Codacy Badge](https://api.codacy.com/project/badge/Grade/84561b4ccfd9445d98dc3107a22bd6dc)](https://app.codacy.com/gh/charlie-baba/user-api?utm_source=github.com&utm_medium=referral&utm_content=charlie-baba/user-api&utm_campaign=Badge_Grade_Settings)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/83e1ad5f624e4ea09df5b647909c6662)](https://www.codacy.com/gh/charlie-baba/user-api/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=charlie-baba/user-api&amp;utm_campaign=Badge_Grade)

# user-api

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/84561b4ccfd9445d98dc3107a22bd6dc)](https://app.codacy.com/gh/charlie-baba/user-api?utm_source=github.com&utm_medium=referral&utm_content=charlie-baba/user-api&utm_campaign=Badge_Grade_Settings)

This User-API article will walk you through building an application that allows you to create a user, email user to verify account, verifies the account and can deactivate the user without deleting the record.

For the purpose of demo, the user-api has been deployed to

    https://arc-user-api.herokuapp.com
You can jump to 'Email Configuration' section to continue without setting up the application.

## Prerequisites

Follow each step to build and deploy the app, or skip to.
* Basic Java knowledge, including an installed version of the JVM and Maven.
* Basic Git knowledge, including an installed version of Git.
* Postgres server installed on your device.

### How To run User-API?

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

The email properties are in the application.properties file (section: Email Config)
If you use a gmail account just update the "email.username" and "email.password" properties
If you use an alternate email provide also update the "email.host", and "email.port" tls properties.
The email configuration is for the source email when sending out activation and verification emails.
The email is sent to the actual email used in registering the users.


## Swagger

#### Html Docs
The swagger endpoint is "http://[localhost:8080]/swagger-ui/index.html", assuming that your port is 8080.
or "https://arc-user-api.herokuapp.com/swagger-ui/index.html" for the demo project

#### JSON Docs
The swagger endpoint is "http://[localhost:8080]/v2/api-docs", or "https://arc-user-api.herokuapp.com/v2/api-docs" for the demo project


## User endpoints

The user endpoints are listed below

Fetch Users

    GET | https://arc-user-api.herokuapp.com/api/users
    GET | http://localhost:[8080]/api/users
Create user

    POST | https://arc-user-api.herokuapp.com/api/user
    POST | http://localhost:[8080]/api/user

Update user

    PUT | https://arc-user-api.herokuapp.com/api/user/3
    PUT | http://localhost:[8080]/api/user/3

Delete user

    DELETE | https://arc-user-api.herokuapp.com/api/user/3
    DELETE | http://localhost:[8080]/api/user/3
