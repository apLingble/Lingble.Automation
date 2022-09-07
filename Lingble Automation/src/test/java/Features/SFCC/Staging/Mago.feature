#@author: Alexander Pangilinan
Feature: Smoke test for Mago

#    Payment Methods: JCB, VISA/MASTER, PayPal, AMEX/DINERS/DISCOVER
#    Country: US | United States, GB | United Kingdom, CA | Canada

  @Mago @SmokeTest
  @CreateOrderTest
  Scenario Outline: User should navigate to category tabs
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
    Then Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    Then validate cart page item "<partner>"
    Then click checkout
    And Login | Guest | Create customer Account checkout "<payment_method>" "<country_code>"


    Examples:
      | environment | partner | country_code | country_name  | payment_method       |
      | stg         | mago    | US           | United States | AMEX/DINERS/DISCOVER |


  @CreateOrderForMultipleItems
  Scenario Outline: User should navigate to category tabs
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
    Then Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    And Search for second product name "<partner>"
    Then Select for second item details "<partner>"
    And Add to cart
    Then validate minicart second item "<partner>"
    Then validate cart page item "<partner>"
    Then click checkout
    And Login | Guest | Create customer Account checkout "<payment_method>" "<country_code>"


    Examples:
      | environment | partner | country_code | country_name   | payment_method |
      | stg         | mago    | GB           | United Kingdom | paypal         |


  @AddToCartFromWishlist
  Scenario Outline: User should add to cart item from wishlist
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
    Then Close newsletter form
    And Accept cookies
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

    #WISHLIST PAYMENT METHOD (VISA/MASTER, JCB)
    Examples:
      | environment | partner | country_code | country_name | payment_method | password  |
      | stg         | mago    | AU           | Australia    | VISA/MASTER    | P@ss12345 |


  @RemoveProduct
  Scenario Outline: Successful removal of products from cart
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
    Then Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
    Then Set to desire country "<country_code>" "<country_name>"
    And Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    And Remove item from minicart
    And Search for second product name "<partner>"
    Then Select for second item details "<partner>"
    Then Add to cart
    And validate cart page item "<partner>"
    Then Remove item from cart

    Examples:
      | environment | partner | country_code | country_name   |
      | stg         | mago    | GB           | United Kingdom |


  @EditProductInCart
  Scenario Outline: Successfully edited the product details in cart.
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Get the url then validate if it is contains demandware
    Then Close newsletter form
    And Accept cookies
    When Age consent for ecchiTokyo showed click yes
    Then Set to desire country "<country_code>" "<country_name>"
    Then Search for product name "<partner>"
    Then Select for item details "<partner>"
    And Add to cart
    Then validate minicart item "<partner>"
    Then validate cart page item "<partner>"
    And Edit products in cart "<partner>"

    Examples:
      | environment | partner | country_code | country_name   |
      | stg         | mago    | GB           | United Kingdom |


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
      | environment | partner | country_code | country_name   |
      | stg         | mago    | GB           | United Kingdom |


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
      | environment | partner |
      | stg         | mago    |


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
      | environment | partner |
      | stg         | mago    |


