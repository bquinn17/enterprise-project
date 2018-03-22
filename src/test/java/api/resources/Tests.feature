Feature: user can get a a refund
  Scenario: client makes call to GET /orders/new/refund
    When the client calls /orders/new/refund
    Then the server creates a new order
    And the client receives status code of 200