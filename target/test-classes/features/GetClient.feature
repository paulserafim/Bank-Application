Feature:
  Scenario: Get the posted client
    Given "client/1" endpoint
    When Call "GET" method
    Then Response code should be "200"
    And Response body should contain "1, Paul, Serafim, 123456, 150"

  Scenario: Get client by account number
    Given "client/custom?accountNumber=123456" endpoint
    When Call "GET" method
    Then Response code should be "200"
    And Response body should contain "1, Paul, Serafim, 123456, 150"