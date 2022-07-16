[![Codacy Badge](https://app.codacy.com/project/badge/Grade/d859b5dbf86349b48c4f7eabea1acfc0)](https://www.codacy.com/gh/igar15/skillaggregator/dashboard)
[![Build Status](https://app.travis-ci.com/igar15/skillaggregator.svg?branch=master)](https://travis-ci.com/github/igar15/skillaggregator)

Skill Aggregator project (New)
=================================

Java Enterprise project to view the key skills required for the specified profession.  

### Technology stack used: 
* Maven
* Spring Boot 2
* Spring Web
* Spring Data JPA (Hibernate)
* Thymeleaf
* JUnit 5
* Bootstrap 4

### Project key logic:
* System main purpose: show the key skills required for the specified profession.
* User enters the name of the profession he is interested in, and the city where to search.
* Optionally, user can select the sample size for analysis. The sample size significantly affects the waiting time for the result.
* After processing the data, the program provides the user with a list of key skills for the specified profession, as well as the percentage of vacancies that require their knowledge.
* The program compiles a list of key skills based on vacancy data from the <a href="https://api.hh.ru/">Head hunters Russia API</a> portal.
