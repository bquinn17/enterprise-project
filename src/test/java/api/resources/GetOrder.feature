Feature: Support for getting a specific order by id
  Scenario: The order id is not found
    When a user calls "/orders/getOrder"
    Then the api will return 404


  Scenario: The order id is found
    When a user calls "/orders/getOrder"
    Then the api will return 302