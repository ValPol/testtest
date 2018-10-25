 @All

Feature: Log in feature
  User should have ability to log in/out system


  #=====================================================================================================================
  #                                          Вход в систему
  #=====================================================================================================================
  Scenario: Successful log in system by certificate
    Given I have opened application log in page
    When I have select signature "1"
    When I have opened KYC-form
    When I fill KYC-form
    Then I successfully log in



