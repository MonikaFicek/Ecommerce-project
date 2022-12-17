@tag
Feature: Error validation


  @ErrorValidation
  Scenario Outline: Positive testing submitting the order
    Given I landed on Ecommerce Page
    When Logged in username <name> and <password>
    Then "Incorrect email or password" message is displayed


    Examples:
      | name                  | password   |
      | rahulshetty@gmail.com | IamKing@00 |
