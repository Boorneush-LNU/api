#Queries [SSG]:
#1. Shankar to review and share suggestions with SSG on Update the naming convention based on standard ==> Shan -> { Changed the Scenario name for TC5 & TC6. Remaining naming looks Good }
#2. Shankar to review and update TC25 - TX Handler Endpoint ==> Shan -> { As of now, PP TrustX endpoint is working, for UAT it is throwing error as "Connection timed out" & same for Ping endpoint.}
#3. Shankar to review and update TC26 - SSO Web Registration Health Step definition to refer the header of personal details page ==> Shan -> { Added in the Feature file & refers Personal Details Page  }
#4. Shankar to review and update TC3 and TC21 - Hard coded data to be removed from feature file and use Excel Sheet data reference instead by adding a new line item for this sanity suite test case. ==> Shan -> { TC3 data is been coming from Excel, need to give data for TC21 alone }

#@NewSanity_Suite_K13_UAT
  @CMS_Sanity_Suite
Feature: State Savings Sanity Suite

#     SSO Landing Page
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD @dhd
  Scenario: TC1_State Savings Online landing page
    Given Launch the Ireland State Savings Online Homepage

#     SSO Sign In Page
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC2_Ensure the Sign In page loads
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Then Validate the proper rendering and placement of the email and password input fields

#     SSO Login and Dashboard View
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC3_Validate user login and dashboard view
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Then login to Ireland State Savings with "SanityLogin" as data reference

#     Forgot Password Journey
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC4_Forgot password
    Given Launch the Ireland State Savings Online Homepage
  Then Click sign in button on home page
    Then click the Forgot Password button to initiate the password reset process
    Then validate "Forgot Password:Reset Password" slider content
    Then enter valid email address and click Next
    Then Validate Verification code Slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then validate "Forgot Password:Check Email" slider content
    Then open mailinator portal with new email
    Then click the change password button present in the email body
    And  enter new password, click next to confirm input
    Then a password reset successfully message is displayed

#    Add Iban from Dashboard.  Remove : 242599559
#     @CMS_Sanity_Suite_UAT
#  Scenario: TC5_E2E journey for add iban
#    Given Launch the Ireland State Savings Online Homepage
#    Then Click sign in button on home page
#    Given login to Ireland State Savings with "AddIBAN" as data reference
#    And click on the Notification menu and navigates to the Notification Page
#    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
#    Then User clicks message toggle button and able to view the Confirm change modal
#    Then Clicks on the Confirm and is able to view the alert message
#    Then User click on your Savings Tab
#    When Verify "Add IBAN" prompt message on dashboard
#    Then click on add your bank details now link
#    Then validate "Your Bank Details-Add IBAN" slider content
#    And User enter the iban
#    And Tick the checkbox on the iban page
#    Then User click on the verify bank button
#    Then validate "Your Bank Details-Verification code" slider content
#    Then enter the verification code
#    And click on confirm button on verification code slider
#    Then validate the success banner message is displayed for: "Add IBAN"
#    And Verify iban is displayed in your bank detail
#    Then click on notifications from submenu
#    Then validate "IBAN" notification is displayed

#    Change Iban
     @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC6_E2E journey for Change iban
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Given login to Ireland State Savings with "AddIBAN(P&S)" as data reference
    And click on the Notification menu and navigates to the Notification Page
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
    Then User clicks message toggle button and able to view the Confirm change modal
    Then Clicks on the Confirm and is able to view the alert message
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


# Repay Reinvest
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC7_Reinvest into single/multiple product enter amount journey
    Given Launch the Ireland State Savings Online Homepage
  Then Click sign in button on home page
    Then login to Ireland State Savings with "TC_31" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then User redirected to thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Given Launch Workflow
    And Click administration forms select process: "Repayments Forms" and action: "Search"
    Then Validate the case details with the current date "TC_31"


#  Repay Reinvest
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC8_Reinvest and CashIn Fixed Term Product
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Then login to Ireland State Savings with "TC_02" as data reference
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When User select State Savings Product and clicks on manage button
    And User click on Pending Transaction modal
    Given Launch Workflow
    And Click administration forms select process: "Repayments Forms" and action: "Search"
    Then Validate the case details with the current date "TC_02"


#  Cashin
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC9_Non-Matured: Enter amount Wait for interest not enable
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Then login to Ireland State Savings with "TC_04" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Given Launch Workflow
    And Click administration forms select process: "Repayments Forms" and action: "Search"
    Then Validate the case details with the current date "TC_04"


#   Repay Reinvest
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC10_Matured Cash In for FT product by enter amount
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Then login to Ireland State Savings with "TC_07" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Given Launch Workflow
    And Click administration forms select process: "Repayments Forms" and action: "Search"
    Then Validate the case details with the current date "TC_07"

    #   PB Cash In - Amount
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC11_PB cash-in Enter amount
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Then login to Ireland State Savings with "TC_03" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Validate the details on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page

# PB Cash In -PB Range
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC12_PB cash-in prize bond range
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Then login to Ireland State Savings with "PB_Range_2" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on choose bond range window
    And Verify the choose bond range window
    Then Select from bond range: "SanitySuite-BondRange"
    And User click on the confirm button
   And Validate the details on review transaction page prize amount for Sanity Suite-BondRange
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page


#  Bond range amount must be 50 or above 50...


#PB Reinvest - Amount
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC13_Reinvest_Enter_Amount PB into single/multiple product
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Then login to Ireland State Savings with "TC_01" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then validate "PB :You must allocate full amount(allocate to cash)" modal content
    And User clicks on allocate to cash modal
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And User redirected to thank you page

#PB Reinvest  - PB Bond Range
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD @Shankl
  Scenario: TC14_PB Reinvest-in prize bond range
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Then login to Ireland State Savings with "TC_21_PB" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on choose bond range window
    And Verify the choose bond range window
    And Select from bond range: "SanitySuite-BondRange"
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button

#    Purchase Journeys - Sole
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC15_Guest sole purchase journey - CDE Y
    Given Launch the Ireland State Savings Online Homepage
    Given Click buy now for "TC2"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
   Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

#    Purchase Journeys - Sole
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC16_Sign in sole purchase journey - CDE Y
    Given Launch the Ireland State Savings Online Homepage
    Then Click buy now for "TC23"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

#    Purchase Journeys - Sole
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC17_Sign in sole purchase journey - CDE N
    Given Launch the Ireland State Savings Online Homepage
    Then Click buy now for "TC34"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

#    Purchase Journeys - Joint
  Scenario: TC18_Sign in joint purchase journey - CDE NN
    Given Launch the Ireland State Savings Online Homepage
    Then Click buy now for "TC54"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

#    Expedited Registration Journey
#     @CMS_Sanity_Suite_UAT
#  Scenario: TC19_Expedited Registration
#    Given user launch the State Savings portal and click on Register
#    When click on Begin Registration and verify let's get started page
#    Then user enter the details as per "TC04"
#    And Validate the PPSN_SSCN tooltip
#    And  user click on next button of lets get stared page
#    And  Validate the Continue Registration modal
#    And  Click on I have my PIN button
#    And  verify Enter your Email address page
#    Then Enter and Re enter email
#    And check the consent on Email page
#    And  Click on next button of email page
#    Then enter your PIN "248692"
#    Then Click on continue button of PIN page
#    And verify check your inbox page
#    And open Email and click on email verification link
#    And Enter all the details for mobile number page
#    Then Click on continue button of mobile page
#    And  Enter the security code
#    Then Click on verify button of security code
#    And validate the create password page
#    And Enter the password
#    And click on Show password button
#    And Click on complete button of password page
#    And Validate the congratulations message
#    And First time login using ECR User

#    Workflow Health
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario:TC20_Ensure workflow application launched successfully
    Given Launch Workflow

#    Workflow Health
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC21_Validate workflow case details page displayed
    Given Launch Workflow
    And Click administration forms select process: "Repayments Forms" and action: "Search"
    Then Validate case details page is displayed for this holding id: "Workflow"

#    Workflow Health
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC22_Validate workflow Case search page displayed
    Given Launch Workflow
    And Click administration forms select process: "Search" and action: "Case Search"
    Then Validate purchase page

#    PB Admin Portal Health
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC23_Ensure admin portal is launched successfully
    Given Launch the PB Admin portal and login with valid credentials as "PB Admin User"

#    My Account PING endpoint
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC24_Ensure PingEndpoint portal is launched successfully
    Given Launch the PingEndPoint and validate


#    TrustXHandler Health endpoint
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC25_Ensure TrustXHandler portal is launched successfully
   Given Launch the TrustXHandler and validate


  #    SSO Website Registration portal Health
  @CMS_Sanity_Suite_UAT  @CMS_Sanity_Suite_PREPROD
  Scenario: TC26_Validate the Registration portal is launched successfully
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page

