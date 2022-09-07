#@author: Alexander Pangilinan
Feature: As as user I must be able to create account in the partner site.

  @UserCreation
  Scenario Outline: Successful creation of user account
    Given This user has "<partner>" url of selected "<environment>"
    When Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
    And Age consent for ecchiTokyo showed click yes
    And Close newsletter form
    And Accept cookies
    And User navigate to account creation details
    And User enter firstname
    And User enter lastname
    And User enter phone
    And User enter email
    And User enter confirmEmail
    And User enter password
    And User enter confirmPassword
    And User click create account button
    And User must be able to proceed to profile page
    Then User must be able to log out

    Examples:
      | environment | partner     |
      | stg         | ace         |
      | stg         | attachment  |
      | stg         | briefing    |
      | stg         | dnd         |
      | stg         | ecchi       |
      | stg         | haku        |
      | stg         | igc         |
      | stg         | kokuyo      |
      | stg         | mago        |
      | stg         | makuake     |
      | stg         | maruzeki    |
      | stg         | metaphore   |
      | stg         | swans       |
      | stg         | talex       |
      | stg         | tanuki      |
      | stg         | tatras      |
      | stg         | toot        |
      | stg         | xgirl       |
      | stg         | xlarge      |

