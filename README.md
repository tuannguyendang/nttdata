# *** Twitter Program ***

# The Problem

__Description__

It’s 2002 all over again and our favorite DI (Dependency Injection) Framework have not been invented yet. Your task, should you choose to accept is to design & implement a dependency injection framework like spring.

What is expected of you is to come up with a first deliverable after about 2-4 hours of work. You are free to do any necessary research on the internet as with real task. 

We do not expect you to complete all the features but at the bare minimum, have a basic dependency injection capability, and should be able to support spring bean scopes singleton and prototype.

Write the code with the intention of announcing your pet project on twitter, Hackernews or reddit to encourage contributors.

---
---

# Solution

### Project Structure
Project _components_:
- `TwitterService` handle on post, get feed, follow and unfollow.
- `UserService` handle on get and add user.
- Enum `PETTYPE` store type of pets.
- `model` package store the model objects.
- `utils` all util methods using in program.
- `Constants` class store all constants using in program.

Program using 1 patterns:
1. Singleton Pattern: `TwitterService` and `UserService` we need single instance of both services in application. 
---
###	Tests

Following the structure of test class in program:
- `com.dangtuan.twitter.TwitterServiceTest` Test cases for TwitterService.

Naming Convention test cases:
-  `test***With***` Test method name with condition.
- `@Test(expected = ***)` Define the exception we are waiting to be thrown.

### Logging
`Logger` logging with java.util.logging

###	Build and Run program
JDK version: 1.8

Using gradle we can package our program:
```
cd /{yourpath}/nttdata
gradle clean build
```

Jar package in path `/nttdata/build/libs`:
- `nttdata-1.0-SNAPSHOT.jar` -> application packaged as a jar file.

Run: 
```
cd /{yourpath}/nttdata/build/libs
java -jar nttdata-1.0-SNAPSHOT.jar
```