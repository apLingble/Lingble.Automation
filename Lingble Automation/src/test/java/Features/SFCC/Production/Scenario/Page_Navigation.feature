#@author: Alexander Pangilinan
Feature: As a user I must be able to navigate through the site.

  @STAGING_PAGE_NAVIGATION
  Scenario Outline: Successfull navigation through the site.
    Given This user has "<partner>" url of selected "<environment>"
    Then Validate DNS Certificate validity
    Then Navigate to ApplicationURL
    Then Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    And Validate "<partner>" Social Media Links
    And Navigate to category tabs
    And Validate product prices and images
    And Filter Products "<partner>"
    And Sort Products
    And Select an item from the product list
    And Validate product image from product details
    And Navigate through item details "<partner>"

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
