Feature: Support for getting a specific order by id
  Scenario: The order id is not found
    Given an order id not in the database
    When a user gets a "retailOrder" "/orders/completed"
    Then the api will return 404


  Scenario: The order id is found
    Given a serial number is in the database
    When a user gets a "retailOrder" "/orders/completed"
    Then the api will return 302