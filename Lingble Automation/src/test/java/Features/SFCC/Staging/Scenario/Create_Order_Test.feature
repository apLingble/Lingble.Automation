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
#    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    Then validate cart page item "<partner>"
    Then click checkout
    And Login | Guest | Create customer Account checkout "<payment_method>" "<country_code>"



    Examples:
      | environment | partner    | country_code | country_name  | payment_method |
      | stg         | attachment | US           | United States | AMEX           |
      | stg         | ace        | US           | United States | AMEX           |
      | stg         | briefing   | US           | United States | AMEX           |
      | stg         | dnd        | US           | United States | AMEX           |
      | stg         | ecchi      | US           | United States | AMEX           |
      | stg         | haku       | US           | United States | AMEX           |
      | stg         | igc        | US           | United States | AMEX           |
      | stg         | kokuyo     | US           | United States | AMEX           |
      | stg         | mago       | US           | United States | AMEX           |
      | stg         | makuake    | US           | United States | AMEX           |
      | stg         | maruzeki   | US           | United States | AMEX           |
      | stg         | metaphore  | US           | United States | AMEX           |
      | stg         | swans      | CA           | Canada        | AMEX           |
      | stg         | talex      | CN           | China         | AMEX           |
      | stg         | tanuki     | US           | United States | AMEX           |
      | stg         | tatras     | US           | United States | AMEX           |
      | stg         | toot       | US           | United States | AMEX           |
      | stg         | xgirl      | US           | United States | AMEX           |
      | stg         | xlarge     | US           | United States | AMEX           |