@DigitalRegistration
Feature: Digital Registration E2E flow

#    Queries: [SSG}
#    1. Shankar to confirm if we can add this test case to the Post Login Regression pack for both UAT and PREPROD Regression ==> Shan { We can add thie in UAT/PP. There is a data for UAT, need to set up a data for PP alone }

    @DigitalRegistration @Regression @Post_Login_Regression @UAT_Regression @UAT_Regression_NonRepayReinvest
#        @UAT_Regression @PREPROD_Regression
  Scenario: Digital Registration E2E Happy flow
      Given user launch the State Savings portal and click on Register
      When click on Begin Registration and verify let's get started page
      Then user enter the details as per "DigReg_1"
      And Validate the PPSN_SSCN tooltip
      And user click on next button of lets get stared page
      Then User validates content in Terms of service page
      Then Validate Checkbox content & Click on all the checkbox
      Then click on next button on Terms of service Page
      Then Validate contents in Checklist Page
      Then click on next button on Checklist Page
      And User reaches Contact details page & enter the details
      Then click on Consent Checkbox & next button on Contact Details Page
      And Validate the entered mail & mobileNumber in checkDetails Page & clicks next
      Then User lands on Confirm Mobile Number Page & click on Send code
      And Enter the security code for Digital Registration
      And verify check your inbox page for DigitalReg
      And open Email and click on email verification link
      And verify Email address Verified page
      And Enter password for web Registration
      And Click on Confirm button of Email address Verified page
      Then Scan QR code displays & click on Scan QR code
