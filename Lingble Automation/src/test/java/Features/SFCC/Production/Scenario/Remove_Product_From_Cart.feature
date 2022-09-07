#@author: Alexander Pangilinan
Feature: As a user I should be able to remove products from cart

  @RemoveProduct
  Scenario Outline: Successful removal of products from cart
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    Then Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    And Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    And Remove item from minicart
    And Search for second product name "<partner>"
    Then Select for second item details "<partner>"
    Then Add to cart
    And validate cart page item "<partner>"
    Then Remove item from cart

    Examples:
      | environment | partner    | country_code | country_name |
      | prod        | attachment | PH           | Philippines  |
      | prod        | ace        | PH           | Philippines  |
      | prod        | briefing   | PH           | Philippines  |
      | prod        | dnd        | PH           | Philippines  |
      | prod        | ecchi      | PH           | Philippines  |
      | prod        | haku       | PH           | Philippines  |
      | prod        | igc        | PH           | Philippines  |
      | prod        | mago       | PH           | Philippines  |
      | prod        | maruzeki   | PH           | Philippines  |
      | prod        | metaphore  | PH           | Philippines  |
      | prod        | swans      | PH           | Philippines  |
      | prod        | talex      | PH           | Philippines  |
      | prod        | tanuki     | PH           | Philippines  |
      | prod        | tatras     | PH           | Philippines  |
      | prod        | toot       | PH           | Philippines  |
      | prod        | xlarge     | PH           | Philippines  |
