#@author: Alexander Pangilinan
Feature: Production test for Makuake


  @Makuake @PROD
  Scenario Outline: User should be able to create an order.(URL 1)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Validate protocol and subdomain
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    And Add to cart for makuake
    Examples:
      | environment | partner | country_code | country_name   |
      | prod         | makuake | PH           | Philippines |

  @Makuake @PROD
  Scenario Outline: User should be able to create an order. (URL 2)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 2
    And Validate protocol and subdomain
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    And Add to cart for makuake
    Examples:
      | environment | partner | country_code | country_name   |
      | prod         | makuake | PH           | Philippines |

  @Makuake @PROD
  Scenario Outline: User should be able to create an order. (URL 3)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 3
    And Validate protocol and subdomain
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    And Add to cart for makuake
    Examples:
      | environment | partner | country_code | country_name   |
      | prod         | makuake | PH           | Philippines |

  @Makuake @PROD
  Scenario Outline: User should be able to create an order. (URL 4)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 4
    And Validate protocol and subdomain
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    And Add to cart for makuake
    Examples:
      | environment | partner | country_code | country_name   |
      | prod         | makuake | PH           | Philippines |

  @Makuake @PROD
  Scenario Outline: Successful navigation through the site.
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    And Validate "<partner>" Social Media Links
    And Validate product images and price for each categories.
    And Navigate to category tabs
    And Sort Products
    And Select an item from the product list

    Examples:
      | environment | partner | country_code | country_name   |
      | prod         | makuake | PH           | Philippines |

  @Makuake @PROD
  Scenario Outline: Successful login of user
    Given This user has "<partner>" url of selected "<environment>"
    When Navigate to ApplicationURL
    And Close newsletter form
    And Accept cookies
    And User navigate to account creation details
    And User enters email and password
    And User click login button
    And User must be able to proceed to profile page
    And User must be able to log out
    Examples:
      | environment | partner |
      | prod         | makuake |


