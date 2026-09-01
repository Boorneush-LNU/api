@Profile&Setting
Feature: Profile & Settings

#  Queries [SSG]
#  1. Shankar to confirm the following tags if they could be removed:
#    - @K13Regression  @K13RegPreprod ==> Shan -> { Updated to @TargetedMiniRegression }

  Background:
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page

    #  242599893. Use this SSCN & get the User email
  @ChangeEmail @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  @TargetedMiniRegression
  Scenario Outline: EmailID change request <Testcase Name>
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And click on change button in the "Your Email Address" section
    Then validate "Your Email Address-Change Email address" slider content
    Then Enter Mail & Click on Close button and verify it navigates to profile & setting page New Tc
    Then Validate Email
    And click on change button in the "Your Email Address" section
    Then enter new email address in the field
    And click on confirm button on "Your Email Address" slider
    Then validate "Your Email Address-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then validate "Your Email Address-Check Inbox" slider content
    Then open mailinator portal with new email
    Then click "Confirm change of email address" button in mail message body
    And validate email address verified message is displayed
    Then login to Ireland State Savings with "<data>" as data reference
    Then click on notifications from submenu

    Examples:
      | TCName | data|
      | TC31 |ChangeEmail|


  @ChangePassword @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  @TargetedMiniRegression
  Scenario Outline: Password Change "<TCName>"
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And click on change button in the "Your Password" section
    Then validate "Your Password-change your password" slider content
    And enter old and new password
    Then validate all password requirements are fulfilled
    When confirm button is enabled
    Then click on confirm button on "Your Password" slider
    And validate "Your Password-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then validate the success banner message is displayed for: "Change Password"
    Then click on notifications from submenu
    Then validate "change password" notification is displayed

  Examples:
    | TCName | data|
    | TC32 |ChangePassword|

  @FullNameChange @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  @TargetedMiniRegression
  Scenario Outline: Full name  change request "<TCName>"
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And click on request an update button in the "Your Full Name" section
    Then validate "Your Full Name" slider content

  Examples:
    | TCName | data|
    | TC33 |Generic User|


  @MobileNumberChange @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  @TargetedMiniRegression
  Scenario Outline: Mobile Number  change request "<TCName>"
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And click on request an update button in the "Your Mobile Number" section
    Then validate "Your Mobile Number" slider content

    Examples:
      | TCName | data|
      | TC34 |Generic User|

  @AddressChange @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  @TargetedMiniRegression
  Scenario Outline: Address  change request "<TCName>"
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And click on request an update button in the "Your Address" section
    Then validate "Your Address" slider content

    Examples:
      | TCName | data|
      | TC35 |Generic User|

  @ShowSSCN @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  @TargetedMiniRegression
  Scenario Outline: Check your SSCN details "<TCName>"
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And click on show your SSCN code button in the "Your SSCN Code" section
    Then validate "State Savings Customer Number (SSCN):" slider content

    Examples:
      | TCName | data|
      | TC36 |Generic User|


  @DashboardAddHoldings @Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  @TargetedMiniRegression
  Scenario Outline: Add Holdings from dashboard <TCName>
    Then login to Ireland State Savings with "<data>" as data reference
    Then click on "Are all of your holdings included in Ireland State Savings Online?" link
    Then validate "Add holding: Request to add holdings" slider content
    And select "Prize Bonds" from dropdown and add different holding
    Then click on add another product "Savings Bond 3 years" and add different holding
    And click remove button to remove "Savings Bond 3 years" product from the list
    Then click on confirm and download form button
    Then validate "Add holding:Thank you your holding is downloaded" slider content
    And validate "add holding form" is downloaded
    Then click close button on "Add holding" slider

    Examples:
      | TCName | data|
      | TC70 |Generic User|

  @DashboardAddHoldingsAllProduct @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Add Holdings from dashboard <TCName> All Products
    Then login to Ireland State Savings with "<data>" as data reference
    Then click on "Are all of your holdings included in Ireland State Savings Online?" link
    Then validate "Add holding: Request to add holdings" slider content
    And select "Prize Bonds" from dropdown and add different holding
    Then click on add another product "Savings Bond 3 years" and add different holding
    Then click on add another product "Savings Certificate 5 years" and add different holding
    Then click on add another product "National Solidarity Bond 4 years" and add different holding
    Then click on add another product "National Solidarity Bond 10 years" and add different holding
    Then click on add another product "Childcare Plus 6 years" and add different holding
    Then click on add another product "State Savings Account" and add different holding
    Then click on add another product "Instalment Savings 6 years" and add different holding
    And click remove button to remove "Savings Bond 3 years" product from the list
    And click remove button to remove "Savings Certificate 5 years" product from the list
    And click remove button to remove "National Solidarity Bond 4 years" product from the list
    And click remove button to remove "National Solidarity Bond 10 years" product from the list
    And click remove button to remove "Childcare Plus 6 years" product from the list
    And click remove button to remove "State Savings Account" product from the list
    And click remove button to remove "Instalment Savings 6 years" product from the list
    Then click on confirm and download form button
    Then validate "Add holding:Thank you your holding is downloaded" slider content
    And validate "add holding form" is downloaded
    Then click close button on "Add holding" slider

    Examples:
      | TCName | data|
      | TC30_AllProduct |Generic User Products|

  @SummaryAddHoldings @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  @TargetedMiniRegression
  Scenario Outline: Add Holdings from summary <TCName>
    Then login to Ireland State Savings with "<data>" as data reference
    When User selects the product and clicks on manage button
    Then click on "Are all of your holdings included in Ireland State Savings Online?" link
    Then validate "Add holding: Request to add holdings" slider content
    And select "Prize Bonds" from dropdown and add different holding
    Then click on add another product "Savings Bond 3 years" and add different holding
    And click remove button to remove "Savings Bond 3 years" product from the list
    Then click on confirm and download form button
    Then validate "Add holding:Thank you your holding is downloaded" slider content
    And validate "add holding form" is downloaded
    Then click close button on "Add holding" slider

    Examples:
      | TCName | data|
      | TC71 |Generic User Products|

  @ChangeEmailErrorMessagePS @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  @AutomationRegression
  Scenario Outline: Checking Error message while giving <TCName> for email change
    Then login to Ireland State Savings with "<data>" as data reference
    And User navigates to Email address Change section on the Profile and Settings
    When new email address is "<newEmailType>" ensure error message displayed
    Examples:
      | TCName | data|newEmailType|
      | TC17_Blank |ChangeEmail|CE-Blank|
      | TC18_SameEmail |ChangeEmail|CE-Blank|
      | TC19_Invalid |ChangeEmail|CE-Blank|

  @ChangePasswordErrorMessage @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: user checks the error message while giving <TCName> in fields
    Then login to Ireland State Savings with "<data>" as data reference
    And User navigates to Password Change section on the Profile and Settings
    When current password and new password type is "<PasswordType>" ensure error message displayed

    Examples:
      | TCName | data|PasswordType|
      | TC21 |ChangePassword|CP-SamePassword|
      | TC22 |ChangePassword|CP-InvalidOldPassword|
      | TC23 |ChangePassword|CP-BlankOldPassword|

  @HeaderValidation @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Validate Profile&Setting Headers <Testcase Name>
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    Then Validate Content of P&S Page

    Examples:
      | TCName | data|
      | TC105 |ChangeEmail|

  @ChangeEmailNotVerified @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Change Email not verified. Try logging in with old email
    Given login to Ireland State Savings with "TC38" as data reference
    Then click profile & settings on dashboard page
    And click on change button in the "Your Email Address" section
    Then enter new email address in the field
    And click on confirm button on "Your Email Address" slider
    Then enter the verification code
    And click on confirm button on verification code slider
    Then Refresh the page
    Given login to Ireland State Savings with "TC38" as data reference

  @ChangeEmailErrorMessage  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Checking Error message while giving <newEmailType> for email change
    Then login to Ireland State Savings with "<data>" as data reference
    And User navigates to Email address Change section on the Profile and Settings
    When new email address is "<newEmailType>" ensure error message displayed

    Examples:
      | data        | newEmailType |
      | TC38 | CE-AlreadyRegistered     |

# Need to give the password all the time. In credental.uat give the password as Dummy!2011 each & everytime. Because we are not completing the journey.
  @ChangePasswordDidnotGetCode  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Validate Didn't get your verification code slider content on change password
    Then login to Ireland State Savings with "ChangePasswordNoOTP" as data reference OTP
    Then click profile & settings on dashboard page
    And click on change button in the "Your Password" section
    And enter old and new password OTP
    Then click on confirm button on "Your Password OTP" slider
    And User click on Didn't receive the code for change password
    And validate "Your Password did not get Verification code" slider content
    Then enter the verification code

  @ChangeEmailDidNotGetCode @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Validate Didn't get your verification code slider content on change email
    Then login to Ireland State Savings with "TC38" as data reference
    Then click profile & settings on dashboard page
    And click on change button in the "Your Email Address" section
    Then Enter Mail & Click on Close button and verify it navigates to profile & setting page New Tc
    And click on change button in the "Your Email Address" section
    And click on confirm button on "Your Email Address" slider
    And User click on Didn't receive the code for change email
    And validate "Your Email Address-didn’t get Verification code" slider content

  @ChangeemailCancelAndClose @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Validate cancel and close in change email address flow
    Then login to Ireland State Savings with "TC38" as data reference
    Then click profile & settings on dashboard page
    And click on change button in the "Your Email Address" section
    Then Enter Mail & Click on Close button and verify it navigates to profile & setting page New Tc
    And click on change button in the "Your Email Address" section
    Then User click on close icon at top
    And click on change button in the "Your Email Address" section
    Then click on confirm button
    Then User click on close icon on email security code screen
    And click on change button in the "Your Email Address" section
    And click on confirm button on "Your Email Address" slider
    Then User click on cancel button on email security code screen
    And click on change button in the "Your Email Address" section
    And click on confirm button on "Your Email Address" slider
    And validate "Your Password-Verification code" slider content
    And User click on Didn't receive the code for change email
    Then User click on close button on did not receive code screen
    And click on change button in the "Your Email Address" section
    And click on confirm button on "Your Email Address" slider
    And validate "Your Password-Verification code" slider content
    And User click on Didn't receive the code for change email
    Then User click on cancel button on did not receive code screen