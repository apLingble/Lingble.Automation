#@author: Alexander Pangilinan
Feature: As as user I must be able to navigate in navigation tabs.

  @CreateOrder @PRD
  Scenario Outline: User should navigate to category tabs
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    When Age consent for ecchiTokyo showed click yes
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    Then validate cart page item "<partner>"




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
