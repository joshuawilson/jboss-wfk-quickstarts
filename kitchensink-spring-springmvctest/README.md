kitchensink-spring-springmvctest: Kitchensink SrpingMVCTest Example using Spring 3.2
====================================================================================
Author: Marius Bogoevici, Tejas Mehta, Joshua Wilson
Level: Intermediate
Technologies: JSP, JPA, JSON, Spring, JUnit
Summary: An example that incorporates multiple technologies
Target Product: WFK
Product Versions: EAP 6.1, EAP 6.2, WFK 2.4
Source: <https://github.com/jboss-developer/jboss-wfk-quickstarts/>

What is it?
-----------


This is your project! It is a sample, deployable Maven 3 project to help you get your foot in the door developing with 
Java EE 6 and Spring on Red Hat JBoss Enterprise Application Platform 6.1 or later.

This project is setup to allow you to create a compliant Java EE 6 application using JSP, JPA 2.0 and Spring 3.2. It 
includes a persistence unit and some sample persistence and transaction code to introduce you to database access in enterprise Java:

* This module adds `MemberMockMVCTest.java` to showcase a use case of `MockMVC` and `RestTemplate` to test the mvc aspect of the application.

* By using `@WebAppConfiguration` and `@ContextConfiguration` we tell Spring the configuration files we would like the tests to use.

* In `jboss-as-spring-mvc-context.xml` `<context:component-scan base-package="org.jboss.as.quickstarts.kitchensink.spring.basic.controller"/>` and `<mvc:annotation-driven/>` are used to register both the non-rest and rest controllers.

* The controllers map the respective urls to methods using `@RequestMapping(url)`.

* To return JSON, the rest controller uses `@ResponseBody`.

* The datasource and entitymanager are retrieved via JNDI.

System Requirements
-------------------

The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform (EAP) 6.1 or 
later with the Red Hat JBoss Web Framework Kit (WFK) 2.4.

To run the quickstart with the provided build script, you need the following:

1. Java 1.6, to run JBoss and Maven. You can choose from the following:
    * OpenJDK
    * Oracle Java SE
    * Oracle JRockit

2. Maven 3.0.0 or later, to build and deploy the examples
    * If you have not yet installed Maven, see the [Maven Getting Started Guide](http://maven.apache.org/guides/getting-started/index.html) for details.
    * If you have installed Maven, you can check the version by typing the following in a command line:

            mvn --version 

3. The JBoss EAP distribution ZIP.
    * For information on how to install and run JBoss, refer to the product documentation.


Run the Quickstart
-------------------

* [Configure Maven](https://github.com/jboss-developer/jboss-wfk-quickstarts#configure-maven)
* [Start the JBoss server](#start-the-jboss-server)
* [Build and deploy the quickstarts](#build-and-deploy-the-quickstart)


### Start the JBoss Server

To start JBoss EAP:

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the JBoss server:

            For Linux:   JBOSS_HOME/bin/standalone.sh
            For Windows: JBOSS_HOME\bin\standalone.bat

### Build and Deploy

#### Build the Quickstart Archive

In some cases, you may want to build the application to test for compile errors or view the contents of the archive. 

1. Open a command line and navigate to the root directory of the quickstart.
2. Use this command if you only want to build the archive, but not deploy it:

            mvn clean package

#### Build and Deploy the Quickstart Archive

1. Make sure you [start the JBoss server](#start-the-jboss-server) for the deploy to work. 
2. Open a command line and navigate to the root directory of the quickstart.
3. Use this command to build and deploy the archive:

            mvn clean package jboss-as:deploy

4. This will deploy `target/jboss-kitchensink-spring-springmvctest.war` to the running instance of the server.

#### Undeploy an Archive

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn jboss-as:undeploy


Access the application
----------------------

The application will be running at the following URL: <http://localhost:8080/jboss-kitchensink-spring-springmvctest/>.


Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------

You can also start the server and deploy the quickstart from Eclipse using JBoss tools. For more information, see 
[Use JBoss Developer Studio or Eclipse to Run the Quickstart](../README.md#use-jboss-developer-studio-or-eclipse-to-run-the-quickstarts)


Debug the Application
---------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following 
commands to pull them into your local repository. The IDE should then detect them.

        mvn dependency:sources
        mvn dependency:resolve -Dclassifier=javadoc
