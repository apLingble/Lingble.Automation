#@author: Alexander Pangilinan
Feature: As as user I must be able to login in the lingble link site, select partner site, and perform fulfillment flow.

  @LogIn
  Scenario Outline: An order number has been successfully fulfilled.
    Given Open Lingble Link "<partnerSite>" LoginPage.
    When User enters valid credentials.
    And User clicks log in button.
    Then Wait for LL Page to be ready.
    And Select Order List Menu.
    And User selects Partner Site.
    Then validate Selected Partner Site.
    And User sets the order list filter.
    Then Get first order number in the list.
    And Validate order number.
    And Validate the payment status.
    Then Check for No Location Assigned.
    Then User performs the fulfillment flow.
    And Validate if order status is completed.
    And Validate if fulfillment status is fulfilled.

    Examples:
      | partnerSite |
      | tanuki |



