# Willpowered students

This application was made as a final project for a Full-stack Web-Development bootcamp NOVI Hogeschool in the Netherlands

The application consists of 2 parts:

- wps-fe The front-end part
- wps-be The back-end part

The back-end part functions as an API for the web-app. With several endpoints the web-app feeds and pulls data from the database through this spring-boot application. The
web-app functions a project management platform where peole can organise projects together. De app is also a place to find information a willpowered students and the
administrators.

The backend part secures the data in a PostgreSQL database after adjusting it to our data models. People can create their accounts through the web app which send the details to the backend.


# Hosted on localhost:8080/

- authenticate(GET)
- /register(POST)
- users(GET)
  /{username}(GET, PUT, POST, DELETE)
  /{username}/authorities(GET, POST)
  /{username}/authorities/{authority}(DELETE)
  /password (PUT)
- blogs (GET, POST)(get-var: blogOwner)
  /{id}(PUT, DELETE)
- category(POST)
  /{id}(GET, PUT, DELETE)
  /all(GET)
- projects(GET, POST)(get-var: categoryId, collaborator, projectOwner)
  /{id}(GET, PUT, DELETE)
  /all (GET)
- tasks (GET, POST)(get-var: parentProjectId, parentBlogId, username)
  /{id}(GET, PUT, DELETE)
  /all(GET)
- comments(GET,POST)(get-var: parentProjectId, parentBlogId, username)
  /{id}(GET, PUT, DELETE)
  /all(GET)
- votes(POST)
- portal(POST)
  /{id}(GET, PUT, DELETE)
  files(POST)
  /{id}(GET, DELETE)
  /all(GET)
  /{username}/download (GET, PUT, DELETE)