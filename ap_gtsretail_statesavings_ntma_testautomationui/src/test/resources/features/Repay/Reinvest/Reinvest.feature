@ReinvestJourney
Feature: User Reinvest in State Savings portal

#Queries [SSG]:
#1. Shankar to review the tags below to confirm if they are required or if we could remove them ?
#  @Build_Sanity_Add_IBAN  - what is this tag used for ??  ==> Shan { It is just a tag name & we are not using this Tag. Will remove this tag }
#  @K13Regression ==> Shan -> { Updated to @TargetedMiniRegression }
#  @K13RegPreprod ==> Shan -> { Updated to @TargetedMiniRegression }

  Background:
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page

    @Reinvest_FT_To_PB_Allocate_Full_Amount_IBAN_Not_Added  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: Reinvest into PB Allocate full amount button journey_IBAN not added for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount (reinvest into PB No IBAN) modal" modal content
    And User click on PB allocate modal Add IBAN button
    Then validate "Your Bank Details-Add IBAN" slider content
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then User redirected to thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal

    Examples:
      | TCNO  |
      | TC49 |

  @Reinvest_Amount_Error  @ErrorScenarios  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Checking Enter Amount Errors for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then validate "<modal name>" modal content

    Examples:
      | Scenario                   | modal name                 |
      | TC288| You must allocate full amount(less amount entered) |
      | TC289 | Available amount exceeded   |


  @Reinvest_FT_To_Any_Product_DoNotAcceptT&C  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest into any product Allocate full amount & Don't accept T&C button journey for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Click confirm button on review page to finalize the transaction
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then Validate error message on details page when "<scenario>"
    Examples:
      | TCNO          | scenario|
      | TC37_ReviewCheckbox | TickContent|


  @Error_At_Otp_Page  @ErrorScenarios
  Scenario: Error at otp page for "TC_38"
    Then login to Ireland State Savings with "TC_38" as data reference
    When User click on the manage button and select the parent Account
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate error message on OTP page with multiple combination


  @Reinvest_Less_Amount_Entered_Modal  @Regression @UAT_Regression @Post_Login_Regression
  Scenario: Less amount entered modal verify for "TC291"
    Then login to Ireland State Savings with "TC291" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then validate "You must allocate full amount(less amount entered)" modal content



  @Reinvest_More_Amount_Entered_Modal  @Regression @UAT_Regression @Post_Login_Regression
  Scenario: More amount entered modal verify for "TC325(More Amount)"
    Then login to Ireland State Savings with "TC325(More Amount)" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then validate "Available amount exceeded" modal content


# Shankar to review and update the test case number for this scenario - This Scenario is not there in Master Reg. Sheet.
  @Reinvest_Cancel_Journey
  Scenario: Reinvest Cancel Journey
    Then login to Ireland State Savings with "Reinvest User" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    Then User click cancel button on review page


  @Reinvest_Installment_To_Product
  Scenario Outline: Reinvest From Installment to any Product Enter Amount Journey for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User click on the manage button and select the parent Account
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then User redirected to thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_38    |
#      | TC_37    | Reinvest   |
#      | TC_55    | Reinvest   |
#      | TC_56    | Reinvest   |

  @Reinvest_Installment_To_Product_Allocate_Button
  Scenario Outline: Reinvest From Installment to any Product Allocate Amount Journey for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User click on the manage button and select the parent Account
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And User clicks on confirm button to finalize product and amount for reinvestment
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_40    |


  @Reinvest_FT_To_FT_Enter_Journey_Multiple @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  @TargetedMiniRegression
  Scenario Outline: Reinvest into single/multiple product enter amount journey for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
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

    Examples:
      | TCNO  |
      |TC50_ReinvestMultiple|


  @Reinvest_FT_To_Any_Product_Allocate_Journey  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  @TargetedMiniRegression
  Scenario Outline: Reinvest FT into any product Allocate full amount button journey for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then User redirected to thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal

    Examples:
      | TCNO          |
      | TC52 |


  @Reinvest_FT_To_PB_Allocate_Full_Amount_IBAN_Already_Added
  Scenario: Reinvest into PB Allocate full amount button IBAN added journey for "TC_31AllocateToPBIBANAdded"
    Then login to Ireland State Savings with "TC_31AllocateToPBIBANAdded" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount (reinvest into PB modal)" modal content
    And User click on allocate modal confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then User redirected to thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal


  @errorReinvestValidationDetailsPage  @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest and Cash In error validation on details page for <TCName>
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then Validate error message on details page when "<scenario>"

    Examples:
      | TCName | data| scenario|
      | TC29 |Reinvest User|no product selected   |
      | TC30 |Reinvest User|FT-no amount entered  |
      | TC31 |Reinvest User|FT-below minimum purchase  |
      | TC32 |Reinvest User|DA-account number and amount error   |
      | TC33 |Reinvest User|DA-account number error   |
      | TC34 |Reinvest User|DA-below minimum purchase   |
      | TC35 |Reinvest User|PB-no amount entered   |
      | TC35 |Reinvest User|PB-below minimum purchase|


  @Reinvest_Edit_Product_&_CashIn_Journey  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: Reinvest into any product Allocate full amount button journey for <TCNO>
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects one product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And User click on edit your order review page
    And user selects other product from the dropdown list
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then User redirected to thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal

    Examples:
      | TCNO          |
      | TC290_EditProdAndAmount |


  @Reinvest_MultipleProduct_LessAmount_ConfirmJourney @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest into multiple product & allocate less amount journey for <TCNO>
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then validate "You must allocate full amount(less amount entered)" modal content
    And User validate the modal & clicks on Go Back button

    Examples:
      | TCNO          |
      | TC292_Reinvest |


    @Reinvest_NSB_More_Amount_Journey  @Regression @UAT_Regression @Post_Login_Regression
    Scenario Outline: Reinvest into NSB product & Reinvest more amount journey for <data>
  Then login to Ireland State Savings with "<data>" as data reference
  When Validate IBAN status is displayed
  When User select State Savings Product and clicks on manage button
  Then User click "Reinvest Cash In" link on the summary page
  Then User click investment type on choose option page
  And User choose product from dropdown and enter the amount
  And User clicks on confirm button
      Then validate "Available amount exceeded" modal content
      Then User validate the modal & clicks on Close button

      Examples:
      | data          |
      | TC314_NSBReinvest |



  @CashIn_NSB_More_Amount_Journey @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: CashIn into NSB product & CashIn more amount journey for <data>
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then User enters Amount for Cashin
    And User clicks on confirm button
    Then validate "Insufficient Fund" modal content
    Then User validate the modal & clicks on Close button
    Examples:
      | data          |
      | TC315_NSBCashIn |



  @Reinvest/CashIn_NSB_Less_Amount_Journey  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest into NSB product & Reinvest more amount journey for <data>
    Then login to Ireland State Savings with "<data>" as data reference
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button
    Then Validate error message on details page when "<scenario>"

    Examples:
      | data          | scenario|
      | TC316_NSBLessAmount | FT-NSB-below minimum purchase|


  @Reinvest_FT_To_FT_Enter_Journey  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: Reinvest into any product Allocate full amount button journey FT for <TCNO>
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then User redirected to thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal

    Examples:
      | TCNO          |
      | TC48_ReinvestSingle|




