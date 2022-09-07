#@author: Alexander Pangilinan
Feature: As as user I must be able to login in the partner site.

  @AccountManagement
  Scenario Outline: Successful login of user
    Given This user has "<partner>" url of selected "<environment>"
    When Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
    And Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
    And User navigate to account creation details
    And User enters email and password
    And User click login button
    And User must be able to proceed to profile page
    And User must be able to log out

    Examples:
      | environment | partner    |
      | stg         | ace        |
      | stg         | attachment |
      | stg         | briefing   |
      | stg         | dnd        |
      | stg         | ecchi      |
      | stg         | haku       |
      | stg         | igc        |
      | stg         | kokuyo     |
      | stg         | mago       |
      | stg         | makuake    |
      | stg         | maruzeki   |
      | stg         | metaphore  |
      | stg         | swans      |
      | stg         | talex      |
      | stg         | tanuki     |
      | stg         | tatras     |
      | stg         | toot       |
      | stg         | xgirl      |
      | stg         | xlarge     |