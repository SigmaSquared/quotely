# QUOTELY

### Features
* Request Quotes from Forismatic
* Supported languages are Russian and English (English is by default if nothing is passed.)

TODO:
* Test cases...
* I think the CommandLineStarted could have been refactored to keep user specific control away from everything else.

Heartache: 
* Struggled with CommandLineRunner - Spring Boot wanted to load it for test.
* Couldn't get RestTemplate to download from maven.
* Introducing a Scanner

Notes:
Admittedly I haven't done TDD in 3 years and it showed...I could not wrap my head around how to test user input in the context of the CommandLineStarter.
Typically I have a repo and a service and I would give the repo dummy data, inject it into my service and run tests.
With not very much time left...running code wins - I had to make a choice between completion and test cases. 
Hopefully I made the right decision...

### Total Time: 2hr 10min
