# Introduction
Web Application developed by Mithun for CGI code assessment

# Front End
The front end with minimal design is developed for the recipe functionality.

### Tech Stack Used
1.	Angular 10.x
### Running the app
* Move to the frontend/cgi-assessment-ui folder and execute the below command
* **npm run local**
* The app will start in 4200 port by default
* Hit localhost:4200/ to access the application
* Sample screenshots are attached in the resources/sampleResponses/fronEnd folder

# Back End
### Tech Stack Used
1.	Java 17
2.	Spring Boot 3
3.	Spring 6
4.  Maven

### Spring Profiles Used
* secure,performanceLogger
* performanceLogger - profile that will enable the performance logs

### Building the app
* Use **mvn clean install** command from terminal

### Starting the app
* Use the below command to start the app from terminal : 
* mvn spring-boot:run -Dspring-boot.run.profiles=secure,performanceLogger

### Endpoints
* 1.GetAllRecipes - Reads the input recipe file and returns all the recipes
* 2.GetRecipesByIngredient - Accepts a list of ingredients. Returns the recipes that contain the ingredient
* 3.AnalyseLogs - Accepts log level as input. Returns the log statements matching the input log level 

### More info about the app
* Performance Loggers have been used to identity the API performance
* Spring AOP has been used to implement performance logging.
* The average response times are show below
* Endpoint Name        | Avg Resp Time(in ms)
  ---------------------| -------------
  Recipe               | 10
  LogAnalyser          | 20
* Handled the exceptions globally using controller advice
* Added SecurityFilter that handles the security. For now , it will just print a log statement to show that it is invoked.
* Logging has been configured in logback.xml to have custom information(Based on requirement)
* Used lombok to avoid the boilerplate getters and setters code.
* **Attached the postman collection in the path backend/resources/postmanCollections along with the environment file to test the endpoints**
* Sample responses for success and failure scenarios have been attached in **backend/resources/sampleresponses** folder


  


