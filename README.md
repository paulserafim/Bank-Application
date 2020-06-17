# Bank-Application
This project is a simple single page Bank Application which enable users to view their balance, transfer money between each other, and follow their transactions. The application records the users' location to be able to detect potential transaction frauds (e.g. a user transfers money from another place a short time after another transaction took place in a different location).

The application is tested with Cucumber scenarious. To run the application, start the server (as an usual Spring Boot application) and run the following Cucumber features (in the following order):

1. PostClient.feature
2. GetClient.feature
3. Login.feature

These features will generate the following test usernames and password:
1. Account Number: 123456
   Passowrd: 1234
   
2. Account Number: 123457
   Password: 1235
   
The application front-end is in the Bank-Application-front-end repository.
