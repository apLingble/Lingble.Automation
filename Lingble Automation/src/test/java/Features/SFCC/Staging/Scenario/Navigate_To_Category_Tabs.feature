#@author: Alexander Pangilinan
Feature: As as user I must be able to navigate in navigation tabs.

  @NavigateToCategoryTabs
  Scenario Outline: User should navigate to category tabs
    Given This user has "<partnerSite>" url of selected "<environment>"
    Then Navigate to ApplicationURL
    And Close newsletter form
    Then Accept cookies
    When Age consent for ecchiTokyo showed click yes
    Then Navigate to category tabs

    Examples:
      | environment | partnerSite | country_code | country_name   |
      | stg         | haku        | GB           | United Kingdom |