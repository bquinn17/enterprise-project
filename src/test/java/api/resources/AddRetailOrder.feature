Feature: Tests Related to AddRetailOrder  Endpoint: /orders/retail/new
  Scenario: Client creates new order without products and is expecting a 400 Bad Request
    Given a client submits an order without a product
    Then we expect to receive a 400 Bad Request response