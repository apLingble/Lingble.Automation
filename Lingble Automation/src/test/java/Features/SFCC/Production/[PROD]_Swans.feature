#@author: Alexander Pangilinan
Feature: Production Test for Swans

#  CREATE ORDER TEST
  @Swans @Production
  Scenario Outline: User should be able to create an order
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
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
      | environment | partner    | country_code | country_name   |
      | prod        | swans   | PH           | Philippines    |

#  CREATE ORDER FOR MULTIPLE
  @Swans @Production
  Scenario Outline: User should be able to create multiple order
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    And Search for second product name "<partner>"
    Then Select for second item details "<partner>"
    And Add to cart
    Then validate minicart second item "<partner>"
    Examples:
      | environment | partner    | country_code | country_name   |
      | prod        | swans   | PH           | Philippines    |

#  MOVE PRODUCT TO WISHLIST
  @Swans @Production
  Scenario Outline: User should be able to move product to wishlist
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    Then Close newsletter form
    And Accept cookies
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
      | environment | partner    | country_code | country_name   | password  |
      | prod        | swans   | PH           | Philippines    | P@ss12345 |

#  REMOVE PRODUCT FROM CART
  @Swans @Production
  Scenario Outline: Successful removal of products from cart
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    And Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    And Remove item from minicart
    And Search for second product name "<partner>"
    Then Select for second item details "<partner>"
    Then Add to cart
    And validate cart page second item "<partner>"
    Then Remove second item from cart
    Examples:
      | environment | partner    | country_code | country_name   |
      | prod        | swans   | PH           | Philippines    |

#  EDIT PRODUCT FROM CART
  @Swans @Production
  Scenario Outline: Successfully edited the product details in cart.
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    Then validate cart page item "<partner>"
    And Edit products in cart "<partner>"
    Examples:
      | environment | partner    | country_code | country_name   |
      | prod        | swans   | PH           | Philippines    |

#  USER LOGIN LOGOUT
  @Swans @Production
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
      | environment | partner    |
      | prod        | swans   |

#  PAGE NAVIGATION
  @Swans @Production
  Scenario Outline: Successfull navigation through the site.
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    And Validate "<partner>" Social Media Links
    And Validate product images and price for each categories.
    And Navigate to category tabs
    And Filter Products "<partner>"
    And Sort Products
    And Select an item from the product list
    And Validate product image from product details
    And Navigate through item details "<partner>"
    Examples:
      | environment | partner    | country_code | country_name   |
      | prod        | swans   | PH           | Philippines    |

#  ADD PRODUCT TO CART (URL 1)
  @Swans @Production
  Scenario Outline: Successfully adding items to the cart (URL 1)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Validate protocol and subdomain
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Examples:
      | environment | partner    | country_code | country_name   |
      | prod        | swans   | PH           | Philippines    |

#  ADD PRODUCT TO CART (URL 2)
  @Swans @Production
  Scenario Outline: Successfully adding items to the cart (URL 2)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 2
    And Validate protocol and subdomain
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Examples:
      | environment | partner    | country_code | country_name   |
      | prod        | swans   | PH           | Philippines    |

#  ADD PRODUCT TO CART (URL 3)
  @Swans @Production
  Scenario Outline: Successfully adding items to the cart (URL 3)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 3
    And Validate protocol and subdomain
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Examples:
      | environment | partner    | country_code | country_name   |
      | prod        | swans   | PH           | Philippines    |

#  ADD PRODUCT TO CART (URL 4)
  @Swans @Production
  Scenario Outline: Successfully adding items to the cart (URL 4)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 4
    And Validate protocol and subdomain
    Then Close newsletter form
    And Accept cookies
    When Chat widget open minimize it
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Examples:
      | environment | partner    | country_code | country_name   |
      | prod        | swans   | PH           | Philippines    |