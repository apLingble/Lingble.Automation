#@author: Alexander Pangilinan
Feature: As as user I must be able to move product to wishlist.

  @CreateOrder @PRD
  Scenario Outline: User should move product to wishlist
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    Then Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then Move mouse to minicart then click
    Then Add click add to wishlist button
    And Click remove to cart confirmation
    Then Open wishlist
    Then Check if partner needs to login to remove wishlist item "<country_code>" "<password>"

    Examples:
      | environment | partner    | country_code | country_name |
      | prod        | ace        | PH           | Philippines  |
      | prod        | attachment | PH           | Philippines  |
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