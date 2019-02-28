## Clean Architecture Example

### Pre-requisite

Java 11

```
> java -version
openjdk version "11" 2018-09-25
OpenJDK Runtime Environment 18.9 (build 11+28)
OpenJDK 64-Bit Server VM 18.9 (build 11+28, mixed mode)
```

### Compile

`./mvn clean install`

### Run Simple Manual example

`/application/manual-app/src/main/java/com.infosupport.iska/Main -> Main` 


### Run Spring example

`/application/spring-app/src/main/java/com.infosupport.iska.spring/Application -> Main` 

### Run Vertx example

`/application/vertx-app/src/main/java/com.infosupport.iska.vertx/RestVertxApplication -> Main` 

### Use the webbapps

#### Create Chicken
```
POST: http://localhost:8080/chickens
Body:
{
  "name": "Klara Kip",
  "age": 1
}
```

#### Get all chickens
```
GET: http://localhost:8080/chickens
```

#### Get chicken by RegistrationNumber
```
GET: http://localhost:8080/chickens/{RegistrationNumber} (You can find this number via the getAllChickens request)
```

#### Lay eggs
A random number of eggs will be created for the chicken
```
POST: http://localhost:8080/chickens/{RegistrationNumber}/eggs
Body:
{}
```

Note: The JavaEE application does not work yet. A working example will be added in the near future. 
