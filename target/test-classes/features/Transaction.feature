Feature:
  Scenario: Create two clients that would transfer money between each other
    Given "client/" endpoint
    And "Client" "Mihai, Popescu, 654321, 4321, 200" in the request body
    When Call "POST" method
    Then Response code should be "201"
    Given "client/" endpoint
    And "Client" "Ioan, Popa, 135798, 1357, 150" in the request body
    When Call "POST" method
    Then Response code should be "201"

  Scenario: Login with one of the users
    Given "credentials/" endpoint
    And "Credentials" "654321, 4321, 46.6295847, 27.7313423, str. Decebal nr. 10 Vaslui, 2020-05-20T10:59:19" in the request body
    When Call "POST" method
    Then Response code should be "200"
    And Response body should contain "Mihai, Popescu, 654321, 200"

  Scenario: Attempt a valid transaction
    Given "ledger/new" endpoint
    And "Transaction" "654321, 135798, 13.5, Mock transaction description, 2" in the request body
    When Call "POST" method
    Then Response code should be "200"
    And Response body should contain "654321, 135798, 13.5, Mock transaction description"
    Given "account/654321" endpoint
    When Call "GET" method
    Then Response code should be "200"
    And Response body should contain "186.5"
    Given "account/135798" endpoint
    When Call "GET" method
    Then Response code should be "200"
    And Response body should contain "163.5"

  Scenario: Attempt an invalid transaction
    Given "ledger/new" endpoint
    And "Transaction" "654321, 135798, 300, Mock transaction description, 2" in the request body
    When Call "POST" method
    Then Response code should be "403"
    And Response body should contain "You have insufficient funds to proceed with this transaction!"

  Scenario: Attempt a fraudulent login and transaction
    Given "credentials/" endpoint
    And "Credentials" "654321, 4321, 44.4377401, 25.9545541, Piata Victoriei Bucuresti, 2020-05-20T12:00:19" in the request body
    When Call "POST" method
    Then Response code should be "200"
    And Response body should contain "Mihai, Popescu, 654321"
    Given "ledger/new" endpoint
    And "Transaction" "654321, 135798, 13.5, Mock transaction description, 3" in the request body
    When Call "POST" method
    Then Response code should be "403"
    And Response body should contain "The transaction could not be processed due to security reasons!"
