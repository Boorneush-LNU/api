@Title_validation
# Queries [SSG]:
#  Is this feature scenarios run for both UAT and Pre PROD regression ? ==> Shan -> { Yes. For UAT we can run the complete Feature file. For Preprod we must comment ECR journey & change the Newcomms url too }
#  To be reviewed after the current issue of repay/reinvest transaction cannot be uplfited from Workflow is resolved. ==> Shan -> { Need to run this once & confirm }

Feature: Title Validation

  Background:

    Given Launch the Ireland State Savings Online Homepage

    Then Click sign in button on home page

  @TitleValidation

  Scenario Outline: TitleValidation

#    Repay Reinvest

#  Given User login with valid credential "TC04"
#    When user clicks on View and Manage button to access summary
#    Then Validate Title "Standard Account Summary | Ireland State Savings"
#    Then User click "Reinvest Cash In" link on the summary page
#    Then Validate Title "Choose Option | Ireland State Savings"
#    And User choose "<option>"
#    And user selects product from the dropdown list and proceeds to enter the desired amount

#    Then Validate Title "Detail | Ireland State Savings"

#    And User clicks confirm button to finalize product and amount for reinvestment

#    Then Validate Title "Detail | Ireland State Savings"

#    And Validate the details on the review page

#    And Click confirm button on review page and finalize the transaction

#    And User enter valid otp on the security code page

#    Then Validate Title "Security | Ireland State Savings"

#    And Confirm transaction by clicking confirm button on verification code page

#    Then Validate Title "Security | Ireland State Savings"

#    Then validate Thank You Page is displayed

#    Then Validate Title "Your Savings | Ireland State Savings"

#    Then click on notifications from submenu

#    Then Validate Title "Notifications | Ireland State Savings"

#    Change Mail

    Then login to Ireland State Savings with "<data>" as data reference

    Then click profile & settings on dashboard page

    Then Validate Title "Profile and Settings | Ireland State Savings"

    And click on change button in the "Your Email Address" section

    Then validate "Your Email Address-Change Email address" slider content

    Then enter new email address in the field

    And click on confirm button on "Your Email Address" slider

    Then validate "Your Email Address-Verification code" slider content

    Then enter the verification code

    And click on confirm button on verification code slider

    Then validate "Your Email Address-Check Inbox" slider content

    Then open mailinator portal with new email

    Then click "Confirm change of email address" button in mail message body

    And validate email address verified message is displayed MetaData

    Then Validate Title "Email address verified | Ireland State Savings"

    Then Click sign in button on home page

    Then Validate error message on sign in page for "<InvalidScenario>"

    Then Validate Title "Sign in | Ireland State Savings"


#     New Comms

    And Click sign in button on home page

    Given User enters valid credentials "<Scenario>"

    Then Validate Title "Sign in | Ireland State Savings"

    And open link and validate Title "NewComms-Repay Reinvest"
    Then Validate Title "Reinvest & Cash In | Comms | Ireland State Savings"

    And open link and validate Title "NewComms-PrizeBond-WinningPaymentOption"
    Then Validate Title "Prize Bond Payment Options | Comms | Ireland State Savings"

#    And open link and validate Title "https://statesavingsalt-qa.dev-anpost.com/news-comm/state-savings-online" // Hold this now ( Need to create a new feature file )
#    Then Validate Title "Registering for State Savings Online | Comms | Ireland State Savings"

    And open link and validate Title "NewComms-Celebrate100Years"
    Then Validate Title "100 Years of Savings Certificates | Comms | Ireland State Savings"

    And open link and validate Title "NewComms-SSCN"
    Then Validate Title "State Savings Customer Number | SSCN | Ireland State Savings"


#    Buynow

    Then Launch the Ireland State Savings Online Homepage

    Given Click buy now for "TC3"

    Then User select product for purchase

    Then User select journey as guest

    Then Validate Title "Personal Details | Checkout | Ireland State Savings"

    Then User enter personal details on your details page for first applicant

    Then Validate Your Order page

    Then Validate Title "Your Order | Checkout | Ireland State Savings"

    Then Validate product and amount on review page

    Then Validate Title "Review | Checkout | Ireland State Savings"

    Then User selects customer level critical data elements

    Then Enter payment details click pay

    Then Verify Thank You page

    Then Validate Title "Result | Checkout | Ireland State Savings"

    Then User click on Backtostatesavings

# Digi Regs Check

    When click on Register

    When click on Begin Registration and verify let's get started page

    Then user enter the details for Title "<Scenario>"

    And  user click on next button of lets get stared page

    Then Validate Title "Terms of Service | Ireland State Savings"

    And User click on Backtostatesavings

#    For Invalid Regs Details & Forgot password

    When click on Register

    When click on Begin Registration and verify let's get started page

    Then user enter invalid details for Title

    Then Validate Title "Enter your Personal Details | Ireland State Savings"

    And  user click on next button of lets get stared page

    Then Validate Title "Details not found | Ireland State Savings"

    Given Launch the Ireland State Savings Online Homepage

    Then Click sign in button on home page

    Then click the Forgot Password button to initiate the password reset process

    Then enter valid email address and click Next

    Then enter the verification code

    And click on confirm button on verification code slider

    Then open mailinator portal with new email

    Then click the change password button present in the email body

    And  enter new password

    Then Validate Title "Reset password | Ireland State Savings"

    And Click next button

    Then a password reset successfully message is displayed

# ECR

    Then Launch the Ireland State Savings Online Homepage

    Then click on Register

    When click on Begin Registration and verify let's get started page

    Then Validate Title "Enter your Personal Details | Ireland State Savings"

    Then user enter the details for ECR "<Scenario>"

    And  user click on next button of lets get stared page

    And  Click on I have my PIN button

    And  verify Enter your Email address page

    Then Validate Title "Enter your email address | Ireland State Savings"

    Then Enter and Re enter email for Title

    And check the consent on Email page

    And  Click on next button of email page

    Then enter your PIN "<pin>"

    Then Validate Title "Enter your PIN | Ireland State Savings"

    Then Click on continue button of PIN page

    Then Validate Title "Enter your PIN | Ireland State Savings"

    And open Email for ECR

    Then Validate Title "Enter your mobile number | Ireland State Savings"

    And Enter all the details for mobile number

    Then Click on continue button of mobile page

    Then Validate Title "Enter Verification Code | Ireland State Savings"

    And  Enter the security code for ecr

    Then Click on verify button of security code

    And validate the create password page

    Then Validate Title "Create your password | Ireland State Savings"

    And Enter the password for Title

    And click on Show password button

    And Click on complete button of password page

    And Validate the congratulations message

    Then Validate Title "Sign in | Ireland State Savings"

    Examples:

      | Scenario | pin    | option   | Number      | Email                        | data        | InvalidScenario |
      | TC04     | 438875 | Reinvest | 020-8010124 | UAT-WF-User82@mailinator.com | ChangeEmail | TC2             |









