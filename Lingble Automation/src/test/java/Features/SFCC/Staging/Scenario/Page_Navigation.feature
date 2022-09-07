#@author: Alexander Pangilinan
Feature: As a user I must be able to navigate through the site.

  @STAGING_PAGE_NAVIGATION
  Scenario Outline: Successfull navigation through the site.
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
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
      | environment | partner    | country_code | country_name   |
      | stg         | attachment | GB           | United Kingdom |
      | stg         | ace        | GB           | United Kingdom |
      | stg         | briefing   | GB           | United Kingdom |
      | stg         | dnd        | GB           | United Kingdom |
      | stg         | ecchi      | GB           | United Kingdom |
      | stg         | haku       | GB           | United Kingdom |
      | stg         | igc        | GB           | United Kingdom |
      | stg         | maruzeki   | GB           | United Kingdom |
      | stg         | swans      | GB           | United Kingdom |
      | stg         | kokuyo     | GB           | United Kingdom |
      | stg         | tatras     | GB           | United Kingdom |
      | stg         | toot       | GB           | United Kingdom |
      | stg         | xgirl      | GB           | United Kingdom |
      | stg         | xlarge     | GB           | United Kingdom |
      | stg         | tanuki     | GB           | United Kingdom |
      | stg         | makuake    | GB           | United Kingdom |
      | stg         | talex      | GB           | United Kingdom |
      | stg         | mago       | GB           | United Kingdom |
      | stg         | metaphore  | GB           | United Kingdom |
