#@author: Alexander Pangilinan
Feature: As a user I must be able to validate DNS certificate.

  @Production @Smoke
  Scenario Outline: Successful dns certificate validation.
    Given This user has "<partner>" url of selected "<environment>"
    Then Validate DNS Certificate validity

    Examples:
      | environment | partner    |
      | prod        | attachment |
      | prod        | ace        |
      | prod        | briefing   |
      | prod        | dnd        |
      | prod        | ecchi      |
      | prod        | haku       |
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
