# Spring Boot Application

[![Build Status](https://travis-ci.org/your-username/your-repository.svg?branch=main)](https://travis-ci.org/your-username/your-repository)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## Overview

Spring boot 3 + Angular 16 application for Xtramile coding test

## Features

- List of Patients
- Add/Edit/Remove patient data
- Search patients by id or name

## Prerequisites

- Java 17 or later
- Maven 3.x
- Node ^16.14.0 || ^18.10.0
- Your preferred IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Project Structures
- The java spring boot project code reside on the src/main/java and resources in src/main/resources
- The angular 16 project code for front end reside on the src/main/frontend
- The angular projects build location will be on src/main/resources/static so it will act as static resources for spring boot apps

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/cfathoni/xtramile-java-test.git

2. **Build Project**
   * To build angular project you can go to src/main/frontend/xtramile-frontend then run
       ```bash
       npm install
       ng build --configuration=production
   * To build spring boot project from the root directory of this project you can run
       ```bash
       mvn clean package
3. **Run the build jar**
   * To run the produced application, go to target directory then you can run this command
      ```bash
       java -jar xtramile-java-test-0.0.1-SNAPSHOT.jar
   
