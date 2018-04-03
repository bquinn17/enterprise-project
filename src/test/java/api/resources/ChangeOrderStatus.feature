Feature: Support for changing the status of a Retail Order
  Scenario: The order is not found in the database
    When a user calls "/orders/update/status"
    Then the api will return 404


  Scenario: The status passed in is not valid
    When a user calls "/orders/update/status"
    Then the api will return 304
    
  Scenario: The order is updated with a new status
    When a user calls "/orders/update/status"
    Then the api will return 202