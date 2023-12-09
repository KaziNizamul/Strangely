# Strangely


Strangely is an innovative web platform that facilitates connections between strangers through events, promoting new friendships and social interactions. It provides an easy-to-use interface for users to engage in events and meet new people.

##

  ![Coverage(https://img.shields.io/badge/coverage-85%25-blue)](https://img.shields.io/badge/coverage-85%25-blue)
  ![pipeline(https://img.shields.io/badge/pipeline-passed-green)](https://img.shields.io/badge/pipeline-passed-green) 

<br><hr>

## Table of Contents
[**Documentation**](#documentation)
- [Overview](#overview)
- [1.1 Usage Scenario](#usage_scenario) 
    <br>

- [Backend Setup](#backend-setup)
    - [Libraries Used](#libraries-used)
    - [Prerequisites](#prerequisites)
    - [Getting Started](#getting-started)
    - [References of technologies used](#References-of-technologies-used)

    <br>

- [ Frontend Setup](#strangely---ui)
  - [Prerequisites](#prerequisites-1)
  - [Getting Started](#getting-started-1)
  - [Commands](#commands)
  - [References of technologies used](#reference-of-technologies-used)
  - [Opinionated Dev Environment](#opinionated-dev-environment)
    <br>

- [1.2 Build/Deployment/Local run instructions](#build_instructions)
    

2. [**CI/CD**](#ci/cd)

    [2.1 Build](#ci_cd_build)

    [2.2 Pipeline Stages](#pipeline_stages)

    [2.3 Build Stage](#build_stages)

    [2.4 Test Stage](#test_stages)

    [2.5 Publish Stage](#publish_stages)

    [2.6 Deploy Stage](#deploy_stages)

    [2.7 Docker Container](#docker_container)

    [2.8 Deployment Access](#deployment_access)


3. [**Test**](#test)

    [3.1 Coverage](#coverage)

    [3.2 Integration tests](#integration_test)
    
    [3.3 Test best practices](#test_best)

    [3.4 TDD adherence](#tdd_adherence)

4. [**Quality**](#quality)

    [4.1 Architecture smells](#architecture_smell)
    
    [4.2 Design principles](#design_principles)

    
    [4.3 Design smells](#design_smell)

    [4.4 Implementation smells](#implementation_smell)

    [4.5 Other clean code practices](#other_clean)

5. [**Miscellaneous**](#miscellaneous)

    [5.1 Dependencies](#dependencies)
    
    <br>
6. [**Demo**](#demo)
    [5.2 Demo Video](#demo)

- [Features](#features)
- [Developers](#developers)
- [License](#license)

***
# ☑️ Documentation <a name = "documentation"></a>

## Overview

Strangely is designed to create a comfortable and welcoming platform for people looking to expand their social circles and experience new events.

***

## ▪️Usage Scenario <a name = "usage_scenario"></a>

##### ⏺  User Registration and Login:
- **Sign Up with Ease:** Users can effortlessly register using their email or opt for a quicker sign-up with their Google account.

- **Simple Login Process:** Once registered, users can log in using their chosen credentials or with their Google account.

##### ⏺  Password Management:
- **Forgot Password? No Worries:** If a user forgets their password, they can click on the "Forgot Password" option, enter their username, and receive a password reset link.

##### ⏺  Homepage Exploration:
- **Explore Categories:** Upon login, users will land on the homepage showcasing various categories. They can even add new categories that interest them.

##### ⏺  User Profile Settings:
- **Customize Your Profile:** A convenient left-hand panel offers options for "Profile" and "Connections." Users can click on "Profile" to easily modify settings such as username, email, and phone number.

##### ⏺  Connections and Chat History:
- **Stay Connected:** In the "Connections" section, users can see their connected contacts and review their chat history with them.

##### ⏺  Category-specific Posts:
- **Discover and Share:** Clicking on individual categories reveals related posts. Users can view posts by others or contribute by adding their own with captions or images.

##### ⏺  Interactive Post Engagement:
- **Express Yourself:** Each post offers options to like, dislike, love, and chat. Users can react to posts and connect with others through our built-in messaging system.

##### ⏺  Automatic Chat Groups:
- **Post-Related Chat Groups:** When a user successfully posts, a chat group is automatically created. The author becomes the group admin.

##### ⏺  Seamless Navigation:
- **Navigate with Ease:** On the post page, a left-hand section allows users to effortlessly return to categories.

##### ⏺  Intuitive Navbar:
- **Navigate Anytime:** The top navigation bar includes buttons for the homepage, connections (chats), and logout.

##### ⏺  Chat and Connection Management:
- **Connect and Communicate:** On the chat page, users can manage connections, add new ones, or remove existing ones. The messaging system enables individualized communication with connections.
  
-  **Join Post-Related Groups:** Viewers of a post can request to join the chat group. They can send a direct message to the author, asking for an invitation to the group.

- **One-on-One Communication:** Users can directly message anyone within the platform. This allows for private and direct communication between users.

To easily understand use case scenario we have created the Use Case UML diagram

![Image](./maintainance_&_report/readme/Strangely_USECASE_Scenario_4.jpeg "Use Case Diagram")

<br> <hr>
# Strangely - Backend


## Prerequisites

- [Java 17](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)
- [Maven 3.9.5](https://maven.apache.org/download.cgi)
- [Lombok 1.18.22](https://projectlombok.org/)
- [SQL](https://dev.mysql.com/downloads/workbench/)

```
## Getting Started
Follow these steps to set up and run the project locally.

### 1. Clone the Repository
```bash
 git clone git@git.cs.dal.ca:courses/2023-fall/csci-5308/Group14.git 
 OR
 git clone https://git.cs.dal.ca/courses/2023-fall/csci-5308/Group14.git
```
### 2. Navigate to the project directory
```bash
cd Group14/Backend
```
## Commands

<table>
<tbody>
<tr>
<th>&nbsp;title</th>
<th>command</th>
<th>&nbsp;description</th>
</tr>
<tr>
<td>build/td>
<td>

```
mvn clean install -Dskiptests
```

</td>
<td>Load Dependencies and build project</td>
</tr>
<tr>
<td>Run on localhost</td>
<td>

```
Run the 'main' function BackendApplication class present in BackendApplication.java
```

</td>
<td>Will run the backend application on localhost:8080. To run any api just hit "http://localhost:8080/api_url" with required body</td>
</tr>
</tbody>
</table>

### Libraries Used

The backend utilizes a range of libraries, including:

- spring-boot-starter-data-jdbc
- spring-boot-starter-oauth2-client
- spring-boot-starter-oauth2-resource-server (version: 6.1.5)
- spring-boot-starter-security
- spring-boot-starter-web
- spring-ldap-core
- spring-security-ldap
- mysql-connector-j
- spring-boot-starter-data-jpa
- spring-boot-devtools
- spring-boot-starter-test
- spring-security-test
- spring-security-oauth2-resource-server: `v6.1.5`
- lombok-maven-plugin: `v1.18.20.0`
- mapstruct: `v1.5.5.Final`
- mapstruct-processor: `v1.5.5.Final`
- spring-boot-starter-mail: `v3.1.4`
- velocity: `v1.7`
- jakarta.validation-api: `v3.0.2`
- lombok: `v1.18.30`
- spring-context-support: `v6.0.12`
- firebase-admin: `v9.2.0`
- spring-security-oauth2-autoconfigure: `v2.6.8`
- java-jwt: `v4.4.0`
- spring-web: `v6.0.12`
- spring-security-web: `v6.1.4`
- spring-websocket (version: 6.0.13)
- spring-messaging (version: 6.0.13)
- junit
- mockito-core

## Application Properties Overview
### Security Configuration
 
- spring.security.user.name
- spring.security.user.password
- spring.security.oauth2.client.registration.google.client-id
- spring.security.oauth2.client.registration.google.client-secret
- spring.security.oauth2.client.registration.google.scope
 
### DataSource Configuration
 
- spring.datasource.url
- spring.datasource.username
- spring.datasource.password
 
### JPA Configuration
 
- spring.jpa.show-sql
- spring.jpa.properties.hibernate.format_sql
- OAuth2 Resources Configuration
- spring.security.oauth2.resourceserver.jwt.issuer-uri
- spring.security.oauth2.resourceserver.jwt.jwk-set-uri
 
### Email Configuration

- spring.mail.protocol
- spring.mail.host
- spring.mail.port
- spring.mail.username
- spring.mail.password
- spring.mail.properties.mail.smtp.auth
- spring.mail.properties.mail.smtp.starttls.enable
- spring.mail.properties.mail.smtp.ssl.enable
- spring.mail.properties.mail.smtp.starttls.required
- mail.smtp.starttls.enable

### References of technologies used:
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Framework](https://spring.io/projects/spring-framework)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring LDAP](https://spring.io/projects/spring-ldap)
- [Spring Cloud](https://spring.io/projects/spring-cloud)
- [MySql Connector/](https://dev.mysql.com/doc/connector-j/en)
- [Project Lombok](https://projectlombok.org/)
- [MapStruct](https://mapstruct.org)
- [Jakarta Validation API](https://jakarta.ee/specifications/validation/3.0/)
- [Java JWT](https://github.com/auth0/java-jwt)
- [Firebase Admin SDK](https://firebase.google.com/docs/admin/setup)
- [Apache Velocity](https://velocity.apache.org/engine/1.7/user-guide.html)
- [Junit](https://junit.org/junit4/)
- [Mockito](https://site.mockito.org)

***

# Strangely - UI

### Getting Started

### 1. Clone the Repository
```bash
 git clone git@git.cs.dal.ca:courses/2023-fall/csci-5308/Group14.git 
 OR
 git clone https://git.cs.dal.ca/courses/2023-fall/csci-5308/Group14.git
```
### 2. Navigate to the ui directory
```bash
cd Group14/ui
```
### 3. Install dependencies 
```bash
 npm install 
 or
 yarn install
```
### 4. Start the development server
```bash
npm dev
or
yarn dev
```
## Prerequisites

- [NodeJS](https://nodejs.org/en) `v18.x`
> you can use [nvm](https://github.com/nvm-sh/nvm) to manage node versions

check if exists

```sh
node --version
vite --version
```

NOTE: if you are trying to consume deployed backend url in frontend, then you need to connect to Dalhousie VPN and consume this deployed backend url 

i.e `'http://172.17.1.123:8073'` in file : `ui/src/services/urls.js`

else if you want to use locally ran backend service 

then replace `export const baseURL = 'http://172.17.1.123:8073';` with 
`export const baseURL = 'http://localhost:8080';`
## Commands

<table>
<tbody>
<tr>
<th>&nbsp;title</th>
<th>command</th>
<th>&nbsp;description</th>
</tr>
<tr>
<td>development</td>
<td>

```
vite
```

</td>
<td>starts development server</td>
</tr>
<tr>
<td>build</td>
<td>

```
vite build
```
</td>
<td>generates bundle files in <u>build</u> directory</td>
</tr>
</tbody>
</table>

## Reference of technologies used

- [React](https://react.dev/)
- [React Router](https://reactrouter.com/en/main)
- [Redux](https://redux.js.org)
- [AntDesign](https://ant.design)
- [Vite](https://vitejs.dev)
- [Axios](https://axios-http.com)
- [Emotion](https://emotion.sh/docs/introduction)
- [Material-UI](https://mui.com)
- [React Font Awesome](https://fontawesome.com/v5/docs/web/use-with/react)
- [React Oauth](https://www.npmjs.com/package/@react-oauth/google)
- [Sass](https://sass-lang.com)
- [Classnames](https://www.npmjs.com/package/classnames)
- [Redux DevTools Extension](https://github.com/zalmoxisus/redux-devtools-extension)
- [Redux Thunk](https://github.com/reduxjs/redux-thunk)

## Opinionated Dev Environment

- Use `Visual Studio Code - v1.82.2`.
- Install extensions
    - [ESLint](https://marketplace.visualstudio.com/items?itemName=dbaeumer.vscode-eslint)
    - [Prettier - Code formatter](https://marketplace.visualstudio.com/items?itemName=esbenp.prettier-vscode)


# ☑️ CI/CD <a name = "ci/cd"></a>

#### ▪️Build <a name = "ci_cd_build"></a>
Continuous Integration and Delivery
This application uses GitLab CI/CD for automating builds, tests, and deployments.

#### ▪️Pipeline Stages <a name = "pipeline_stages"></a>
The `.gitlab-ci.yml` file defines the following stages:

#### ▪️Build Stage <a name = "build_stages"></a>
This stage has two parallel jobs:

- **backend-build-job:** Builds the Spring Boot backend application using Maven.
  - Uses Maven Docker image.
  - Runs `mvn clean install` to compile and package the backend.
  - Saves the generated JAR file as a build artifact.

- **frontend-build-job:** Builds the React frontend application using Yarn.
  - Uses Node Docker image.
  - Runs `yarn install` to install dependencies.
  - Runs `yarn build` to create a production build.
  - Saves the frontend build artifacts.

#### ▪️Test Stage <a name = "test_stages"></a>
- **backend-test-job:** Runs JUnit tests for backend code.
  - Uses Maven Docker image.
  - Runs `mvn verify` to execute unit tests.
  - Frontend tests are commented out for now.

#### ▪️Publish Stage <a name = "publish_stages"></a>
Builds Docker images for the backend and frontend.
- Tags images with Git commit SHA for traceability.
- Pushes images to Docker Hub repository.
![Image](./maintainance_&_report/readme/dockerHub.png "Docker hub")


#### ▪️Deploy Stage <a name = "deploy_stages"></a>
Deploys the application to the production server (IP `172.17.1.123`).
- Connects to the server via SSH using a private key.
- Pulls the latest images from Docker Hub.
- Stops any existing containers.
- Starts new containers with the latest images.

#### ▪️Docker Containers <a name = "docker_container"></a>
- `my-app-backend` for the backend, exposes port `8073`.
- `my-app-ui` for the frontend, exposes port `8074`.
  - Port `8074` on the server maps to port `5173` inside the frontend container.

#### ▪️Deployment Access <a name = "deployment_access"></a>
- Backend: [http://172.17.1.123:8073](http://172.17.1.123:8073) (for internal consumption)
- Frontend: [http://172.17.1.123:8074](http://172.17.1.123:8074) (Publicaly exposed)

<br><hr>
# ☑️ Test <a name = "test"></a>

## ▪️Coverage <a name = "coverage"></a>
Code coverage report was generated using IntelliJ. Our code coverage for service class is 85%.
## ▪️Integration tests <a name = "integration_test"></a>
We have written multiple integration tests.
## ▪️Test best practices <a name = "test_best"></a>
We have followed best practices for mocking the dependent classes. System under test is beign tested in isolation.
## ▪️TDD adherence <a name = "tdd_adherance"></a> 
For some of our APIs we have followed Test driven developement approach.

<br><hr>

# ☑️ Quality <a name = "quality"></a>

## ▪️Design principles <a name = "design_principles"></a>


- <h4> Single Responsibility Principle </h4>

  <p> The Single Responsibility Principle dictates that a class should have a singular responsibility, promoting the separation of concerns and facilitating the modification, testing, and reuse of code. This principle is being adhered to in several instances where we are creating distinct controllers and services for various stakeholders. </p>

- <h4>Open/Closed Principle (OCP) </h4>

  <p>"A class should be open for extension but closed for modification" means that a class's behavior can be extended without modifying its source code. We have implemented this principle in our application by creating separate classes for different types of users. </p>

- <h4>Liskov Substitution Principle (LSP)</h4>

  <p>Subtypes should be able to replace their base types without changing the correctness of the program. As a result, we ensure that if any class implements an abstract class or interface, it can fully replace its parent class.</p>

- <h4>Interface Segregation Principle (ISP)</h4>

  <p>The Interface Segregation Principle advocates for the use of smaller, more specialized interfaces instead of larger, more complex ones. This approach enables easier maintenance, testing, and code reuse. To implement this principle, we have designed separate interfaces for specific tasks. For example, we have a dedicated mail service interface responsible solely for sending mails, without including unnecessary methods that users would have to implement during implementation. By segregating interfaces in this way, we ensure that our application remains organized and manageable.</p>

- <h4>Dependency Inversion Principle (DIP)</h4>

  <p>To promote modularity and extensibility, the principle suggests that modules should rely on abstractions such as interfaces and abstract classes. To reduce dependencies on individual classes and decouple the components, we employ numerous interfaces and classes. As we're using Spring Boot for our application, we don't need to be too concerned about this principle because the framework is based on SOLID principle.</p>

<br> <hr>

## ▪️Architecture smells <a name = "architecture_smell"></a>

### The detailed excel sheet can be found here 
- The Excel sheet : [ArchitectureSmellsFinal.xlsx](./maintainance_&_report/readme/ArchitectureSmellsFinal.xlsx)
- Preview: 
![Image](./maintainance_&_report/readme/ArchitectureSmells.png)
- These are all false positive smells, Thier reason for being false positive is shown below. 


## ▪️Design smells <a name = "design_smell"></a>

### The detailed excel sheet can be found here 
- The Excel sheet : [DesignSmellsFinal.xlsx](./maintainance_&_report/readme/DesignSmellsFinal.xlsx)
- Preview:
![Image](./maintainance_&_report/readme/DesignSmells.png)
- These are all false positive smells, Thier reason for being false positive is shown below.


## ▪️Implementation smells <a name = "implementation_smell"></a>

### The detailed excel sheet can be found here 
- The Excel sheet : [ImplementationSmellsFinal.xlsx](./maintainance_&_report/readme/ImplementationSmellsFinal.xlsx)
- Preview:
![Image](./maintainance_&_report/readme/ImplementationSmells.png)
- These are all false positive smells, Thier reason for being false positive is shown below.

## ▪️Other clean code practices (FrontEnd) <a name = "other_clean"></a>
1.  We've separated CSS and JavaScript for improved maintainability and readability.
2.  Our vite.config.js is organized to enhance readability and manage different aspects of the build process efficiently.
3.  In LoginSignup.constant.js, we've defined action types and string constants, adhering to the DRY principle and ensuring consistency.
4.  Our reducers, like in LoginSignup.reducer.js, define an initial state, providing a clear understanding of our data structure and default values.
5.  We use a clear structure with switch statements in our reducers for handling different action types, enhancing code readability.
6.  Our action creators, as seen in LoginSingup.actions.js, provide a consistent method for dispatching actions, facilitating maintainability.
7.  Across our files, we use descriptive variable and function names, making our code self-explanatory.
8.  We've organized our files to separate concerns (constants, actions, reducer), aiding in maintainability and scalability.
9.  We maintain a consistent coding style across all files to ensure readability and maintainability.
10. In complex logic sections, like in our reducers, we use comments to clarify the purpose of code blocks.
11. We use constants instead of hard-coded values to avoid magic numbers/strings, enhancing code readability and maintainability.1
12. Our functions are designed to adhere to the Single Responsibility Principle.
13. We ensure proper indentation and spacing in our code formatting for enhanced readability.
14. We use arrow functions for a concise and modern approach in our JavaScript files.
15. In our state management, as seen in reducers, we handle the state immutably, crucial for Redux-like architectures.
16. We implement robust error handling mechanisms throughout our code.
17. Consistent Naming Conventions: Our naming conventions are consistent, aiding in understanding the codebase.
18. We separate logic (JS files) from presentation, following best practices in frontend development.
19.  Our code utilizes modern JavaScript features, reflecting up-to-date and efficient coding practices.
20. Our file structure is designed to be scalable, accommodating the growth of the project.

# ☑️ Miscellaneous <a name = "miscellaneous"></a>


## ▪️Dependencies <a name = "dependencies"></a>
### FrontEnd Dependencies

<p>• @emotion/react: ^11.11.1 - Library for CSS-in-JS styling solution, providing great performance and flexibility.</p>
<p>• @emotion/styled: ^11.11.0 - A lightweight library for writing CSS styles with JavaScript.</p>
<p>• @fortawesome/free-solid-svg-icons: ^6.4.2 - Solid style icon set from Font Awesome.</p>
<p>• @fortawesome/react-fontawesome: ^0.2.0 - Font Awesome integration for React applications.</p>
<p>• @mui/icons-material: ^5.14.18 - Material Design icons for MUI.</p>
<p>• @mui/material: ^5.14.18 - React components that implement Material Design using MUI.</p>
<p>• @mui/styled-engine: ^5.14.15 - Styled engine for MUI components.</p>
<p>• @react-oauth/google: ^0.11.1 - Library for implementing Google OAuth in React.</p>
<p>• @reduxjs/toolkit: ^1.9.7 - Toolkit for efficient Redux development.</p>
<p>• antd: ^5.9.4 - Ant Design, a design system with React UI library.</p>
<p>• axios: ^1.5.1 - Promise-based HTTP client for browser and Node.js.</p>
<p>• classnames: ^2.3.2 - Utility for conditionally joining classNames together.</p>
<p>• eslint-config-react-app: ^7.0.1 - ESLint configuration used by Create React App.</p>
<p>• node-sass: ^9.0.0 - Library providing Node.js bindings to LibSass.</p>
<p>• react: ^18.2.0 - Library for building user interfaces.</p>
<p>• react-dom: ^18.2.0 - DOM-specific methods for React.</p>
<p>• react-redux: ^8.1.3 - Official React bindings for Redux.</p>
<p>• react-router-dom: ^6.16.0 - DOM bindings for React Router.</p>
<p>• redux: ^4.2.1 - Predictable state container for JavaScript apps.</p>
<p>• redux-devtools-extension: ^2.13.9 - DevTools extension for Redux.</p>
<p>• redux-thunk: ^2.4.2 - Middleware for Redux.</p>
<p>• vite: ^4.4.11 - Frontend build tool.</p>
<p>• vite-plugin-eslint: ^1.8.1 - Vite plugin to integrate ESLint.</p>

### Backend Dependencies
<p>• spring-boot-starter-data-jpa: - Integration of Spring Data JPA with Spring Boot.</p>
<p>• spring-boot-starter-security: - Integration of Spring Security with Spring Boot.</p>
<p>• jjwt-api: - APIs for creating, parsing, and verifying JWTs.</p>
<p>• jjwt-impl: - Implementation of JWT APIs.</p>
<p>• jjwt-jackson: - Jackson-based JSON support for JWTs.</p>
<p>• spring-boot-starter-web: - Support for building web applications including RESTful applications using Spring MVC.</p>
<p>• spring-boot-devtools: - Tools for development in Spring Boot.</p>
<p>• mysql-connector-j: - JDBC driver for MySQL.</p>
<p>• lombok: 1.18.30 - Java library for reducing boilerplate code.</p>
<p>• spring-boot-starter-tomcat: - Integration of Tomcat with Spring Boot.</p>
<p>• spring-boot-starter-test: - Tools for testing Spring Boot applications.</p>
<p>• spring-security-test: - Support for testing Spring Security.</p>
<p>• log4j-api: - Logging API for Java.</p>
<p>• spring-ldap-core: - Core LDAP features in Spring.</p>
<p>• spring-security-ldap: - LDAP features in Spring Security.</p>
<p>• spring-security-oauth2-resource-server: 6.1.5 - Support for OAuth 2.0 resource server.</p>
<p>• lombok-maven-plugin: 1.18.20.0 - Maven plugin for Lombok.</p>
<p>• mapstruct: 1.5.5.Final - Code generator for converting between Java bean types.</p>
<p>• mapstruct-processor: 1.5.5.Final - Processor for MapStruct.</p>
<p>• spring-boot-starter-mail: 3.1.4 - Integration of mail sending functionality with Spring Boot.</p>
<p>• velocity: 1.7 - Template engine.</p>
<p>• jakarta.validation-api: 3.0.2 - Jakarta Bean Validation API.</p>
<p>• spring-context-support: 6.0.12 - Support classes for integrating common third.</p>

# ☑️ Demo Video (Features) <a name = "demo"></a>

![Demo Video](./maintainance_&_report/DemoVideo/DemoVideo.mov)


# Features

## Developers

- Nizamul Kazi (B00961418) - nz707888@dal.ca
- Rachit Khanna (B00948604) - rc257785@dal.ca
- Nisarg Chudasama (B00971370) - ns458128@dal.ca
- Freya Jayant Vora (B00961402) - freya.vora@dal.ca
- Mohd Faizan (B00966320) - mh299149@dal.ca


