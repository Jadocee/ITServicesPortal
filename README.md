# IT Services Portal

The IT Services Portal is a web application for managing and reporting IT services-related issues and incidents. It was created as part of a course assignment at the University of Newcastle, with the goal of improving the efficiency of the IT staff by providing a knowledge-base for users to browse before submitting an issue. The application was created using Spring Framework, Java, and Java Server Pages (JSPs), and follows the MVC (Model-View-Controller) architecture.

## Use Cases

The IT Services Portal has different use cases depending on whether the user is an IT Staff member or a Client. The following are the use cases for each role:

### IT Staff

* Mark comments as suggested solutions
* Add or remove issues to the knowledge base
* View all issues
* Filter issues by status
* Tag issues

### Client

* Create issue reports
* View self-reported issues
* Review suggested solutions

### Both

* Comment on issues
* View the knowledge base
* Log in and out

## Features

The homepage of the application also includes a statistics section that shows the total unsolved incidents in each category, the total resolved incidents within the last 7 days in each category, and the stress rate.

## Design

### Presentation 

![UWE Presentation Model for IT Services Portal](./images/UWE-Presentation-Diagram.jpeg)

<figure>
    <img src="./images/UWE-Presentation-Diagram.jpeg" alt="UWE Presentation Model for IT Services Portal"/>
    <figcaption>UWE Presentation Model for IT Services Portal</figcaption>
</figure>

### Content 

![UWE Content Model for IT Services Portal](./images/Content.jpg)

### Navigation 

#### IT Staff

The following UML diagram shows the different pages and features available to IT Staff members, as well as the actions they can perform on each page:

![UWE Navigation Model of IT Staff users for IT Services Portal](./images/Navigation_ITStaff.jpg)

This diagram shows that IT Staff members can view a list of all issues, filter issues by status, and tag issues. They can also mark comments as suggested solutions, and add or remove issues to the knowledge base.

#### Client

The following UML diagram shows the different pages and features available to Clients, as well as the actions they can perform on each page:

![UWE Navigation Model of navigation of a Client using IT Services Portal](./images/Navigation_Client.jpg)

This diagram shows that Clients can create issue reports and view their own self-reported issues. They can also review suggested solutions and comment on issues.

## Dependencies

To build and run the application, you will need the following dependencies:

* Apache Maven
* Java 11
* Local or cloud hosted Microsoft SQL (or T-SQL) database

## Setup

The IT Services Portal uses Spring Security to manage authentication, and data is stored on a SQL database accessed using JDBC. A SQL script for setting up the database is provided in the repository.

To run the application locally using Apache Tomcat, follow these steps:

1. Move the project file into the <tomcat-installation-directory>/webapps directory
2. Run <tomcat-installation-directory>/bin/startup.bat

## Technologies Used

* Spring Framework
* Spring MVC
* Spring Boot
* Spring Security
* Spring Data
* Apache Tomcat (for hosting the application)
* Azure SQL Server / Microsoft SQL Server (for hosting the database on the cloud or locally, respectively)
* JDBC (for establishing connection to the database)
* Spring Session
* Java Server Pages (JSPs)
* npm (with TypeScript package for compiling TypeScript to JavaScript)

## Credits

Alex Budden ([Buddostars](https://github.com/Buddostars))

Aiden Brown ([Aiden-Brown](https://github.com/Aiden-Brown))

## License

Licensed under the MIT License. See [LICENSE](./LICENSE) for more information.