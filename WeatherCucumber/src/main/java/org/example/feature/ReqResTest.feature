Feature: Test ReqRes POST/PUT/DELETE request

  @reqres
  Scenario: Test POST Request in ResRes
    Given Input details for POST request
    When Submit the POST request
    Then Verify the POST response