## Bestseller Assignment - Pedro Estima

Since I don't have prior experience in the retail domain, I found some of the functional requirements a bit challenging to interpret.
As a result, I had to make a few assumptions, which may differ from what was originally intended in the assignment. 

### Run the app

```
./gradlew build && ./gradlew bootRun
```

You can also open Swagger UI and check all the API

http://localhost:8080/swagger-ui/index.html

### Assumptions:

- Admins can only get a list of all Toppings sorted by number of orders (no other action is allowed)
- "Current amount of the cart and products should be communicated back to the caller" I don't understand when this information
  should be communicated back to the client, therefore I did it as response to make an order
- I didn't create a method to edit/delete drinks from your cart

### My decisions

- I'm using an in-memory instance of H2 DB
- My exceptions should have a message explaining with more detail what happened or even with the ID of the entity that caused the error
- For simplicity and lack of time I had to go with basic entities and didn't look into indices etc
- The DiscountRules I created are agnostic to the rule used to choose them. For instance, the Rule1 has no understanding about the minimum 12 euros purchase in order to be selected 
- I created a basic ControllerAdvice just to handle the exceptions
- Due to lack of time, I didn't create tests to all Services or Controllers, but I did implement them to those I found more relevant
- For easy of calculations, I decided to go with `double` but `BigDecimal` would be a better type to handle money
