
Feature: Purchase the Order from E-Commerce website
  I want to use this template for my feature file

Background: 
 Given I landed on Ecommerce page


  @tag2
  Scenario Outline: Positive test for purchasing the order
    Given Logged in the username<username> and password <password>
    When I add product name <product> from the cart.
    When Checkout <product> and submit the order
    Then  "THANKYOU FOR THE ORDER." message is displayed on confirmation page.

    Examples: 
      | username                   |             password |  product   |
      | testingnaresh@gmail.com    |     Aspiremay@12345 |  ADIDAS ORIGINAL   |
