# Reward Points Program Challenge with Spring Boot

### Tech Stack
Technologies used for current Task

* [Java 1.8]
* [Spring Boot, Spring Boot Web,embedded Tomcat Server]
* [Maven]
* [Swagger, Log 4j]

#Data set prepared and set in dataSet.json in the resources folder, for the past three months.

### Rest Apis Info
The following things illustrate Requests and Response:
# A get api which takes in customer id and month
getRewardsEarnedPerMonthByCustomer/{customerId}/{month}
* [Request] : customerId=>42341, month=>8
* [Response] : Reward points earned : 150

# A get api which takes in customer id 
getRewardsEarnedThroughoutByCustomer/{customerId}
* [Request] : customerId=>42341
* [Response] : Reward points earned: 450

