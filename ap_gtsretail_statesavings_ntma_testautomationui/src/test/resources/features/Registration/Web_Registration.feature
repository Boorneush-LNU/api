#This feature is Decommissed as part of Digital SSO Registration Project
#  - All the tags are commented to exclude them from any regression run

@WebRegistration
Feature: Web Registration E2E flow


#  @K13Regression
#    @webRegistration @AutomationRegressionWeb1
  Scenario Outline: Web Registration E2E Happy flow <Testcase Name>
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    Then Enter all address details for Web Register user
    And Click on next button of We Matched your details page
    And Verify details of Check Details and continue page
    And Click on consent of check Details and continue page
    And click on next button of check Details and continue page
    And click on send code of confirm your mobile phone page
    And Enter the security code for web Registration
    And Click on Confirm button of security code for web Registration
    And verify the check your inbox page
    And open Email and click on email verification link
    And verify Email address Verified page
    And Enter password for web Registration
    And Click on Confirm button of Email address Verified page
    And Verify what happens next page displayed
    Examples:
      | Scenario |
      | TC72  |

#    Examples:
#      | Scenario |   Before MS
#      | Web_73   |
#      | Web_1    |
#      | Web_3    |
#      | Web_4    |
#      | Web_5    |
#      | Web_6    |
#      | Web_7    |
#      | Web_8    |
#      | Web_9    |
#      | Web_10   |
#      | Web_11   |
#      | Web_12   |
#      | Web_13   |
#      | Web_14   |
#      | Web_15   |
#      | Web_16   |
#      | Web_17   |
#      | Web_18   |
#      | Web_19   |
#      | Web_20   |
#      | Web_21   |
#      | Web_22   |
#      | Web_23   |
#      | Web_24   |
#      | Web_25   |
#      | Web_26   |
#      | Web_27   |
#      | Web_28   |
#      | Web_29   |
#      | Web_30   |
#      | Web_31   |
#      | Web_32   |

#   @EmailRegister
  Scenario Outline: Open Email and Register User
    Given open Email and click on email verification link for "<Scenario>"
    And verify Email address Verified page
    And Enter password for web Registration
    And Click on Confirm button of Email address Verified page
    Then Verify what happens next page displayed
    Examples:
      | Scenario |
      | Web_2    |

#  @PIN_Expired_Perform_WebRegistration
  Scenario Outline: Open Email and Register User
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
#    And Click on Continue button of Pin No Longer Valid modal
    Then Enter all address details for Web Register user
#    And Click on next button of Postal address page
    And Click on next button of We Matched your details page
    And Verify details of Check Details and continue page
    And Click on consent of check Details and continue page
    And click on next button of check Details and continue page
    And click on send code of confirm your mobile phone page
    And Enter the security code for web Registration
    And Click on Confirm button of security code for web Registration
    And verify the check your inbox page
    And open Email and click on email verification link
    And verify Email address Verified page
    And Enter password for web Registration
    And Click on Confirm button of Email address Verified page
    And Verify what happens next page displayed
    Examples:
      | Scenario     |
      | Web_73 |

#  @errorWeMatchDetailsPage @ErrorScenarios
  Scenario Outline: Open Email and Register User
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    Then Validate error message displayed for all "<fields>" on We Matched your details page
#     No need to delete the user in web utility
    Examples:
      | Scenario |fields|
      | Web_2    |Blank |
      | Web_2    |Registered Email |
      | Web_2    |Registered Mobile |

#  @consentError @ErrorScenarios
  Scenario Outline: Error message validation on We Match Details page <Testcase Name>
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    Then Enter all address details for Web Register user
    And Click on next button of We Matched your details page
    And Verify details of Check Details and continue page
    And click on next button of check Details and continue page
    Then Validate error message displayed when consent checkbox not selected
    Examples:
      | Scenario |
      | Web_2    |

#    @errorOTPPage @ErrorScenarios
  Scenario Outline: Error message validation on Confirm your mobile number page <Testcase Name>
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    Then Enter all address details for Web Register user
    And Click on next button of We Matched your details page
    And Verify details of Check Details and continue page
    And Click on consent of check Details and continue page
    And click on next button of check Details and continue page
    And click on send code of confirm your mobile phone page
    Then Validate error message on Confirm your Mobile Number page when otp is: "<fieldType>"
#    Delete this users
    Examples:
      | Scenario |fieldType|
      | Web_4    |Blank |
      | Web_6    |Invalid |
      | Web_7    |Expired |

#  @InvalidDOB  @AutomationRegressionWeb
  Scenario Outline: Validate error message for invalid DOB
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter invalid DOB and validate the error message as per "<Scenario>"

    Examples:
      | Scenario |
      | Invalid DOB   |


