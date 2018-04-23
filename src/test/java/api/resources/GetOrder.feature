Feature: Support for getting a specific order by id
  Scenario: The order id is not found
    When getOrder is called with serial number "not_in_database"
    Then getOrder will return an order with a matching product id

  Scenario: The order id is found
    Given a serial number is in the database mock
    When getOrder is called with serial number "7331"
    Then getOrder will return an order with a matching product id