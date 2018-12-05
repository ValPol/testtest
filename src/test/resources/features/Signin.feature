 @All

Feature: user can sig in system

  #=====================================================================================================================
  #                                      Администратор утверждает заявку KYC
  #=====================================================================================================================
  Scenario: sign in
    Given I have opened application log in page
    When I have opened sign in page
    When I have set credentials
    When I have confirm new account
    Then I successfully log in

