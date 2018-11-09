 @All

Feature: Log in feature
  User should have ability to log in/out system


  #=====================================================================================================================
  #                                       Отправка заполненной формы KYC клиентом
  #=====================================================================================================================
  Scenario: Successful log in system by certificate
    Given I have opened application log in page
    When I have select signature "1"
    When I have opened KYC-form
    When I have filled KYC-form
    Then I have posted KYC-request

  #=====================================================================================================================
  #                                      Администратор утверждает заявку KYC
  #=====================================================================================================================
  Scenario: Successful log in system by certificate
    Given I have opened application admin log in page
    When I have select signature "2"
    When I have opened KYC section
    When I have opened KYC request
    Then I have approved KYC request





