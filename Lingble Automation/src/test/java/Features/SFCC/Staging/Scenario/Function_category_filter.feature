#@author: Alexander Pangilinan
Feature: As as user I must be able to login in the partner site.

  @AccountManagement
  Scenario Outline: Successful login of user
    Given User has valid credentials
    When User enters email and password
    And User click login button
    Then User must be able to proceed to profile page
    And User must be able to validate "<email>"
    Then User must be able to log out
    Examples:
      | email |
      | alexander@lingble.com |

#  create Code > push a copy of your repo (github) > execute of scripts (jenkins)

#  create new function > test new function > commit changes and push to specific branch > review > merge to main

