Feature:
  Scenario: Post a new client, check if account and credentials are posted
    Given "client/" endpoint
    And "Client" "Paul, Serafim, 123456, 1234, 150" in the request body
    When Call "POST" method
    Then Response code should be "201"
    Given "account/123456" endpoint
    When Call "GET" method
    Then Response code should be "200"
    And Response body should contain "123456, 150"

    Scenario: Post the same the client
      Given "client/" endpoint
      And "Client" "Paul, Serafim, 123456, 1234, 150" in the request body
      When Call "POST" method
      Then Response code should be "500"