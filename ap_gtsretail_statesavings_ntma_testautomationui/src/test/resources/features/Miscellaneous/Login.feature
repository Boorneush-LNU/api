#Queries [SSG]:
#  Need to discuss if the '@LockedUser' scenario can be included in the scope of regression - Shankar to confirm the feasibility of the same? ==> Shan -> { We can use this scenario-Working }

@Login
Feature: Login


  Background:
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page

  @ValidLogin @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Sign In with valid credentials <TCName>
    Then login to Ireland State Savings with "<data>" as data reference

    Examples:
      | TCName | data|
      | TC26 |Generic User|

# Change the Credential for PP.
  @PasswordReset @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Forgot password
    Then click the Forgot Password button to initiate the password reset process
    Then validate "Forgot Password:Reset Password" slider content
    Then enter valid email address and click Next
    Then validate "Forgot Password:Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then validate "Forgot Password:Check Email" slider content
    Then open mailinator portal with new email
    Then click the change password button present in the email body
    And  enter new password, click next to confirm input
    Then a password reset successfully message is displayed

  @InvalidLogin  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Sign In with invalid credentials <scenario> for <caseType>
    Then Validate error message on sign in page for "<scenario>"
#    Examples:
    #    Examples: <Shankar to update the test data and automation master sheet to incorporate this additional test case>
##      | Pending Registration       |  --> Added for this scenario
##      | Multiple Failed Logins     |  --> This is already covered in @LockedUser scenario

    Examples:
      | scenario |caseType|
      | TC1 |    Blank Username Pwd         |
      | TC2 |  Invalid Username Pwd           |
      | TC3 |  Invalid Username Valid Pwd           |
      | TC4 |     Valid Username Invalid Pwd        |
      | Pending Registration| Pending Registration  |


  @ErrorCheckOnOTPPage   @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate error message on verification code page when OTP is <TCName>
    Then login to Ireland State Savings with "<data>" as data
    When verification code entered is "<OTP field>" ensure error message displayed

    Examples:
      | TCName | data|OTP field   |
      | TC5 |Generic User|Blank Verification Code |
      | TC6 |Generic User|Wrong Verification Code|
      | TC8 |Generic User|Incorrect Verification Code Length|


  @ResetPwdEmailErrorMsg  @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Forgot password when <email field> for <caseType>
    And click the Forgot Password button to initiate the password reset process
    Then validate "Forgot Password:Reset Password" slider content
    When email entered is "<email field>" ensure error message displayed
   #    Examples: <Shankar to update the test data for TC54>
    Examples:
      | email field |caseType|
      | TC10 |     FWP-Blank Email           |
      | TC11 |   FWP-Invalid Email             |
      | TC12 |    FWP-WrongEmail           |
      | TC13 |   FWP-Pending Registration            |
      | TC54 |     FWP-Locked User         |

  @ResetPwdErrorMsg  @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Reset password using old password
    Then click the Forgot Password button to initiate the password reset process
    Then validate "Forgot Password:Reset Password" slider content
    Then enter valid email address and click Next
    Then validate "Forgot Password:Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then validate "Forgot Password:Check Email" slider content
    Then open mailinator portal with new email
    Then click the change password button present in the email body
    And  enter old password:"Dummy!2022", click next to confirm input
    Then validate error message "Please choose a password that you haven't used before." is displayed

  @LockedUser
  Scenario: Validate error message for invalid password 3 attempts
    And validate "Multiple Failed Logins" error message content on login

  @ForgotpasswordDidNotreceivecode  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Validate did not receive code on forgot password flow
    Then click the Forgot Password button to initiate the password reset process
    Then enter valid email address and click Next
    Then User click on Didn't receive the code for forgot password
    And validate "Forgot password did not get Verification code" slider content on login

  @CheckemailDidNotreceivecode  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Validate did not receive email on forgot password flow
    Then click the Forgot Password button to initiate the password reset process
    Then enter valid email address and click Next
    Then enter the verification code
    And click on confirm button on verification code slider
    Then User click on Didn't receive the code on check email page
    And validate "Forgot Password did not get Email" slider content on login

  @ResetPasswordfieldvalidation
  Scenario: Reset password field validation
    Then click the Forgot Password button to initiate the password reset process
    Then enter valid email address and click Next
    Then enter the verification code
    And click on confirm button on verification code slider
    Then open mailinator portal with new email
    Then click the change password button present in the email body
    And  validate next button when password requirement is not met

  @Didnotreceivecodeonotp @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Validate did not receive code on otp page during login
    Then login to Ireland State Savings with "TC_11" as data
    Then User click on Didn't receive the code on OTP page
    And validate "Login OTP page did not get Verification code" slider content on login

  @ForgotpasswordDidNotreceivecodeSlider  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Validate did not receive code on forgot password Negative Flow
    Then click the Forgot Password button to initiate the password reset process
    Then enter valid email address and click Next
    Then User click on Didn't receive the code for forgot password
    And validate "Forgot password did not get Verification code" slider content on login

  @CheckemailDidNotreceivecodeSlider  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Validate did not receive email on forgot password Negative flow
    Then click the Forgot Password button to initiate the password reset process
    Then enter valid email address and click Next
    Then enter the verification code
    And click on confirm button on verification code slider
    Then User click on Didn't receive the code on check email page
    And validate "Forgot Password did not get Email" slider content on login

  @ResetPwdErrorMsgValidation  @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Reset password using old password Validation
    Then click the Forgot Password button to initiate the password reset process
    Then validate "Forgot Password:Reset Password" slider content
    Then enter valid email address and click Next
    Then validate "Forgot Password:Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then validate "Forgot Password:Check Email" slider content
    Then open mailinator portal with new email
    Then click the change password button present in the email body
    And  enter old password:"Dummy!2022", click next to confirm input
    Then validate error message "Please choose a password that you haven't used before." is displayed
