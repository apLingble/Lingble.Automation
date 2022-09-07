#@author: Alexander Pangilinan
Feature: As as user I must be able to add products to cart.

  @AddToCart @PRD
  Scenario Outline: Successfully adding items to the cart (URL 1)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Validate protocol and subdomain
    When Age consent for ecchiTokyo showed click yes
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart

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

  Scenario Outline: Successfully adding items to the cart (URL 2)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 2
    And Validate protocol and subdomain
    When Age consent for ecchiTokyo showed click yes
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart

    Examples:
      | environment | partner    | country_code | country_name  |
      | prod        | ace        | PH           | Philippines   |
      | prod        | attachment | PH           | Philippines   |
      | prod        | briefing   | PH           | Philippines   |
      | prod        | dnd        | PH           | Philippines   |
      | prod        | ecchi      | PH           | Philippines   |
      | prod        | haku       | PH           | Philippines   |
      | prod        | igc        | PH           | Philippines   |
      | prod        | mago       | PH           | Philippines   |
      | prod        | maruzeki   | PH           | Philippines   |
      | prod        | metaphore  | PH           | Philippines   |
      | prod        | swans      | PH           | Philippines   |
      | prod        | talex      | PH           | Philippines   |
      | prod        | tanuki     | PH           | Philippines   |
      | prod        | tatras     | PH           | Philippines   |
      | prod        | toot       | PH           | Philippines   |
      | prod        | xlarge     | PH           | Philippines   |

#Mago - URL not contains PH Base on selected country
#Ecchi - element click intercepted to search icon

  Scenario Outline: Successfully adding items to the cart (URL 3)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 3
    And Validate protocol and subdomain
    When Age consent for ecchiTokyo showed click yes
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart

    Examples:
      | environment | partner    | country_code | country_name   |
      | prod        | ace        | PH           | Philippines   |
      | prod        | attachment | PH           | Philippines   |
      | prod        | briefing   | PH           | Philippines   |
      | prod        | dnd        | PH           | Philippines   |
      | prod        | ecchi      | PH           | Philippines   |
      | prod        | haku       | PH           | Philippines   |
      | prod        | igc        | PH           | Philippines   |
      | prod        | mago       | PH           | Philippines   |
      | prod        | maruzeki   | PH           | Philippines   |
      | prod        | metaphore  | PH           | Philippines   |
      | prod        | swans      | PH           | Philippines   |
      | prod        | talex      | PH           | Philippines   |
      | prod        | tanuki     | PH           | Philippines   |
      | prod        | tatras     | PH           | Philippines   |
      | prod        | toot       | PH           | Philippines   |
      | prod        | xlarge     | PH           | Philippines   |

  Scenario Outline: Successfully adding items to the cart (URL 4)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 4
    And Validate protocol and subdomain
    When Age consent for ecchiTokyo showed click yes
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart

    Examples:
      | environment | partner    | country_code | country_name   |
      | prod        | ace        | PH           | Philippines   |
      | prod        | attachment | PH           | Philippines   |
      | prod        | briefing   | PH           | Philippines   |
      | prod        | dnd        | PH           | Philippines   |
      | prod        | ecchi      | PH           | Philippines   |
      | prod        | haku       | PH           | Philippines   |
      | prod        | igc        | PH           | Philippines   |
      | prod        | mago       | PH           | Philippines   |
      | prod        | maruzeki   | PH           | Philippines   |
      | prod        | metaphore  | PH           | Philippines   |
      | prod        | swans      | PH           | Philippines   |
      | prod        | talex      | PH           | Philippines   |
      | prod        | tanuki     | PH           | Philippines   |
      | prod        | tatras     | PH           | Philippines   |
      | prod        | toot       | PH           | Philippines   |
      | prod        | xlarge     | PH           | Philippines   |