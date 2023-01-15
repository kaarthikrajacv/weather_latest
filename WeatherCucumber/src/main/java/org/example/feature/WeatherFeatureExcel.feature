@excel
Feature: Weather API Test data driven from Excel

  Scenario: Test Weather data for Mumbai
    Given The input specification for the city data from excel
    When Weather API is triigered
    Then Validate weather response for city

  Scenario: Test Weather data for Chennai
    Given The input specification for the city data from excel
    When Weather API is triigered
    Then Validate weather response for city

  Scenario: Test Weather data for Delhi
    Given The input specification for the city data from excel
    When Weather API is triigered
    Then Validate weather response for city
