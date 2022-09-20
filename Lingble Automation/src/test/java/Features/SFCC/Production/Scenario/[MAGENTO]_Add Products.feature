#@author: Alexander Pangilinan
Feature: As a user I must be able to add products to cart

  Scenario Outline:
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Close newsletter form - Magento
    And Allow cookies
    And Search for product name "<partner>"
    And Select for item details "<partner>"
    And Add to cart

    Examples:
      | environment | partner   |
      | prod        | japanblue |
      | prod        | knifan    |
      | prod        | momotaro  |
