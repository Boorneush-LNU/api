@Cash_in_non_matured
Feature: Cash_In Non-Matured Journeys

  #      Queries [SSG]:
#  1. Shankar to review the tags below to confirm if they could be removed ?
#  - @K13Regression ==> Shan -> { Updated to @TargetedMiniRegression }
#  - @K13RegPreprod  ==> Shan -> { Updated to @TargetedMiniRegression }
#  - @Negative (what is difference between this tag and ErrorScenarios tag) ==> Shan -> { There is no diff. Will remove these tag as it is used for my Ref }
#  - @UATRun ==> Shan -> { Will remove these tag as it is used for my Ref }
#  2. I could see some scenarios are marked as K13Regression or UATRun here but not included in the UAT_Regression or PREPROD_Regression tags -
#  are those rightly mapped ? or did we had some scenarios which were run earlier as part of regression but are now not executed ?
#   ==> Shan -> UATRun tag is a unwanted tag used for my ref. & [Non-Matured: Enter amount Wait for interest not enable] this scenario comes under UAT_Regression & PREPROD_Regression
#               where as the scenario : [Non-Matured: Enter amount Wait for interest enable] comes under K13Regression (which was used to run previous Regression suite).


  Background:
      Given Launch the Ireland State Savings Online Homepage
        Then Click sign in button on home page

    @Non_Matured_Error_On_Enter_Amount_Page @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Non - Matured: Error on enter amount page for FT product for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
  Then Verify the error message displayed when amount entered is "<Amount>"

    Examples:
      | Scenario | Amount   |
      | TC251    | Repayment-MoreThan |
      | TC252     | Repayment-Blank|


  @Non_Matured_Error_On_Enter_OTP_Page  @ErrorScenarios
  Scenario: Non - Matured: Error on OTP page for FT product for "TC_04"
    Then login to Ireland State Savings with "TC_04" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    Then Validate error message on OTP page with multiple combination


  @Non_Matured_Repay_Reinvest_Link_For_New_Product
  Scenario Outline: Repay-Reinvest link for product for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    Examples:
      | Scenario |
      | TC_04    |


  @Non_Matured_Cancel_Journey @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Cancel Journey for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    And User click on allocate full amount
    And validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    Then User click cancel button on review page

    Examples:
      | Scenario  |
      | TC255 |


    @Non_Matured_Add_IBAN_IN_Journey @Regression @UAT_Regression @Post_Login_Regression  @PREPROD_Regression
    Scenario Outline: Add iban while cash-in for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
      Then User click add bank details on details page
    Then validate "Your Bank Details-Add IBAN" slider content
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    And Verify the thank you message displayed
      Then User enters Amount for Cashin
      And User clicks on the confirm button
      And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal

      Examples:
        | Scenario |
        | TC40    |



  @Non_Matured_CashIn_Installment_Enter_Amount
  Scenario Outline: Non Matured: Childcare,SSA,Installment Enter amount journey wait for interest not enable for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User click on the manage button and select the parent Account
    Then User click "Cash In" link on the summary page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_10    |


  @Non_Matured_CashIn_Installment_Enter_Amount
  Scenario Outline: Non Matured: Childcare,SSA,Installment Enter amount journey Wait for interest enable for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click on the delay checkbox
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_04_SSA    |


  @Non_Matured_CashIn_Installment_Allocate_Modal
  Scenario Outline: Non - Matured: Childcare,SSA,Installment Allocate amount journey wait for interest not enable for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User click on the manage button and select the parent Account
    Then User click "Cash In" link on the summary page
    And User click on allocate full amount
    And validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_77    |



  @Non_Matured_CashIn_Installment_Allocate_Modal
  Scenario Outline: Non - Matured: Childcare,SSA,Installment Allocate amount journey wait for interest enable for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User click on the manage button and select the parent Account
    Then User click "Cash In" link on the summary page
    And User click on allocate full amount
    And validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_23    |

  @Non_Matured_CashIn_FT_Allocate_Modal
  Scenario Outline: Non-Matured: Allocate Modal Wait for interest not enable for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    And User click on allocate full amount
    And validate "Allocate full amount" modal content
    And User click on Close button in Insufficient Model
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario           |
      | TC_05AllocateModal |


  @Non_Matured_CashIn_FT_Allocate_Modal
  Scenario Outline: Non-Matured: Allocate Modal Wait for interest enable for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click on the delay checkbox
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC56     |

  @Non_Matured_CashIn_FT_Enter_Amount  @Regression @UAT_Regression @Post_Login_Regression  @PREPROD_Regression
  @TargetedMiniRegression
  Scenario Outline: Non-Matured: Enter amount Wait for interest not enable for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
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

    Examples:
      | Scenario |
      | TC43    |


  @Non_Matured_CashIn_FT_Enter_Amount_Above_same
  @TargetedMiniRegression
  Scenario Outline: Non-Matured: Enter amount Wait for interest enable for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click on the delay checkbox
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_20    |


  @Repayment_Edit_Amount_&_Order @Regression @UAT_Regression @Post_Login_Regression  @PREPROD_Regression
  Scenario Outline: Non matured Cash In edit & change Amount  for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And User click on edit your order review page
    Then User Reenters Amount for Cashin
    And User clicks on the confirm button
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed

    Examples:
      | TCNO             | MethodType |
      | TC253_EditCashIn | Reinvest   |


  @Non_Matured_CashIn_Insufficient_Amount
  Scenario Outline: Non-Matured: Enter Exceed amount & click on Confirm for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And validate "Insufficient Fund" modal content
    Then User click on Close button in Insufficient Model
    Examples:
      | TCNO             | MethodType |
      | TC318_InsufficientFund | Cash In   |


  @Non_Matured_CashIn_DidNot_Get_VerificationCode  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Non-Matured: Verify Didn't get Verification code slider for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then User clicks on Didn't Receive Verification Code link
    And validate "Didn't get your Verification Code?" slider content
    Examples:
      | TCNO             | MethodType |
      | TC74_Slider | Cash In   |


  @NonMaturedDetailCashInCancel @Regression @UAT_Regression @Post_Login_Regression
  Scenario: Cancel from detail page for "TC_20"
    Then login to Ireland State Savings with "TC_20" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    Then User enters Amount for Cashin
    Then User click cancel button on review page
    And verify user lands on dashboard page

  @NonMaturedVerifyCashInCancel @Regression @UAT_Regression @Post_Login_Regression
  Scenario: Cancel from verification page for "TC_20"
    Then login to Ireland State Savings with "TC_20" as data reference
    When User selects the product and clicks on manage button
    Then User click "Cash In" link on the summary page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click on the delay checkbox
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    Then User click cancel button on review page
    And verify user lands on dashboard page




