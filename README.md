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
  * To run end to end test, execute the following command:  
  `$ mvn clean compile compiler:testCompile failsafe:integration-test`  
  
### Using the REST API  
The REST APIs can be tested using popular REST client like Postman. Another way to test the REST APIs is to use *curl* command. Here are some sample requests and responses.  

  * Sending money between accounts
    * HTTP POST URI: `/api/v1/transactions/transfer` 
    * Request body:  
     ```
     {
         "fromAccNumber":"121-123-323",
         "toAccNumber":"121-123-876",
         "amount":1000
     }
     ```    
     * Response body: 
     ```
     {
         "toAccId":2,
         "id":6,
         "transactionDate":"2019-06-08T20:46:35.331+0000",
         "fromAccId":1,
         "amount":1000,
         "type":3
     }
     ```  
     * HTTP Statuses:
       * 200: Transfer successful
       * 404: Account not found
       * 406: Insufficient balance
       
  * Account statement with account balance and list of transactions  
    * HTTP GET URI: `/api/v1/statements/{accountNumber}` 
    * Response body:  
     ```
     {
         "balance":28000,
         "accountId":1,
         "transactionDtoList":[
            {
               "type":3,
               "amount":500,
               "id":3,
               "toAccId":2,
               "fromAccId":1,
               "transactionDate":"2019-06-08T18:05:23.000+0000"
            },
            {
               "type":3,
               "amount":1000,
               "id":4,
               "fromAccId":1,
               "toAccId":2,
               "transactionDate":"2019-06-08T18:10:53.000+0000"
            },
            {
               "type":3,
               "id":5,
               "amount":500,
               "toAccId":1,
               "fromAccId":2,
               "transactionDate":"2019-06-08T18:11:16.000+0000"
            },
            {
               "fromAccId":1,
               "toAccId":2,
               "transactionDate":"2019-06-08T20:46:35.000+0000",
               "id":6,
               "type":3,
               "amount":1000
            }
         ],
         "accountNumber":"121-123-323"
     }
     ```  
     * HTTP Statuses:
       * 200: Statement and transactions found
       * 404: Account not found     
