# RABO BANK TRANSACTION REPORTS

* RaboTransactionReports is a microservice that exposes rest endpoints to generate a transaction report based file input provided.
* The transaction report generated has the list of valid and Invalid Transactions.
* Transaction are valid only if :
	 The transaction reference number is unique and 
	 endBalance = startBalance + Mutation.		
* The API accepts XML/CSV file as an input.

## Tech Stack 

* Java Version 1.8
* Spring 5.x
* SpringBoot 2.x
* Maven 3.x
* Apache Commons 1.5
* Junit 5
* Tomcat 8(In Built)

## IDE Used

* Spring Tool Suite

## How to Build

* Build Tool Used : maven
* Build Command   : **clean install** (from STS), **mvn clean install** (from cmd Prompt)


## How to Launch 
* Right click the project->Run/Debug as SpringBootApp
* Check logs/console to confirm the app has started

## How to Test

* Postman tool can be used for testing this API
* URL to hit: http://localhost:8080/raboBank/transaction/report

<ul>
	Steps :
	<li>Create a post request with the following details: </li>
	<ul>
	 	<li> 
	 		In body section, select "form-data" radio button and Pass the following details 
	 		<ul>
				<li>key   : file</li>
		    	<li>value : file to be uploaded</li>
			</ul>
		</li>
	</ul>
</ul>

## Logs:

* Logback.xml has been configured and the log files will be generated in your localMachine.
* Look for the application.properties file to know the log files path.

## JUnit: 

* Test cases have been completed with 100% code coverage
* ECL Emma plugin can be used for STS

## Author :
* **Mithun Ram Kumar** 
[LinkedIn](https://www.linkedin.com/in/mithun-ram-kumar-489110142/)
[Github](https://github.com/MithunRamKumar07/)
