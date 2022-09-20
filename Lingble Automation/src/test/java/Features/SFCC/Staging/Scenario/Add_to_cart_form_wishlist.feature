#@author: Alexander Pangilinan
Feature: User should open the account and add product to wishlist then add it into cart.

  @StagingGivenSteps
  Scenario Outline: User should add to cart item from wishlist
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
    Then Close newsletter form
    And Accept cookies
#    When Chat widget open minimize it
    When Age consent for ecchiTokyo showed click yes
    Then Set to desire country "<country_code>" "<country_name>"
    And Open login account
    Then validate account landing page
    And Login user account "<country_code>" "<password>"
    Then Proceed to login
    And click minicart
    Then Check cart if empty or not "<partner>"
    Then click checkout
    And Enter shipping details "<country_code>"
    Then Validate order summary
    And Enter checkout billing details "<payment_method>" "<country_code>"
    Then Validate order receipt
    And validate order number in LL


    Examples:
      | environment | partner    | country_code | country_name | payment_method | password  |
      | stg         | attachment | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | ace        | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | briefing   | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | dnd        | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | ecchi      | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | haku       | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | igc        | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | kokuyo     | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | mago       | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | makuake    | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | maruzeki   | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | metaphore  | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | swans      | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | tanuki     | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | tatras     | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | toot       | AU           | Australia    | VISA           | P@ss12345 |
      | stg         | xgirl      | CA           | Canada       | VISA           | P@ss12345 |
      | stg         | xlarge     | CA           | Canada       | VISA           | P@ss12345 |


#  @StagingGivenSteps
#  Scenario Outline: User should add to cart item from wishlist
#    Given This user has "<partner>" url of selected "<environment>"
#    Then Navigate to ApplicationURL
#    And Get the url then validate if it is contains demandware
#    Then Close newsletter form
#    And Accept cookies
##    When Chat widget open minimize it
#    When Age consent for ecchiTokyo showed click yes
#    Then Set to desire country "<country_code>" "<country_name>"
#    And Open login account
#    Then validate account landing page
#    And Login user account "<country_code>" "<password>"
#    Then Proceed to login
#    And click minicart
#    Then Check cart if empty or not "<partner>"
#    Then click checkout
#    And Enter shipping details "<country_code>"
#    Then Validate order summary
#    And Enter checkout billing details "<payment_method>" "<country_code>"
#    Then Validate order receipt
#    And validate order number in LL
#
#
#    Examples:
#      | environment | partner    | country_code | country_name | payment_method | password  |
#      | stg         | attachment | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | ace        | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | briefing   | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | dnd        | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | ecchi      | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | haku       | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | igc        | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | kokuyo     | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | mago       | AU           | Australia    | JCB            | P@ss12345 |
##      | stg         | makuake    | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | maruzeki   | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | metaphore  | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | swans      | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | tanuki     | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | tatras     | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | toot       | AU           | Australia    | JCB            | P@ss12345 |
#      | stg         | xgirl      | CA           | Canada       | JCB            | P@ss12345 |
#      | stg         | xlarge     | CA           | Canada       | JCB            | P@ss12345 |