 @All

Feature: KYC send and approve/decline feature

  #=====================================================================================================================
  #                                       Отправка заполненной формы KYC клиентом
  #=====================================================================================================================
  Scenario: User send KYC-request for administrator
    Given I have opened application log in page
    When I have select signature "1"
    When I have opened KYC-form
    When I have filled KYC-form
    Then I have posted KYC-request

  #=====================================================================================================================
  #                                      Администратор утверждает заявку KYC
  #=====================================================================================================================
  Scenario: Admin approve user`s KYC-request
    Given I have opened application admin login page
    When I have select signature "2"
    When I have opened KYC section
    When I have opened KYC request
    Then I have approved KYC request

  #=====================================================================================================================
  #                                       Отправка заполненной формы KYC клиентом
  #=====================================================================================================================
  Scenario: User send KYC-request for administrator 2
    Given I have opened application log in page
    When I have select signature "1"
    When I have opened KYC-form
    When I have filled KYC-form
    Then I have posted KYC-request

  #=====================================================================================================================
  #                                      Администратор отклоняет заявку KYC
  #=====================================================================================================================
  Scenario: Admin decline user`s KYC-request
    Given I have opened application admin login page
    When I have select signature "2"
    When I have opened KYC section
    When I have opened KYC request
    Then I have decline KYC request




