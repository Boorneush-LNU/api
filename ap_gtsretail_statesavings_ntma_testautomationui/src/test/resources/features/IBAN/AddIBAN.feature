@AddIbanRegression @ManageIBAN

Feature: Add IBAN and Change IBAN feature
  #This feature file includes ADD IBAN scenarios and Change IBAN scenarios

  Background:
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page

  @AddIBAN_error @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Verify the IBAN using Verify Bank details option through <TCName>  prompt message
    Given login to Ireland State Savings with "<data>" as data reference
    Then click on add your bank details now link
    And When IBAN entered is "<IBANType>" ensure error message displayed
#    Examples: <Shankar to update the test data and automation master sheet to incorporate this additional test case>
#      | data         | IBANType          |
#      | AddIBANError | IBAN-ConsentError |
    Examples:
      | TCName | data         | IBANType      |
      | TC24   | AddIBANError | IBAN-Blank    |
      | TC25   | AddIBANError | IBAN-Invalid  |
      | TC26   | AddIBANError | IBAN-Non Sepa |
      | TC27   | AddIBANError | IBAN-ConsentError |


  @AddIBAN_OTPError @ErrorScenarios
  Scenario Outline: Verify that OTP expired using prompt message
    Given login to Ireland State Savings with "AddIBAN" as data reference
    Then click on add your bank details now link
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    When otp submitted is:"<OTPType>" ensure error message displayed

    Examples:
      | OTPType |
      | BLANK   |
      | LESS    |
      | INVALID |
      | EXPIRED |


  @changeIBAN_error  @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Verify that OTP expired using prompt message
    Given login to Ireland State Savings with "TC114_ChangeIbanError" as data reference
    Then click profile & settings on dashboard page
    Then click "change" button in the Your Bank Details section
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    When otp submitted is:"<OTPType>" ensure error message displayed

    Examples:
      | OTPType |
      | BLANK   |
      | LESS    |
      | INVALID |
      | EXPIRED |


#
  @K13Regression
  @addIBAN_Dashboard  @Regression @UAT_Regression @PREPROD_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: E2E journey for add/amend Dashboard iban
    Given login to Ireland State Savings with "TC39_AddIban" as data reference
    And click on the Notification menu and navigates to the Notification Page
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
    Then User clicks message toggle button and able to view the Confirm change modal
    Then Clicks on the Confirm and is able to view the alert message
    Then User click on your Savings Tab
    When Verify "Add IBAN" prompt message on dashboard
    Then click on add your bank details now link
    Then validate "Your Bank Details-Add IBAN" slider content
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then validate the success banner message is displayed for: "Add IBAN"
    And Verify iban is displayed in your bank detail
    Then click on notifications from submenu
    Then validate "IBAN" notification is displayed


  @AddIBAN_Cancel @UATRunNO @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Verify that user able to cancel the Add Bank details from Add/Amend Bank details page
    Given login to Ireland State Savings with "TC119" as data reference
    Then click on add your bank details now link
    And User enter the iban
    And Tick the checkbox on the iban page
    And User click on the cancel button


  @ADDIBAN_OTPCancel @UATRunNo @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Verify that user able to cancel the Add Bank details from security code page
    Given login to Ireland State Savings with "TC119" as data reference
    Then click on add your bank details now link
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And User clicks on otp cancel button


#    Remove 242601090 Iban
  @AddIBAN_CancelAndClose   @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Verify that user able to cancel and close from add iban slider
    Given login to Ireland State Savings with "TC38" as data reference
    Then click on add your bank details now link
    And User click on cancel button on Slider
    Then click on add your bank details now link
    And User click on close button on addIban Slider

    #    Remove 242601090 Iban
  @AddIBAN_verifyCancelAndClose   @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Verify that user able to cancel and close from verification code page for add iban
    Given login to Ireland State Savings with "TC38" as data reference
    Then click on add your bank details now link
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And User click on cancel button on changeIban verification Slider
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And User click on close button on changeIban Verification Slider

  @AddIBAN_didnotreceiveCancelAndClose  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Verify that user able to cancel and close from code not received page for add iban
    Given login to Ireland State Savings with "TC38" as data reference
    Then click on add your bank details now link
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And User click on Didn't receive the code
    And User click on cancel button on code not received Slider
    And User click on Didn't receive the code
    And User click on close button on code not received Slider


  @changeIbancloseandcancel    @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Verify cancel and close button in change IBAN slider
    Given login to Ireland State Savings with "TC38" as data reference
    Then click profile & settings on dashboard page
    Then click "change" button in the Your Bank Details section
    And User click on cancel button on Slider bar
    Then click "change" button in the Your Bank Details section
    And User click on close button on changeIban Slider

  @changeIbanverifycloseandcancel  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Verify cancel and close button in change IBAN slider for verification code screen
    Given login to Ireland State Savings with "TC38" as data reference
    Then click profile & settings on dashboard page
    Then click "change" button in the Your Bank Details section
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And User click on cancel button on changeIban verification Slider
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And User click on close button on changeIban Verification Slider

  @CHANGEIBAN_didnotreceivecancelandclose  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Verify cancel and close button in did not receive code screen for change iban
    Given login to Ireland State Savings with "TC38" as data reference
    Then click profile & settings on dashboard page
    Then click "change" button in the Your Bank Details section
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And User click on Didn't receive the code
    And User click on cancel button on code not received Slider
    And User click on Didn't receive the code
    And User click on close button on code not received Slider


  @addIBAN_Profile&Setting  @Regression @UAT_Regression @PREPROD_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: E2E journey for add/amend iban P&S
    Given login to Ireland State Savings with "TC37" as data reference
    And click on the Notification menu and navigates to the Notification Page
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
    Then User clicks message toggle button and able to view the Confirm change modal
    Then Clicks on the Confirm and is able to view the alert message
    Then click profile & settings on dashboard page
    Then click "change" button in the Your Bank Details section
    Then validate "Your Bank Details-Add IBAN" slider content
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then validate the success banner message is displayed for: "Add IBAN"
    And Verify iban is displayed in your bank detail
    Then click on notifications from submenu
    Then validate "IBAN" notification is displayed


  @changeIBAN_Profile&Setting  @Regression @UAT_Regression @PREPROD_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: E2E journey for add/amend iban
    Given login to Ireland State Savings with "TC37" as data reference
    Then click profile & settings on dashboard page
    Then click "change" button in the Your Bank Details section
    Then validate "Your Bank Details-Change IBAN" slider content
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then validate the success banner message is displayed for: "Add IBAN"
    And Verify iban is displayed in your bank detail
    Then click on notifications from submenu
    Then validate "IBAN" notification is displayed

  @didnotreceiveonchangeiban  @Regression @UAT_Regression @PREPROD_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Verify did not receive code screen on change Iban
    Given login to Ireland State Savings with "TC38" as data reference
    Then click profile & settings on dashboard page
    Then click "change" button in the Your Bank Details section
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And User click on Didn't receive the code
    And validate "Your bank details did not get Verification code" slider content