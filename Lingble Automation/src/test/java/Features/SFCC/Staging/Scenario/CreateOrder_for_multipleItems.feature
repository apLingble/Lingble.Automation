#@author: Alexander Pangilinan
Feature: As as user I must be able to navigate in navigation tabs.

  @StagingGivenSteps
  Scenario Outline: User should navigate to category tabs
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
    Then Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    And Search for second product name "<partner>"
    Then Select for second item details "<partner>"
    And Add to cart
    Then validate minicart second item "<partner>"
    Then validate cart page item "<partner>"
    Then click checkout
    And Login | Guest | Create customer Account checkout "<payment_method>" "<country_code>"



    Examples:
      | environment | partner    | country_code | country_name   | payment_method |
      | stg         | attachment | GB           | United Kingdom | paypal         |
      | stg         | ace        | GB           | United Kingdom | paypal         |
      | stg         | briefing   | GB           | United Kingdom | paypal         |
      | stg         | dnd        | GB           | United Kingdom | paypal         |
      | stg         | ecchi      | GB           | United Kingdom | paypal         |
      | stg         | haku       | GB           | United Kingdom | paypal         |
      | stg         | igc        | GB           | United Kingdom | paypal         |
      | stg         | kokuyo     | GB           | United Kingdom | paypal         |
      | stg         | mago       | GB           | United Kingdom | paypal         |
      | stg         | maruzeki   | GB           | United Kingdom | paypal         |
      | stg         | metaphore  | GB           | United Kingdom | paypal         |
      | stg         | swans      | GB           | United Kingdom | paypal         |
      | stg         | talex      | GB           | United Kingdom | paypal         |
      | stg         | tanuki     | GB           | United Kingdom | paypal         |
      | stg         | tatras     | GB           | United Kingdom | paypal         |
      | stg         | toot       | GB           | United Kingdom | paypal         |
      | stg         | xgirl      | GB           | United Kingdom | paypal         |
      | stg         | xlarge     | GB           | United Kingdom | paypal         |

