# Bill Application
## About The Project

This billing application manage the creation and processing of bills for customers. 
Each bill consists of a list of products that are associated with a specific customer.
The bill may also include various discounts that are applied based on certain criteria:
* For every $100 on the bill, there would be a $5 discount.
* Moreover, if the customer is classified as normal, employee, or affiliate, 
they will get a different discount that is applied only on products of type groceries.

## Build with
*  [MySQL 8.0](https://dev.mysql.com/downloads/mysql/8.0.html)
*  [Maven](https://maven.apache.org/)
*  [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

## Getting Started

### Installation
* Maven clean and install
```sh
mvn clean install
```

### Run Application 
Using intellij IDE  
* File > Open > Select project > Load as maven

OR
* File > Open > Select project > pom.xml file > Reload project

## License
* [LICENSE.MIT](https://github.com/Khawlamq/BillApplication/blob/main/LICENSE)