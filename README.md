# Chat Messaging Application

Create and join chat rooms to send messages and talk with other people.

![Image of home page](./docs/images/home.JPG?raw=true)

# Demo

<a href="https://chat-app-qapi.onrender.com/">Link to live demo</a>

Note! The application might take some time to load for the first time, if it hasn't been used in a while.

The deployment uses a cloud hosted PostgreSQL database. It has 1 GB storage so keep that in mind while using the application.

# About

MVC Backend application developed with Spring Boot and Java.

# How to use

- Create a new user
- Sign in
- Go to chat rooms page
- Create a chat room
- Join the room
- Send messages
- Refresh chat page to edit and delete your messages

To verify if messages are sent in realtime:

- Create another user
- Sign in with another browser to have 2 different sessions with different users
- Join the room
- Send messages and check if they show in the other session

You can also create more rooms. All rooms have their own message history.

# Features

- <strong>Authentication</strong>
- <strong>User login and signup</strong>
- <strong>Create chat rooms.</strong> Only room creator can edit and delete it.
- <strong>Join chat rooms.</strong> All users can view and join chat rooms.
- <strong>Send messages.</strong> Sent messages show in realtime to all users in the chat room. Message sender can edit and delete their messages.
- <strong>Message history.</strong> Previous messages will be loaded when user joins a chat room.
- <strong>Timestamps.</strong> Chat room and message creation date and time will be saved and can be seen.

# Tech and tools

- Developed with Spring Boot framework.
- Java as main language.
- Client code uses JavaScript.
- HTML views created with Thymeleaf template engine.
- MVC controllers contain endpoint-specific code and return views to users depending on requests.
- Application secured with Spring security. All users have USER role. Admin role is not needed.
- Spring JDBC for database operations. Data Access Objects with custom SQL queries that use Spring JdbcTemplate. No Spring Data JPA repositories.
- WebSocket server and WebSocket client. In this project, WebSocket is used to send user messages in realtime to all subscribed clients, so users don't have to refresh page every time a new message is sent.
- Server-side validation for user inputs.
- Database connection with JDBC to PostgreSQL database.
- Database schema is generated on application startup and it is defined in schema.sql file.
- Application properties use environment variables to load database configurations (url, password, etc.)
- Separate profile-specific application properties for testing. This can be loaded with `SPRING_PROFILES_ACTIVE=testing` environment variable.
- Custom error page. Default whitelabel error page is disabled for users.

# How to run locally

You need a PostgreSQL database server in order to run the application locally.

Before running:

- Create a PostgreSQL database.
- Change `application.properties` file if needed.
- Set environment variables to match `application.properties` configurations
- Database connection uses JDBC. Database URL should start with `jdbc:postgresql`
- Example database URL: `jdbc:postgresql://localhost:5433/test_db`
- `schema.sql` generates database tables. `schema-production.sql` is for production build.

In production build, you need to set environment variable `SPRING_PROFILES_ACTIVE=production`. With this, `application-production.properties` will be used. When running the application locally, default profile will be used instead.

# Database

This project uses PostgreSQL as database.

`ERD.json` file in project root has the structure of the database in JSON format.

Database entities generated by `ERD.json` below

![Database Entities](./docs/images/database_entities.JPG?raw=true)

Database diagram generated by pgAdmin below

![Database Diagram](./docs/images/database_diagram.JPG?raw=true)

# Tests

Test data for tests is created in `ChatAppApplication.java` when `SPRING_PROFILES_ACTIVE=testing` environment variable is set.

To run tests correctly, you need to set the environment variable for all JUnit tests. Without test data, tests will not run correctly.

In addition, you can use `application-testing.properties` for application properties only for tests, such as a separate testing database.

When `SPRING_PROFILES_ACTIVE=testing` environment variable is set, Spring will use `application-testing.properties` instead of default `application.properties`.
