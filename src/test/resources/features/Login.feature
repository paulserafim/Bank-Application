Feature:
  Scenario: Add a new client through post
    Given "client/" endpoint
    And "Client" "Alex, Serafim, 123457, 1235, 200" in the request body
    When Call "POST" method
    Then Response code should be "201"
    Given "account/123457" endpoint
    When Call "GET" method
    Then Response code should be "200"
    And Response body should contain "123457, 200"

  Scenario: The new created user logs in with at the specified location and date
    Given "credentials/" endpoint
    And "Credentials" "123457, 1235, 46.6295847, 27.7313423, str. Decebal nr. 10 Vaslui, 2020-05-20T10:59:19" in the request body
    When Call "POST" method
    Then Response code should be "200"
    And Response body should contain "Alex, Serafim, 123457, 200"
    Given "loginEntry/1" endpoint
    When Call "GET" method
    Then Response code should be "200"
    And Response body should contain "Alex, Serafim, 123457, 200, 46.6295847, 27.7313423, str. Decebal nr. 10 Vaslui, 2020-05-20T10:59:19"

  Scenario: When attempting to login with wrong credentials
    Given "credentials/" endpoint
    And "Credentials" "123457, 1239, 46.6295847, 27.7313423, str. Decebal nr. 10 Vaslui, 2020-05-20T10:59:19" in the request body
    When Call "POST" method
    Then Response code should be "403"
    And Response body should contain "Invalid username or password"

