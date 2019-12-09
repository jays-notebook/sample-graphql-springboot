# GraphQL with SpringBoot

## GraphQL
### Introduction to GraphQL(from official site : [link](https://graphql.org/))
> GraphQL is a query language for your API, and a server-side runtime for executing queries by using a type system you define for your data. GraphQL isn't tied to any specific database or storage engine and is instead backed by your existing code and data.

### GraphQL 개념잡기(from kakao Tech : [link](https://tech.kakao.com/2019/08/01/graphql-basic/))
> Graph QL(이하 gql)은 Structed Query Language(이하 sql)와 마찬가지로 쿼리 언어입니다. (중략) gql은 웹 클라이언트가 데이터를 서버로 부터 효율적으로 가져오는 것이 목적입니다

> REST API는 URL, METHOD등을 조합하기 때문에 다양한 Endpoint가 존재 합니다. 반면, gql은 단 하나의 Endpoint가 존재 합니다. 또한, gql API에서는 불러오는 데이터의 종류를 쿼리 조합을 통해서 결정 합니다.  

![REST vs GraphQL](https://miro.medium.com/max/800/1*qpyJSVVPkd5c6ItMmivnYg.png)*An artists’ interpretation of fetching resources with multiple REST roundtrips vs. one GraphQL request([source](https://blog.apollographql.com/graphql-vs-rest-5d425123e34b))*]

## About this Project
It's a GraphQL API application with Spring boot. 

### Requirements
1. JDK 11
2. Gradle 5.2.1
3. Spring Boot 2.1.9.RELEASE
4. GraphQL Java 13
5. GraphQL SpringBoot-webmvc 1.0
6. Junit 5

### How to run on local environment
- `./gradlew bootrun` on your cli
- or Run Main Class `YourBookMarkApiApplication.class`
- or Run excutable jar file after build `java -jar graphql-springboot-1.0-SNAPSHOT.jar`

### How to build as a excutable jar
- `./gradlew bootjar` on your cli

### Use Postman 
- import script `graphql-sample.postman_collection.json` in your Postman app.
