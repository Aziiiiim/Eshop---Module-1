# E-Shop  

A simple e-commerce application built with Spring Boot for managing products.  

## Reflection 1  

While reviewing my code, I noticed some areas that needed improvement. First, my code lacked comments, which made it harder to understand and maintain. I have now added concise and relevant comments to key parts of the controller, repository, and service to improve readability.  

Second, I identified a potential issue in the `findById` method—if the product was not found, it could return `null`, leading to unexpected errors. I fixed this by ensuring it throws a `NoSuchElementException` with a clear message. Additionally, in the `update` method, I handled the case where `findById` fails to avoid modifying a `null` product.  

Another important issue was with the product quantity field. Previously, users could enter any value, leading to errors. I improved this by enforcing numeric input using `type="number"`, adding a minimum value constraint, and displaying an error message when validation fails.  
