@Cash-In_Prize_Bond
Feature: Prize Bond Cash-in

#      Queries [SSG]:
#  1. Shankar to review the tags below to confirm if they could be removed ?
#  - @K13Regression ==> Shan -> { Updated to @TargetedMiniRegression }
#  - @K13RegPreprod ==> Shan -> { Updated to @TargetedMiniRegression }
#  - @Build_Sanity_Add_IBAN ==> Shan -> { It is just a tag name & we are not using this Tag(used for my Ref). Will remove this tag }

  Background:
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page


  @PB_Error_On_Choose_Amount_Page @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: PB Cash_In Amount more than available Less than available error for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And amount entered is "<Amount>" ensure error message displayed on Choose Amount page

    Examples:
      | Scenario | Amount                         |
      | TC47_Error    | ChooseAmount-Blank             |
      | TC47_Error    | ChooseAmount-MoreThanAvailable |

  @PB_Error_On_Enter_OTP_Page @ErrorScenarios
  Scenario Outline: PB cash-in error on OTP Page for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    Then Validate error message on OTP page with multiple combination

    Examples:
      | Scenario |
      | TC_03    |


  @PB_Cancel_Journey
  Scenario Outline: PB Cancel Journey for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Validate the detail on review transaction page
    Then User click cancel button on review page
    Examples:
      | Scenario |
      | TC_03    |

  @PB_Error_On_Prize_Bond_Range_Window @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: PB cash-in Error on bond range window for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on choose bond range window
    And Verify the choose bond range window
    And User click on the confirm button
    And Verify error message displayed on prize bond range window

    Examples:
      | Scenario |
      | TC48 |

  @PB_Add_IBAN_IN_Journey
  Scenario Outline: Prize Bond: Add iban while cash-in for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then validate "Your Bank Details-Add IBAN" slider content
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
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
    Examples:
      | Scenario    |
      | ADD_IBAN_PB |

  @PB_Cash_In_Enter_Amount  @Regression @UAT_Regression @Post_Login_Regression   @PREPROD_Regression
  @TargetedMiniRegression
  Scenario Outline: PB cash-in Enter amount for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
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

      Examples:
        | Scenario | MethodType |
        | TC46_PBCashin    | Cash-In    |

  @PB_Cash_In_Allocate_Full_Amount
  Scenario Outline: PB cash-in Allocate full amount for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And Click on allocate full amount of Amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Validate the details on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    Examples:
      | Scenario | MethodType |
      | PB_TC_02 | Cash-In    |



    @PB_Cash_In_Choose_Bond_Range  @Regression @UAT_Regression @Post_Login_Regression  @PREPROD_Regression
   @TargetedMiniRegression
  Scenario Outline: PB cash-in prize bond range select bond range for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on choose bond range window
    And Verify the choose bond range window
    Then Select from bond range: "<Range>"
    And User click on the confirm button
    And Validate the details on review transaction page prize amount
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page

    Examples:
      | Scenario                         | Range                 |
      | TC47_Range | SX483622 - SX483629 |


  @PB_Cash_In_Choose_Prize_Bond_Range
  Scenario Outline: PB cash-in prize bond range for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on choose bond range window
    And Verify the choose bond range window
    And User selects all prize bonds
    And User click on the confirm button
    And Validate the details on review transaction page prize amount
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    Examples:
      | Scenario   |
      | PB_Range_2 |


  @Repayment_PB_Choose_Amount_Edit_&_AllocateteFullAmount @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Edit the order & allocate full amount PB  for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
   And Substantiate particulars on review transaction page Cash In
    And User click on edit your order review page
    And User click on allocate full amount button after edit
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Substantiate particulars on review transaction page Cash In
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    Examples:
      | TCNO                          | MethodType |
      | NewTC_1_Cash In |  Cash In   |


  @PB_Cash_In_ChooseAmount_ConfirmButton  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: PB cash-in Directly click on Confirm button for "<Data>"
    Then login to Ireland State Savings with "<Data>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    Then Validate error message on details page when "<scenario>"
    Examples:
      | Data                          | MethodType |scenario|
      | TC49_PBReinvest_Error |  Reinvest  |     Repayment-Blank  |
| TC50_PBReinvestInvalid_Error|  Reinvest  |     Repayment-Invalid  |



  @PB_Reinvest_ChooseAmount_ConfirmButton_MinimumAmount
  Scenario Outline: PB cash-in Directly click on Confirm button for "<Data>"
    Then login to Ireland State Savings with "<Data>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And User select the Product & amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then Validate error message on details page when "<scenario>"
    Examples:
      | Data |scenario|
      | TC59_ReinvestPBNoAmount |PB FT-below minimum purchase|
