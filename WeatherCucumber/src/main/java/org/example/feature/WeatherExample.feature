@weather
Feature: Test the Open Weather Map APis for a city Weather information


  Scenario: Get the weather information of any city and validate

    Given Input data to get weather information of "Mumbai" with api key "5661a8ed7941f24452984807b47e5b89"
    When The OpenWeatherMap API is triggered
    Then Validate the response for OpenWeatherMap API for given city "Mumbai"

#  Scenario: Post the Employee Data to Server

  Scenario Outline: Get Weather Data for multiple cities and validate

    Given Input data to get weather for a <city> with api <key>
    When The OpenWeatherMap API is triggered
    Then Validae the response for the given <city>
    Examples:
      | city | key |
      |"Mumbai"|"5661a8ed7941f24452984807b47e5b89"|
      |"Chennai"|"5661a8ed7941f24452984807b47e5b89"|
      |"Kolkata"|"5661a8ed7941f24452984807b47e5b89"|
      |"Delhi"|"5661a8ed7941f24452984807b47e5b89"|


