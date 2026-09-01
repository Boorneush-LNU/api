@RepayReinvest
Feature: Repay/Reinvest into State Savings Product

#  Queries [SSG]
#  1. Shankar to review the tags which is line 2 of each of the scenarios below and confirm if they are superceded by UAT_Regression now or do we need to keep them still ?
#  Eg. @K13Regression ==> Shan -> { Updated to @TargetedMiniRegression }
#      @SITE2E  ==> Shan { This Tag is not under UAT_Regression. Will remove that Tag }
#      @K13RegPreprod ==> Shan -> { Updated to @TargetedMiniRegression }

  Background:
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page

  @Reinvest_MultipleProduct_Cashin_Journey  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: Reinvest into multiple product & Cash In remaining enter amount journey for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    Then User enter rest amount for CashIn in Cashin Textbox
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page

    Examples:
      | TCNO                          | MethodType |
      | TC310_AllProduct | Reinvest-Cash In  |

    @RepayReinvestSingle  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
    @TargetedMiniRegression
  Scenario Outline: Reinvest and CashIn Fixed Term Product for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
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

    Examples:
      | data  |
      | SingleTC56 |


  @RepayReinvestMultiple  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: Reinvest and CashIn Fixed Term Product for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
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
    Then click on notifications from submenu
    Then click your savings from submenu
    When User select State Savings Product and clicks on manage button
    And User click on Pending Transaction modal

    Examples:
      | data          |
      | TC57_Multiple |


    @Installment&Childcare
  Scenario Outline: Reinvest and CashIn InstallmentSave and Childcare product for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User clicks on manage button and select monthly instalment savings
    And User opens matured holding ID for Instalment Save and select method type
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    Examples:
      | data  |
      | TC_67 |


# Remove Iban
  @RepayReinvestAddIBAN   @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: Reinvest and CashIn with add IBAN Journey for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then User Enters IBAN number and click on confirm bank details
    Then User then enters otp and press confirm
    Then User click investment type on choose option page
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page

    Examples:
      | data |
      | TC42 |

  @Allocatetocash   @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  @TargetedMiniRegression
  Scenario Outline: Reinvest and CashIn allocate to cash for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User choose product from dropdown and enter the amount
    And User clicks on confirm button
    Then validate "You must allocate full amount (allocate to cash)" modal content
    When Amount entered is less than maturity value user click on allocate to cash
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When User select State Savings Product and clicks on manage button
    And User click on Pending Transaction modal

  Examples:
    | data                |
    | TC58_AllocateToCash |


  @CancelTransaction  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest and CashIn cancel journey for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    Then User click cancel button on review page

    Examples:
      | data               |
      | TC317 |

  @RepayReinvestAllocateAll @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
@TargetedMiniRegression
  Scenario Outline: Reinvest and CashIn allocate full amount for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User choose product from dropdown and enter the amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When User select State Savings Product and clicks on manage button
    And User click on Pending Transaction modal

  Examples:
    | data                    |
    | TC59 FullAmount |

  @HoldingsContentCheck
  Scenario Outline: Reinvest and Cashin Content Validation for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding page selects method type
    Examples:
      | data               |
      | RepayReinvest User |

  @HoldingsContentCheckInstalment&Childcare
  Scenario Outline: Reinvest and Cashin Content Validation Instalment&Childcare for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User clicks on manage button and select monthly instalment savings
    And User opens matured holding page selects method type
    Examples:
      | data  |
      | TC_67 |


  @JointContentCheck   @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Joint Content Validation for matured and non matured holdings for "<data>" for Repay Reinvest
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User select StateSavingProduct and clicks on manage button
    Then User clicks on joint button
    And User selects joint holding with mentioned Name
    Then User verify notice button with mentioned HoldingId
    Then User clicks on button and verifies the modal content
    When User click on more details and verifies button present is same as on holdings page

    Examples:
      | data  |
      | TC329 |
      | TC329_1 |


  @ErrorValidationOTP  @ErrorScenarios
  Scenario Outline: Reinvest and CashIn error validation on otp page for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate error message on OTP page with multiple combination
    Examples:
      | data               |
      | RepayReinvest User |

    @ErrorValidationDetailsPage  @ErrorScenarios  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest and Cash In error validation on details page for "<scenario>"
    Then login to Ireland State Savings with "RepayReinvest User" as data reference
    When User select State Savings Product and clicks on manage button
      Then User click "Reinvest Cash In" link on the summary page
      Then User click investment type on choose option page
    Then Validate error message on details page when "<scenario>"

  Examples:
    | scenario  |
      |TC38|
      |TC39|
      |TC40|
      |TC41|
      |TC42|
      |TC43|
    |TC44|
    |TC45|


# Shankar to review and confirm the failures and discuss with SSG  --> Tried to run this scenario, it is clicking on PB & trying to find the Holding Id & fails. This is a Invalid Scenario. We can remove this
# Taken from regression feature file  -Not used for test execution now
  @CashInChooseAmnt
  Scenario Outline: Cash In PB choose amount <Testcase Name>
    Given login to Ireland State Savings with "<Scenario>" as data reference
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User choose product from dropdown and enter the amount
#    When user clicks View and Manage button to access summary
#    And Click on the reinvest and cashin link
#    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Validate the details on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    Examples:
      | Scenario | MethodType | Testcase Name |
      | TC_03    | Cash-In    | TC 46         |


  @Reinvest_CashIn_POSB_Existing_Account @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: Reinvest/Cash In From Existing POSB Account <Testcase Name>
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects other product from the dropdown list
    And User clicks on Existing account checkbox & enter the Account Number
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Tick in the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal

    Examples:
      | TCNO                          | MethodType |
      | TC306_Posb | Reinvest-Cash In  |


  @Reinvest_MultipleProduct_Confirm_Journey  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest into multiple product & enter more amount then click confirm journey for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button
    Then validate "Available amount exceeded" modal content
    Then User validate the modal & clicks on Close button
    Examples:
      | TCNO                          | MethodType |
      | TC319_NSBReinvestMultipleLessAmount | Reinvest-Cash In  |


  @Reinvest_Cashin_ErrorMessage_Modal @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest/Cash Error Messages & Modal <Testcase Name>
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects one product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button
    Then Validate error message on details page when "<scenario>"
    Then Enter the Amount in the Reinvest Textbox
    And User clicks on confirm button
    Then validate "Available amount exceeded" modal content
    Then User validate the modal & clicks on Close button
    Then user clear the Reinvest Amount
    Then User enter amount in CashIn Section
    And User clicks on confirm button
    And validate "You must allocate full amount (allocate to cash)" modal content
    Examples:
      | TCNO                      | MethodType | scenario|
      | TC318_ReinvestCashInError | Reinvest-Cash In  |FT-no amount entered|


  @MaturedPartialCashInCancel   @Regression @UAT_Regression @Post_Login_Regression
  Scenario: Cancel from detail page repay and reinvest for "TC317"
    Then login to Ireland State Savings with "TC317" as data reference
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then User click cancel button on review page
    And verify user lands on dashboard page


  @MaturedPartialVerifyCashInCancel  @Regression @UAT_Regression @Post_Login_Regression
  Scenario: Cancel from verification page repay and reinvest for "TC317"
    Then login to Ireland State Savings with "TC317" as data reference
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    Then User click cancel button on review page
    And verify user lands on dashboard page