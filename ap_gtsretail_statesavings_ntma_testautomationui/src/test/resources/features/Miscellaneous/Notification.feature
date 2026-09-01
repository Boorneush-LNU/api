@Notifications
Feature: User Notification and Notification setting

#  Queries [SSG]
#  Shankar to review the tags below:
# -  @K13Regression  ==> Shan { Updated to @TargetedMiniRegression }
# -  @K13RegPreprod  ==> Shan { Updated to @TargetedMiniRegression }

  Background:
      Given Launch the Ireland State Savings Online Homepage
      Then Click sign in button on home page

  @Notification @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  @TargetedMiniRegression
  Scenario Outline: Notification Settings Change (Alerts enabled) <TCName>
    Then login to Ireland State Savings with "<data>" as data reference
    And click on the Notification menu and navigates to the Notification Page
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
    Then User disabled the alerts and able to view the Confirm change page
    And Clicks on the Confirm and is able to view the alert message

    Examples:
      | TCName | data|
      | TC66 |Generic User|


  @Notification_Message @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
    @TargetedMiniRegression
   Scenario Outline: Notification Settings Change (Messages enabled) <TCName>
    Then login to Ireland State Savings with "<data>" as data reference
    And click on the Notification menu and navigates to the Notification Page
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
    Then User clicks message toggle button and able to view the Confirm change modal
    Then Clicks on the Confirm and is able to view the alert message

    Examples:
      | TCName | data|
      | TC65 |Generic User|

    @Notification_Alert   @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
    @TargetedMiniRegression
    Scenario Outline: Notification Settings Change (Both Alerts and Messages enabled) <TCName>
    Then login to Ireland State Savings with "<data>" as data reference
    And click on the Notification menu and navigates to the Notification Page
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
    Then User disabled the alerts and able to view the Confirm change page
    And Clicks on the Confirm and is able to view the alert message
    Then User clicks message toggle button and able to view the Confirm change modal
    And Clicks on the Confirm and is able to view the alert message

    Examples:
      | TCName | data|
      | TC67 |Generic User|


    @NotificationCount  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
    Scenario: Validate notification bubble count
      Then login to Ireland State Savings with "TC68" as data reference
      Then validate notification bubble count

  @Notification_Message @MessageDisabled   @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Notification Settings Change (Messages disabled) <TCName>
    Then login to Ireland State Savings with "<data>" as data reference
    And click on the Notification menu and navigates to the Notification Page check the count
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
    Then User clicks message toggle button when it is enabled
  Then click Your Saving Module on dashboard page
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then User redirected to thank you page
    And click on the Notification menu and navigates to the Notification Page check the count after Reinvest
  Then Check the Notification count in Message section Before Reinvest and After Reinvest


    Examples:
      | TCName | data|
      | TC70 | TC70_MessageDisabled|


#    Update the password in the Excel Each time. UATAUTO-WF-User40@mailinator.com/ It is in initial.uat.passwordNoti
  @Notification_Alert @AlertDisabled  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Notification Settings Change (Alerts Disabled) <TCName>
    Then login to Ireland State Savings with "<data>" as data reference OTP
    And click on the Notification menu and navigates to the Notification Page check the count for Alerts
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
  Then User clicks alert toggle button when it is enabled
    Then click profile & settings on dashboard page
    And click on change button in the "Your Password" section
    Then validate "Your Password-change your password" slider content
    And enter old and new password Notification
    Then validate all password requirements are fulfilled
    When confirm button is enabled
    Then click on confirm button on "Your Password Notification" slider
    And validate "Your Password-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then validate the success banner message is displayed for: "Change Password"
    And click on the Notification menu and navigates to the Notification Page check the count after Change Password
    Then Check the Notification count in Alerts section Before Change Password and After Change Password

    Examples:
      | TCName | data|
      | TC71 | TC71_AlertDisabled|









