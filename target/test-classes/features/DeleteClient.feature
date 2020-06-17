Feature: 
  Scenario: Delete the posted client and check if account was deleted
    Given "client/1" endpoint
    When Call "DELETE" method
    Then Response code should be "200"
    Given "account/123456" endpoint
    When Call "GET" method
    Then Response code should be "500"
    
  Scenario: Delete a non-existent client
    Given "client/100" endpoint
    When Call "DELETE" method
    Then Response code should be "204"
