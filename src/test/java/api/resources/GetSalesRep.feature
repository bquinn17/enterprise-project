Feature: Support for getting a sales rep by id
  Scenario: The sales rep id is not found
    When a user calls "/orders/getSalesRep"
    Then the api will return 404
    

  Scenario: The sales rep id is found
    When a user calls "/orders/getSalesRep"
    Then the api will return 302