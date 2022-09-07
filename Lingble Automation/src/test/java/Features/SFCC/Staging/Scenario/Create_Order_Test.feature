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
      | environment | partner    | country_code | country_name  | payment_method       |
      | stg         | attachment | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | ace        | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | briefing   | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | dnd        | CA           | Canada        | AMEX/DINERS/DISCOVER |
      | stg         | ecchi      | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | haku       | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | igc        | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | kokuyo     | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | mago       | CA           | Canada        | AMEX/DINERS/DISCOVER |
      | stg         | makuake    | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | maruzeki   | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | metaphore  | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | swans      | CA           | Canada        | AMEX/DINERS/DISCOVER |
      | stg         | talex      | CN           | China         | AMEX/DINERS/DISCOVER |
      | stg         | tanuki     | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | tatras     | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | toot       | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | xgirl      | US           | United States | AMEX/DINERS/DISCOVER |
      | stg         | xlarge     | US           | United States | AMEX/DINERS/DISCOVER |