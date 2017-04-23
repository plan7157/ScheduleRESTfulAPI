# Read Me #

# Project Detail #
## Technologies And Tool ##
 - Java 1.8
 - IntelliJ
 - Spring Boot
 - Hibernate
 - flyway
 - lombok
 - Mysql
 
# Windows #
## Environment Setup ##
#### Set Up Development Environment ####
1.	Download the JDK from
	    http://www.oracle.com/technetwork/java/javase/downloads/index.html
2.	The recommended version for Fast Easy is Java SE 8u101 or 8u 102
3.	Please accept the EULA and select the correct version for your CPU architecture
4.	After downloading, double click on the executable file 
5.	Accept the default recommended settings and proceed with installation
6.	Note down the location of where your JDK is installed as we need to use it for other software installation

7.	Setup Java JDK 

    a. Type advanced system settings in the search box (beside the Windows start button), clicks View advanced system settings.

    b.	Select Advance tab, clicks Environment Variables

    c.	In System variables, add a new JAVA_HOME variable and point it to the JDK installed folder.

				Variable name: JAVA_HOME
				Variable value: C:\Program Files\Java\jdk\1.8.0_101 {Directory of java} 

    d.	In System variables, find PATH, clicks edit and append this %JAVA_HOME%\bin to the end.

				Variable name: PATH
				Variable value: {Existing PATH};%JAVA_HOME%\bin

8.	Download and Install Apache Maven

	a. Type advanced system settings in the search box (beside the Windows start button), clicks View advanced system settings.

	b.	Select Advance tab, clicks Environment Variables

	c.	In System variables, find PATH, clicks edit and append this Maven directory path to the end.

				Variable name: PATH
				Variable value: {Existing PATH};C:\Program Files\apache-maven-3.3.9-bin\apache-maven-3.3.9\bin

9.	Download and Install Apache Tomcat
		a.	Type advanced system settings in the search box (beside the Windows start button), clicks View advanced system settings.
		b.	Select Advance tab, clicks Environment Variables
		c.	In User variables, add a new user CATALINA_HOME

				Variable name: CATALINA_HOME
				Variable value: C:\Program Files\apache-tomcat-8.5.5-windows-x64\apache-tomcat-8.5.5\bin

### Set Up IDE ###

#### IntelliJ ####


***Download and install IntelliJ Community Edition from IntelliJ website*** 

		https://www.jetbrains.com/idea/?fromMenu

### IntelliJ Lombok Plugin ###
1. Click File on menu bar
2. Click Settings...
3. Click Plugins
4. Click Browse repositories...
5. Search lombok
6. install plugin

### Database Server ####

#### Windows ####

Following the instruction here: 
```
		https://corlewsolutions.com/articles/article-21-how-to-install-mysql-server-5-6-on-windows-7-development-machine
```
### Setting Up User Account ###

#### Windows ####
* Open
```
  C:\Program Files\MySQL\MySQL Server 5.5\my.ini
```
* Add the following lines to the top of the file
```
  [mysqld]
  skip-grant-tables
```
* Connect to MySQL Server
```
 mysql -u root
```
* Inside mysql prompt Flush Privileges
```
 FLUSH PRIVILEGES;
```
* then create a new root user or any other accounts using the command
```
 ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass';
```

### Database Client ###

Download and follow instruction for each platform for this page: 
```
    https://www.mysql.com/products/workbench/
```
### Connecting MySQL Server ###  
1. Open MySQL Workbench 
2. Connect to MySQL Server using following parameters:      
		Host name: localhost
     		Port: 3306
     		User name: root
     		Local Instance of database server should not require any password unless one is set
3. Create a new scheme called schedule

### Lombok ###

Download and install Lombok and follow the instruction on the project’s web site: 
```
				https://projectlombok.org/download.html
```

### Starting Deployment ###
 1. Pull Project from Git:
 ```
 				https://github.com/plan7157/ScheduleRESTfulAPI
 ```
 2. Import the project into your IDE workspace
 3. Update Maven dependencies
 4. Clean and Install with Maven:
 ```
                mvn clean install
 ```
 5. Try to run your project
## Postman ##
### Install Postman ###
Download and install Postman and follow the instruction on the web site: 
 ```
    https://www.getpostman.com/
  ```
### Import Postman Collection ###
1.Get Postman Collection in this project,the collection name called:
 ```
    ScheduleRESTful.postman_collection.json
  ```
  
2.Open Postman

3.Click Import

4.Drop the collection on it.

 ## FAQ ##
 ### Flyway Error ###
 1. Drop all table in schedule or run this command:
  ```
 mvn flyway:clean -Dflyway.url=jdbc:mysql://localhost:3306/schedule -Dflyway.user=root -Dflyway.password=
 ```
 2. Try to run project again.
 
