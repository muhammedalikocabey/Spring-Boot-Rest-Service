#### Spring Boot REST service, which contains parity data where TL equivalents of currencies are kept together with the Parity code and the day it belongs, and enables certain operations on this parity data.

- A method was used to calculate the ratio of two currency units to each other, and this method was included in the Controller Class with the **Dependency Injection Pattern.**
- For yield change method calculation, 3 different functions were defined and these functions were implemented and used with the **Strategy Pattern.**
- The **Factory Pattern** was used to select this 3 yield change method calculation more effectively than the request parameter to the service.
- When unwanted situations occur in the requests sent by the user, the control of these situations was made with **Controller Advice Annotation** as **Global Exception Handling**. Each undesirable situation that occurred was handled separately.


