# Rabobank Assignment - Customer Statement Processor

Rabobank assignment to show coding and software engineering skills.

## About the Project Architecture

This assignment mimics a typical integration job, where the composition of low complexity operations, in a kind of pipeline, tries to reduce overall complexity and interdependency.

Due to the simple requirements, I tried to illustrate the kind of architecture solutions where multiple systems can be easily integrated. The datatype transformations and data flow didn't require lengthy implementations and future expansions added with ease.

Apache Camel is a very mature product, offering a software implementation of Integration Patterns, as defined [here](https://www.enterpriseintegrationpatterns.com/). Another excellent source of reference is the book Enterprise Integration Patterns, from Gregor Hohpe and Bobby Woolf (ISBN: 978-0321200686).

This architectural choice isn't an obligation at all but aims to exercise some more advanced techniques for systems integration.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run this software, you need to have the following components properly installed:

* [Open JDK 8](http://openjdk.java.net/install/) or similar
* [Apache Maven 3.5.2](https://maven.apache.org/download.cgi) or superior

### Installing & Running

Download a local copy of the project using the following command:

```
git clone https://github.com/fabianopinto/rabobank-assignment.git 
```

To build this software and store it in your local Maven repository use the following command:

```
mvn clean install -U 
```

To run this software in DEVELOPMENT mode, use the following command:

```
mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests=true
```

To run this software in PRODUCTION mode, use the following command:

```
mvn spring-boot:run -DskipTests=true
```

To configure the local folders for inbox, outbox and rejected files, edit the `src/main/resources/application.properties` file.

## Running the tests

To run unit tests use the following command:

```
mvn test -Dspring.profiles.active=dev
```

## Application details

This application pools the "inbox" folder for any files to process, then try to process CSV or XML files. Any invalid extension file or if an error occurs during the file parsing, the original file moved to the "rejected" folder.

When the parsing and processing of each file run without any problem, stores the resulting report in the "outbox" folder. This report list any incoherence with the inputted data, as duplicated transaction references or wrong end balance. Each entry includes the index (0 based) of original file data, the respective transaction reference and a simple description of the problem.

The default configuration pools the `data/inbox` folder, the results stored in the `data/outbox` folder, and the rejected files moved to `data/outbox/rejected` folder. Also, the `data/inbox/samples` folder holds some sample data as included in the original assignment specification.

Additionally the following end-points are available for code instrumentation and application management:

URL | Description
--- | ---
`<host>/actuator` | Code instrumentation end-points
`<host>/actuator/camelroutecontroller` | Apache Camel routes controllers
`<host>/actuator/camelroutes` | Apache Camel routes details
`<host>/actuator/camelroutes/{id}/{action}` | Apache Camel route actions
`<host>/actuator/camelroutes/{id}` | Apache Camel route details
`<host>/actuator/auditevents` | Event audit
`<host>/actuator/beans` | Dumps registered beans
`<host>/actuator/health` | System health
`<host>/actuator/conditions` | System conditions details
`<host>/actuator/configprops` | System configuration details
`<host>/actuator/env` | Dumps full system environment
`<host>/actuator/env/<name>` | Dumps system environment property
`<host>/actuator/info` | Lists system-specific information
`<host>/actuator/loggers` | Lists available loggers
`<host>/actuator/loggers/<name>` | Logger details by name
`<host>/actuator/heapdump` | Download heap memory
`<host>/actuator/threaddump` | Dumps threads details
`<host>/actuator/metrics` | Dumps system metrics
`<host>/actuator/metrics/<name>` | Dumps system metrics by name
`<host>/actuator/scheduledtasks` | Lists system scheduled tasks
`<host>/actuator/httptrace` | Traces HTTP requests 
`<host>/actuator/mappings` | List end-point mappings details

For security reasons, in the production environment only the end points `health` and `info` are made available.

## Author

**Fabiano da Silveira Pinto** - [fabianopinto@gmail.com](fabianopinto@gmail.com)

## Acknowledgments

I would like to thank Rabobank for this opportunity.