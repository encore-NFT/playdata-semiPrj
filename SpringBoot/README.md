# Semi Project Web Application

**Contents**

1. 설치 및 실행 방법
2. 구성

## 설치 및 실행 방법

0. 사전 **DB 구성**이 완료되어 있어야합니다.  
1. Spring Tool Suit 4 실행
2. 파일을 불러옵니다
   * File > Import... > Gradle / Existing Gradle Project > Next > Project root directory: ~~/PlayData-SemiPrj/SpringBoot/demo > Next > Finish
3. 서버 실행
   * com.example.demo / DemoApplication.java > Run As Spring Boot App

## 구성

### Dependencies

* [Spring Initialzr](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.5.9&packaging=jar&jvmVersion=1.8&groupId=com.example&artifactId=demo&name=demo&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.demo&dependencies=devtools,lombok,web,data-jpa,mysql) 링크
* **Spring Boot DevTools** : fast app restarts, LiveReload, and configurations
* **Lombok** : Reduce boilerplate code
* **Spring Web** : Build web, RESTful, Spring MVC
* **Spring Data JPA** : Java Persistence API
* **MySQL Driver** : MySQL JDBC
* **Swagger** : Swagger

### Layer

* **Model**
  * Entity : com.example.demo.model
    * CountEntity.java
    * NewsEntity.java
  * DTO : com.example.demo.dto
    * CountDTO.java
    * NewsDTO.java
    * ResponseDTO.java
* **Persistance** : com.example.demo.persistence
  * CountRepositoy.java
  * NewsRepository.java
* **Service** : com.example.demo.service
  * WordCloudService.java
* **Controller** : com.example.demo.cotroller
  * WordCloudController.java

### Etc Package

* Application : com.example.demo
  * DemoApplication.java
* Swagger : com.example.swagger.config
  * SwaggerConfog.java



