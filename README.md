# E-Shop  

A simple e-commerce application built with Spring Boot for managing products.  

## Reflection 1  

While reviewing my code, I noticed some areas that needed improvement. First, my code lacked comments, which made it harder to understand and maintain. I have now added concise and relevant comments to key parts of the controller, repository, and service to improve readability.  

Second, I identified a potential issue in the `findById` method—if the product was not found, it could return `null`, leading to unexpected errors. I fixed this by ensuring it throws a `NoSuchElementException` with a clear message. Additionally, in the `update` method, I handled the case where `findById` fails to avoid modifying a `null` product.  

Another important issue was with the product quantity field. Previously, users could enter any value, leading to errors. I improved this by enforcing numeric input using `type="number"`, adding a minimum value constraint, and displaying an error message when validation fails.  


## Reflection 2  

#### Writing Unit Tests  
After writing unit tests for the eShop project, I feel more confident in the stability and correctness of the application. Unit tests help ensure that individual components work as expected without being everytime in the best scenario for it to work, catching potential bugs early. Writing these tests also encouraged me to think more critically about edge cases and how different parts of the system interact.  

#### Number of Unit Tests per Class  
The number of unit tests in a class depends on the complexity of the functionality being tested. Ideally, each method should have at least one test, covering both positive and negative scenarios. For example, in the `ProductTest.java` file, we should verify not only the expected outputs but also potential errors, such as handling null values or wrong/not expected inputs.  

#### Ensuring Sufficient Test Coverage  
Thanks to Test Coverage, we can check that our tests reach every possible scenarios of our code. However, achieving 100% code coverage does not guarantee the absence of bugs It does not confirm that the logic is correct in all scenarios. Therefore, in addition to unit tests, functional and integration tests are also necessary. Moreover, Test Coverage is only one metric and a lot of other metrics can be useful.

#### Code Cleanliness in Functional Tests  
If we create a new test suite similar to `CreateProductFunctionalTest.java` for verifying the number of items in the product list, we might introduce code duplication. Duplicating setup procedures and instance variables across multiple test classes can reduce code quality by making the tests harder to maintain.  

**Potential Clean Code Issues:**  
- **Code Duplication** – Repeating the same setup code in multiple test classes.  
- **Lack of Reusability** – Each test class initializes WebDriver separately instead of using a shared utility.  
- **Hardcoded Values** – Using static values instead of parameterized tests.  

**Possible Improvement:**  
- Extract common setUp() function code into a "base test class" that other test classes can extend.  
- Implement a utility class for common WebDriver actions, such as navigating to pages or interacting with elements.
- Improve test tracking by creating a TEST_COVERAGE.md file that lists tested features, uncovered parts, and test scenarios (both positive and negative). Adding diagrams can visually represent test coverage that are more easily understandable than
  hundreds of code lines.
