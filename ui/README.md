# Strangely

Strangely is an innovative web platform that facilitates connections between strangers through events, promoting new friendships and social interactions. It provides an easy-to-use interface for users to engage in events and meet new people.

. [**Documentation**](#documentation)
    [1.1 Usage Scenario](#usage_scenario) 
    ## Table of Contents

- [Overview](#overview)
- [Backend Setup](#backend-setup)
    - [Libraries Used](#libraries-used)
    - [Application Properties Overview](#Security Configuration)
    - [Prerequisites](#prerequisites)
    - [Getting Started](#getting-started)
    - [References of technologies used](#References-of-technologies-used)

    [1.2 Build/Deployment/Local run instructions](#build_instructions)

    - [ Strangely - UI Setup](#strangely---ui)
    - [Prerequisites](#prerequisites-1)
    - [Getting Started](#getting-started-1)
    - [Commands](#commands)
    - [References of technologies used](#reference-of-technologies-used)
    - [Opinionated Dev Environment](#opinionated-dev-environment)
    

2. [**CI/CD**](#ci/cd)

    [2.1 Build](#build)

    [2.2 Test](#test1)
    
    [2.3 Code Quality](#code_quality)

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
    
    [5.2 Screenshots (Features)](#screenshots)

- [Features](#features)
- [Developers](#developers)
- [License](#license)

***

## Overview

Strangely is designed to create a comfortable and welcoming platform for people looking to expand their social circles and experience new events.

***

## Backend Setup

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

### Prerequisites

- Java JDK 17
- Maven: `v4.9.4`

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
- [React](https://react.dev)
- [Redux](https://redux.js.org)
- [Ant design](https://ant.design)
- [Axios](https://axios-http.com)
- [Vite](https://vitejs.dev)
- [React Router](https://reactrouter.com/en/main)
***

# Strangely - UI


## Prerequisites

- [NodeJS](https://nodejs.org/en) `v20.x`
> you can use [nvm](https://github.com/nvm-sh/nvm) to manage node versions

check if exists

```sh
node --version
vite --version
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
cd Group14/ui
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
<tr>
<td>preview build</td>
<td>

```
vite preview
```

</td>
<td>preview of generated bundle by <i>build</i> command</td>
</tr>
<tr>
<td>lint</td>
<td>

```
eslint --ext .js,.jsx .
```

</td>
<td>automatically fix most lint errors</td>
</tr>
<tr>
<td>test</td>
<td>

```
vitest
```

</td>
<td>run unit tests</td>
</tr>
<tr>
<td>watch test</td>
<td>

```
vitest watch
```

</td>
<td>run unit tests in watch mode</td>
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
- [React Calendar](https://www.npmjs.com/package/react-calendar)
- [Redux DevTools Extension](https://github.com/zalmoxisus/redux-devtools-extension)
- [Redux Thunk](https://github.com/reduxjs/redux-thunk)

## Opinionated Dev Environment

- Use `Visual Studio Code - v1.82.2`.
- Install extensions
    - [ESLint](https://marketplace.visualstudio.com/items?itemName=dbaeumer.vscode-eslint)
    - [Prettier - Code formatter](https://marketplace.visualstudio.com/items?itemName=esbenp.prettier-vscode)
- create file at `.vscode/settings.json` from root of this repository and add the below snippet

```json
{
    "eslint.format.enable": true,
    "editor.codeActionsOnSave": {
        "source.fixAll.eslint": true 
    },
    "eslint.workingDirectories": [
        {
            "directory": "./ui",
            "changeProcessCWd": true
        }
    ]
}
```

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
<p>• create-vite: ^4.4.1 - Utility for creating new Vite projects.</p>
<p>• eslint-config-react-app: ^7.0.1 - ESLint configuration used by Create React App.</p>
<p>• node-sass: ^9.0.0 - Library providing Node.js bindings to LibSass.</p>
<p>• react: ^18.2.0 - Library for building user interfaces.</p>
<p>• react-calendar: ^4.6.1 - Calendar component for React.</p>
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

# ☑️ Screenshots (Features) <a name = "screenshots"></a>

This will automatically fix lint errors and apply code format defined in eslint and prettier on save file event.

# Features

## Developers

- Nizamul Kazi (B00) - @dal.ca
- Rachit Khanna (B00) - @dal.ca
- Nisarg Chudasama (B00971370) - ns458128@dal.ca
- Freya Jayant Vora (B00) - @dal.ca
- Mohammad Faizan (B00) - @dal.ca

## License

This project is licensed under the MIT License.
