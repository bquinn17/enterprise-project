Feature: Support for getting a sales rep by id
  Scenario: The sales rep id is not found
    Given an invalid sales rep id
    When a user gets a salesRep "/orders" "one" "two"
    Then the api will return 404

#  TODO need to get valid sales rep
#  Scenario: The sales rep id is found
#    Given a valid sales rep id
#    When a user gets a salesRep "/orders/byrep" "3/15/2018" "4/15/2018"
#    Then the api will return 302