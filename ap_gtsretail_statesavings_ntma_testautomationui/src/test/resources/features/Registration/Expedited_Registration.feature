@Expedited_Registration
Feature: Expedited Registration E2E flow

#  Queries: [SSG]
#  1. Shankar to review the tags as follows:
#    - @Expedited_E2E123 ==> Shan { We can remove this scenario, because same journey is done in @expeditedE2E }
#    - @K13Regression ==> Shan -> { Updated to @TargetedMiniRegression }
#    - @expeditedE2E (is this is expedited E2E why is this not included in UAT_Regression ?)  ==> Shan { This is already under UAT_Regression }
#  2. Where are the test cases for user enumeration ? Do we need to script them ? ==> Shan { We need to Script for this }

  Background:
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page

  @ErrorMessage_LetsGetStart @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate Error message on Lets Get Started for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And  user click on next button of lets get stared page
    And verify the error message displayed on Lets get started page for "<Indicator>"

    Examples:
      | Scenario       | Indicator |
      | TC115 | Incorrect |

#    Shankar to add the scenario reference and update the examples to map the respective test case numbers
  @ErrorMessage_LetsGetStart_AllBlank @ErrorScenarios  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate Error message on Lets Get Started for All Blank
    And  user click on next button of lets get stared page
    And verify the error message displayed on Lets get started page for "<Indicator>"
    Examples:
      | Indicator |
      | Blank     |

  @ErrorMessage_EnterEmail @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate Error message on Enter Email for "<Scenario>"
#    And Setup ECR User data for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    And verify the error message displayed on Email page for "<Indicator>"

    Examples:
      | Scenario       | Indicator     |
      | TC117 | EmailMismatch |
      | TC118  | Invalid       |


  @ErrorMessage_EnterEmailBlank @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate Error message on Enter Email for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  Click on next button of email page
    And verify the error message displayed on Email page for "<Indicator>"

    Examples:
      | Scenario      | Indicator |
      | TC116 | Blank     |

  @ErrorMessage_WrongPIN  @ErrorScenarios   @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: validate Error Message on Enter PIN for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And Verify the error message displayed on Pin Page for "<Indicator>"

    Examples:
      | Scenario | Indicator | pin    |
      | TC120     | Incorrect | 678543 |
#      | TC04     | LessThan6 | 4532   | Before MS



  @PIN_Reissue @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: PIN Reissue for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then Click on Didn't get PIN link
    And Click on Resend PIN button
    And click on GotoStateSaving button of Reissue PIN Thankyou page

    Examples:
      | Scenario |
      | TC332    |

  @PIN_ReissueRequestExceeded @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: PIN Reissue Request Exceeded Modal for "<TCName>"
    Then user enter the details as per "<TCName>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then Click on Didn't get PIN link
    And Click on Resend PIN button
    And click on Close Button of Reissue PIN thankyou Page
    Then Click on Didn't get PIN link
    And Click on Resend PIN button
    And Click on Continue Registration button of Reissue Exceeded modal

    Examples:
      | TCName|Scenario |
      | TC333 | PIN_Reissue_Request_Exceeded        |

  @ErrorMessage_MobileNumber @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate Error Message on Enter Mobile Number <TCName>
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And open Email and click on email verification link
    And Enter all the details for mobile number page
    Then Click on continue button of mobile page
    And verify the error message displayed on Mobile number page for "<Indicator>"

    Examples:
    |TCName  | Scenario                      | Indicator      | pin    |
    | TC121 | Mobile_Number_BlankECR        | Blank          | 178285 |
    | TC122 | Mobile_Number_Incorrect_ECRNew       | Incorrect       | 455715 |
    | TC123 | Mobile_Number_MisMatch_ECRNew        | Mobile_MisMatch | 674457 |

#_MisMatch
  @Prefix  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate Error Message on Enter Mobile Number <TCName>
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And open Email and click on email verification link
    And Enter all the details for mobile number page with Prefix
    Then Click on continue button of mobile page
    And verify the error message displayed on Mobile number page for "<Indicator>"

    Examples:
      |TCName  | Scenario                      | Indicator      | pin    |
      | TC124 | Mobile_Number_Prefix_ECR | Prefix_MisMatch | 113199 |


  @ErrorMessage_Security @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate Error Message on Security Code <TCName>
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And open Email and click on email verification link
    And Enter all the details for mobile number page
    Then Click on continue button of mobile page
    And  Enter the security code
    Then Click on verify button of security code
    And  verify the error message displayed on Security code page for "<Indicator>"

    Examples:
      |TCName  | Scenario        | Indicator      | pin    |
      | TC126  | OTP_Blank_ECR       | Blank          | 942274 |
      | TC127 | OTP_Incorrect_ECR   | Incorrect       | 678377 |
      | TC128 | OTP_LessThan6_ECR    | LessThan6 | 057125 |



  @RegisterUsingSecondEmailLink
  Scenario Outline: Expedited Registration using Second Email Verification Link for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<Pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And Click on Didn't receive email link
    And Click on Resend email button
    And open Email and click on email verification link
    And Enter all the details for mobile number page
    Then Click on continue button of mobile page
    And  Enter the security code
    Then Click on verify button of security code
    And validate the create password page
    And Enter the password
    And click on Show password button
    And Click on complete button of password page
    And Validate the congratulations message
    And First time login using ECR User
    Examples:
      | Scenario | Pin    |
      | TC06     | 848008 |


   @Expedited_E2E123
  Scenario Outline: Expedited Registration E2E Happy flow for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And open Email and click on email verification link
    And Enter all the details for mobile number page
    Then Click on continue button of mobile page
    And  Enter the security code
    Then Click on verify button of security code
    And validate the create password page
    And Enter the password
    And click on Show password button
    And Click on complete button of password page
    And Validate the congratulations message
    And First time login using ECR User

  Examples:
    | Scenario |pin    |
    | TC128   |951037  |


  @TargetedMiniRegression
    @expeditedE2E @Post_Login_Regression @Regression @UAT_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Expedited Registration for "<TestCase>"
    Then user enter the details as per "<TestCase>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And open Email and click on email verification link
    And Enter all the details for mobile number page
    Then Click on continue button of mobile page
    And  Enter the security code
    Then Click on verify button of security code
    And validate the create password page
    And Enter the password
    And click on Show password button
    And Click on complete button of password page
    And Validate the congratulations message
    And First time login using ECR User

    Examples:
      | TestCase | pin    |
      | TC04     | 950511 |

  @AlreadyRegistered
  Scenario Outline: Already Registered for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And Verify Already Registered details page displayed

    Examples:
      | Scenario |
      | TC04     |


  @BlankPIN @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate Error Message on enter blank PIN for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then Click on continue button of PIN page
    And validate the error message displayed for blank pin "<PinIndicator>"
    Examples:
      | Scenario |  PinIndicator |
      | TC126    | Blank        |



  @MobileSecurityDidNotReceiveCode @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate didn't get code on check email for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And open Email and click on email verification link
    And Enter all the details for mobile number page
    Then Click on continue button of mobile page
    And Click on did not receive code link on mobile security on expedited flow
    And Validate didn't get code slider content on mobile security on expedited

    Examples:
      | Scenario |pin    |
      | TC30_ECR    |080982  |


  @ECREmailDidNotReceiveCode @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate didn't get code on check email for "<Scenario>"
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And Click on did not receive code link on check email on expedited flow
    And Validate didn't get email slider content on check email flow on expedited
    Examples:
      | Scenario |pin    |
      | TC338_ECR    |885410  |