# simple-banking
This is a simple banking application. It can be used for transferring amount between accounts, viewing statement with account balance and list of transactions.

### Required environment 
  * JDK 8
  * MySQL 5.5.5 / MariaDB 10.0.34
  * Maven 3.3.3 
  
### How to Run
  * Clone the project from github repository.   
  `$ git clone git@github.com:ShahMinulAmin/simple-banking.git` 
  * There is a database dump in db-backup directory (/simple-banking/db-backup). Extract the compressed file bank-2019-06-09.sql.gz.
  * Create a database with name ‘bank’. From command line, you can use the following commands:
    * Logon to mysql server:  
    `$ mysql -u [db_user] -p[db_pass]`
    * From mysql console, create the database:   
    `create database bank character set utf8 collate utf8_general_ci;`
  * Restore the provided backup database. You can use the following command from command line:  
  `$ mysql -u [uname] -p[pass] bank < bank-2019-06-09.sql`
  * In *application.properties* file of project’s resources directory (/simple-banking/src/main/resources), enter your database user name and password.
  * From project’s root directory (/simple-banking), execute the following command to run the project:  
  `$ mvn clean spring-boot:run`
  * To run the unit tests, execute the following command:  
  `$ mvn clean test`
  * To run integration tests, execute the following command:  
  `$ mvn clean compile compiler:testCompile failsafe:integration-test`  
