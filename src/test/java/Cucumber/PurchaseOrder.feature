@tag
  Feature: Purchase order from Ecommerce Website
    Background:
      Given I landed on Ecommerce Page

    @Regression
    Scenario Outline: Positive testing submitting the order
      Given Logged in username <name> and <password>
      When I add the product <productName> to Cart
      And Checkout <productName> and submit the order
      Then "THANKYOU FOR THE ORDER." verify if message is displayed on ConfirmationPage


      Examples:
        | name                  | password    | productName     |
        | rahulshetty@gmail.com | IamKing@000 | ZARA COAT 3     |
        | anshika@gmail.com     | IamKing@000 | ADIDAS ORIGINAL |
