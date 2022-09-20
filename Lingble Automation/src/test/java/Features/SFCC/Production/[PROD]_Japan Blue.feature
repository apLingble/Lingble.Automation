#@author: Alexander Pangilinan
Feature: Production Test for Japan Blue Jeans

  Scenario Outline: As a user, I must be able to edit the products in the cart
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Close newsletter form - Magento
    And Allow cookies
    And Search for product name "<partner>"
    And Select for item details "<partner>"
    And Add to cart
    Then Edit products in cart page magento"<partner>"

    Examples:
      | environment | partner   |
      | prod        | japanblue |

  Scenario Outline: As a user I must be able to remove products from cart
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Close newsletter form - Magento
    And Allow cookies
    And Search for product name "<partner>"
    And Select for item details "<partner>"
    And Add to cart
    And Remove item from cart - Magento

    Examples:
      | environment | partner   |
      | prod        | japanblue |

  Scenario Outline: As a user I must be able to add products to cart (URL 1)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Close newsletter form - Magento
    And Allow cookies
    And Search for product name "<partner>"
    And Select for item details "<partner>"
    And Add to cart

    Examples:
      | environment | partner   |
      | prod        | japanblue |

  Scenario Outline: As a user I must be able to add products to cart (URL 2)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 2
    And Close newsletter form - Magento
    And Allow cookies
    And Search for product name "<partner>"
    And Select for item details "<partner>"
    And Add to cart

    Examples:
      | environment | partner   |
      | prod        | japanblue |

  Scenario Outline: As a user I must be able to add products to cart (URL 3)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 3
    And Close newsletter form - Magento
    And Allow cookies
    And Search for product name "<partner>"
    And Select for item details "<partner>"
    And Add to cart

    Examples:
      | environment | partner   |
      | prod        | japanblue |

  Scenario Outline: As a user I must be able to add products to cart (URL 4)
    Given This user has "<partner>" url of selected "<environment>"
    Then Navigate to ApplicationURL 4
    And Close newsletter form - Magento
    And Allow cookies
    And Search for product name "<partner>"
    And Select for item details "<partner>"
    And Add to cart

    Examples:
      | environment | partner   |
      | prod        | japanblue |