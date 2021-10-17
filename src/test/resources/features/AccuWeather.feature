Feature: As an accuweather user I should be able to compare web temperature value with that of value returned from API

  @Web
  Scenario: Fetch the temperature from the web application
    Given user is on accuweather landing page
    When user enters city as "Bengaluru"
    And user search for the temperature
    Then temperature of the city gets displayed
    And user records the temperature of "Bengaluru"

  @API
  Scenario: Fetch the temperature from the API call
    Given user create the uri and make the API call
    Then the status of the response should be 200
    And user create file and write response in it

  @Compare
  Scenario: Compare the temperature value in Web app with that of API
    Given user read temperature values of "Bengaluru"
    When user compare both temperature values of "Bengaluru"
    Then temperature difference should be within range for "Bengaluru"