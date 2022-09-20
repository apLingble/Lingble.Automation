#@author: Alexander Pangilinan
Feature: Product Images and Prices should be displayed properly.

  @ValidateCategories
  Scenario Outline: Product Images and Prices should be displayed properly.
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    Then Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
    When Chat widget open minimize it
    And Validate product images and price for each categories.


    Examples:
      | environment | partner    |
      | prod        | attachment |
      | prod        | ace        |
      | prod        | briefing   |
      | prod        | dnd        |
      | prod        | ecchi      |
      | prod        | haku       |
      | prod        | kokuyo     |
      | prod        | igc        |
      | prod        | mago       |
      | prod        | maruzeki   |
      | prod        | metaphore  |
      | prod        | swans      |
      | prod        | talex      |
      | prod        | tanuki     |
      | prod        | tatras     |
      | prod        | toot       |
      | prod        | xlarge     |
