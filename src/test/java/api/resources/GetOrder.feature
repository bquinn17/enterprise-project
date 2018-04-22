Feature: Support for getting a specific order by id
#  Scenario: The order id is not found
#    Given an order id not in the database
#    When a user gets a "retailOrder" "/orders/completed"
#    Then the api will return 404

#  Scenario: The order id is found
#    Given a serial number is in the database
#    When a user gets a "retailOrder" "/orders/completed"
#    Then the api will return 302

  Scenario: The order id is not found UNIT
    When getOrder is called with serial number "not_in_database"
    Then getOrder will return an order with a matching product id

  Scenario: The order id is found UNIT
    Given a serial number is in the database mock
    When getOrder is called with serial number "7331"
    Then getOrder will return an order with a matching product id