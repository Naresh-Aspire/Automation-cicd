
Feature: Error Validation

  I want to use this template for my feature file

  @errorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page
    When Logged in the username<username> and password <password>
   
    Then "Incorrect email or password." error message is displayed.

    Examples: 
        | username                   |             password | 
      | testingnaresh@gmail.com    |     Aspiremay@12345876 | 
      
