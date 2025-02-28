# E-Shop  

A simple e-commerce application built with Spring Boot for managing products.  

## Docker Image

### Docker Hub

You can find the Docker image for this application on Docker Hub:
[**aziiiiim/advshop**](https://hub.docker.com/r/aziiiiim/advshop)

### Pulling the Docker Image

To use the pre-built Docker image, you can pull it using the following command:

```bash
docker pull aziiiiim/advshop:latest
```

### Running
Once the image is pulled, you can run the container using this command:

```bash
docker run --rm -it -p 8080:8080 aziiiiim/advshop:latest
```

Then access to:  
- https://localhost:8080/product/list  
- https://localhost:8080/car/list
- https://localhost:8080/

## Reflection 1  (Module 1)

While reviewing my code, I noticed some areas that needed improvement. First, my code lacked comments, which made it harder to understand and maintain. I have now added concise and relevant comments to key parts of the controller, repository, and service to improve readability.  

Second, I identified a potential issue in the `findById` method—if the product was not found, it could return `null`, leading to unexpected errors. I fixed this by ensuring it throws a `NoSuchElementException` with a clear message. Additionally, in the `update` method, I handled the case where `findById` fails to avoid modifying a `null` product.  

Another important issue was with the product quantity field. Previously, users could enter any value, leading to errors. I improved this by enforcing numeric input using `type="number"`, adding a minimum value constraint, and displaying an error message when validation fails.  


## Reflection 2  (Module 1)

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

## Reflection (Module 2)

### 1 - List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

One of the main code quality issues I encountered was insufficient code coverage in the tests. To resolve this, I manually simulated the usage of the application, testing all accessible areas to identify any gaps in functionality. I then translated these personal simulations into functional tests, ensuring that every feature was covered by automated tests. This approach helped to ensure that the application was well tested and that no critical paths were left apart. Additionally, I paid attention to side cases and error handling during these tests to improve the robustness of the application.

### 2 - Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Looking at the current CI/CD implementation, I think that it partially meets the definition of Continuous Integration and Continuous Deployment (CI/CD). While the pipeline automatically runs tests on code commits and builds the application, Continuous Integration is effectively done through the automated running of tests, ensuring that new changes do not break the existing codebase. However, for Continuous Deployment, more steps are required, such as automatically deploying the application to production after a successful build and test cycle. This implementation currently lacks full deployment automation to production, which is crucial for complete Continuous Deployment. The next step of Deployment should be to host the application so that any updates to the project are effective instantly via the URL, making the modifications immediately available to users.

## Reflection (Module 3)

### 1 - Explain what principles you apply to your project!

I tried to apply every SOLID principles to my projet. Indeed, I firstly applied the Single Responsibility Principle (SRP) by spliting the ProductController class into two classes because in the source code provided in the module, the CarController was written in the ProductController.java file and it was an extension of the ProductController. In order to respect the principle, the CarController needs to be in another file because 1 file should contain 1 class and this class should be responsible of 1 functionnality. Also, the CarController shouldn't be an extension of a ProductController because the CarController class needs to be only responsible of being a Controller for Cars and not also for Products. 
Then, I tried to apply the second principle which is the Open/Closed Principle (OCP). I observed that several code lines or methods were almost the same between Car and Product. That is why I decided to make a generic item representing Car and Product, followed by a generic repository, a generic service and a generic controller. This allows my project to respect the OCP principle because now, if I want to add another 'item' which would follow the same logic in the application but would not have the same attributes, it can be done without modifying the existing code. The Liskov Substitution Principle (LSP) was respected by ensuring that all entities implementing Item could be used interchangeably in GenericRepository and GenericService without causing unexpected behavior. The Interface Segregation Principle (ISP) was naturally followed by not forcing unrelated methods onto classes; instead, each class has only the necessary attributes and behaviors. Lastly, the Dependency Inversion Principle (DIP) was applied by ensuring that high-level modules (such as services) do not depend directly on low-level implementations (repositories), but rather on abstractions. This was particularly important in my tests, where dependency injection ensured flexibility.

### 2 - Explain the advantages of applying SOLID principles to your project with examples

In my opinion, pplying SOLID principles brought several advantages to my project. For instance, by using a generic repository, I avoid code duplication and can easily introduce new entities like Car or Product without rewriting database interactions. Additionally, dependency inversion makes unit testing easier, as I can mock repositories instead of relying on real implementations, reducing the risk of errors like NullPointerException. Moreover, the clear separation of concerns ensures that changes in one part of the code do not break unrelated functionalities. This modularity improves code readability and maintainability, making it easier to work on the project.

### 3 - Explain the disadvantages of not applying SOLID principles to your project with examples.

On the other hand, if SOLID principles were not applied, the project would become harder to maintain and scale. Without the Open/Closed Principle, adding a new entity would require modifying existing repository and service classes, increasing the risk of introducing bugs. Without Dependency Inversion, the project would have strong couplings, making it difficult to write isolated unit tests, as seen when ProductServiceImplTest initially failed due to direct dependencies. A lack of Liskov Substitution could lead to runtime errors if a subclass does not behave as expected when used in a generic repository. In the long run, violating these principles would lead to a rigid, error-prone system that is difficult to extend and modify.
