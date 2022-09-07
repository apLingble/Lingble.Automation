#@author: Alexander Pangilinan
Feature: As a user I should be able to remove products from cart

  @RemoveProduct
  Scenario Outline: Successful removal of products from cart
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
    Then Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
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
      | environment | partner    | country_code | country_name   |
      | stg         | ace        | GB           | United Kingdom |
      | stg         | attachment | GB           | United Kingdom |
      | stg         | briefing   | GB           | United Kingdom |
      | stg         | dnd        | GB           | United Kingdom |
      | stg         | ecchi      | GB           | United Kingdom |
      | stg         | haku       | GB           | United Kingdom |
      | stg         | igc        | GB           | United Kingdom |
      | stg         | mago       | GB           | United Kingdom |
      | stg         | metaphore  | GB           | United Kingdom |
      | stg         | maruzeki   | GB           | United Kingdom |
      | stg         | swans      | GB           | United Kingdom |
      | stg         | kokuyo     | GB           | United Kingdom |
      | stg         | tanuki     | GB           | United Kingdom |
      | stg         | tatras     | GB           | United Kingdom |
      | stg         | toot       | GB           | United Kingdom |
      | stg         | xgirl      | GB           | United Kingdom |
      | stg         | xlarge     | GB           | United Kingdom |
      | stg         | talex      | GB           | United Kingdom |
