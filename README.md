# IT Services Portal

The IT Services Portal is a web application for managing and reporting IT services-related issues and incidents. It was created as part of a course assignment at the University of Newcastle, with the goal of improving the efficiency of the IT staff by providing a knowledge-base for users to browse before submitting an issue. The application was created using Spring Framework and Java, and follows the MVC (Model-View-Controller) architecture.

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

To setup a database connection, you will need to set the driver name, database URL, and the username and password for connecting the database in [`com.spacejaam.itservicesportal.config.DatasourceConfig`](./src/main/java/com/spacejaam/itservicesportal/config/DatasourceConfig.java). The application does not provide the ability for users to create accounts, and so, accounts must be manually added to the database. The following table provides example account data.

| Username                           | Password              | Role       |
|------------------------------------|-----------------------|------------|
| `nzinga_houghus5j@wit.vs`          | `UkyDJvlLRqcLL1FLdV7` | `IT Staff` |
| `aamil_delcastillorp@volumes.zn`   | `d3Eu5SE2DIZ89`       | `User`     |
| `binyomin_ruckmanh1tj@richards.ug` | `EosoGD9MoHtRSuRoNHb` | `User`     |
| `marena_davidsontmsu@company.mpd`  | `3mCE7UjWt9F`         | `IT Staff` |

To run the application locally using Apache Tomcat (see [Tomcat Setup](https://tomcat.apache.org/tomcat-8.5-doc/setup.html)), follow these steps:

1. Move the project file into the `<tomcat-installation-directory>/webapps` directory
2. Run `<tomcat-installation-directory>/bin/startup.bat`

## Technologies Used

* [Spring Framework](https://spring.io/projects/spring-framework)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [Spring Data](https://spring.io/projects/spring-data)
* [Apache Tomcat](https://tomcat.apache.org/) (for hosting the application)
* [Azure SQL Server](https://azure.microsoft.com/en-au/services/sql-database/campaign/) / [Microsoft SQL Server](https://www.microsoft.com/en-au/sql-server/sql-server-2019) (for hosting the database on the cloud or locally, respectively)
* [Spring Session](https://spring.io/projects/spring-session)
* [Jakarta Server Pages](https://projects.eclipse.org/projects/ee4j.jsp) (JSPs)
* [npm](https://www.npmjs.com/) (with the [TypeScript package](https://www.npmjs.com/package/typescript) for compiling TypeScript to JavaScript)

## Credits

Alex Budden ([Buddostars](https://github.com/Buddostars))

Aiden Brown ([Aiden-Brown](https://github.com/Aiden-Brown))

## License

Licensed under the MIT License. See [LICENSE](./LICENSE) for more information.