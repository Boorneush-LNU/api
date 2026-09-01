@RepaymentMatured
Feature: User CashIn Matured in State Savings portal

#      Queries [SSG]:
#  1. Shankar to review the tags below to confirm if they could be removed ?
#  - @Build_Sanity_Add_IBAN  ==> Shan { It is just a tag name & we are not using this Tag. Will remove this tag }
#  - @K13Regression ==> Shan -> { Updated to @TargetedMiniRegression }
#  - @K13RegPreprod ==> Shan -> { Updated to @TargetedMiniRegression }

  Background:
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page

  @Matured_Error_On_Enter_Amount  @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Error on enter amount page for FT product for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then Verify the error message displayed when amount entered is "<Amount>"

    Examples:
      | Scenario    | Amount |
      | TC27 | Repayment-Blank   |
      | TC28 | Repayment-Invalid |


  @Matured_Error_On_Enter_Amount  @ErrorScenarios @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Error on enter amount page for FT product for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then User enters Amount for Cashin
    And User clicks on confirm button
    Then validate "Insufficient Fund" modal content
    Then User validate the modal & clicks on Close button
    Examples:
      | Scenario    | Amount |
      | TC264_MoreThan | Repayment-MoreThan |



  @Matured_Review_Page_Cancel_Journey  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Cancel journey from review page for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on allocate full amount
    And validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    Then User click cancel button on review page

    Examples:
      | Scenario    |
      | TC269 |

  @Matured_Error_On_Enter_OTP_Page  @ErrorScenarios
  Scenario Outline: Error on OTP page for FT product for "<data>"
    Then login to Ireland State Savings with "<data>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on allocate full amount
    And validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    Then Validate error message on OTP page with multiple combination

    Examples:
      | data  |
      | CashIn User |


  @Matured_Must_Allocate_Full_Amount_Modal @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Must allocate full amount modal amount entered is less for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And User clicks on the Allocate to cash-in button
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
      | Scenario            |
      | TC267 |

  @Matured_Check_If_Iban_Setup
  Scenario Outline: Add iban link displayed or not check for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Examples:
      | Scenario |
      | ADDIBANCASHINMATURED    |

#    Remove Iban

    @Matured_Add_IBAN_IN_Journey  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: Add iban while cash-in for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then validate "Your Bank Details-Add IBAN" slider content
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    And User selects the Option
    And User click on allocate full amount
    And validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
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
    | TC41     |

  @Matured_CashIn_FT_Product_Allocate_Modal
  Scenario Outline: Matured Cash In for FT product using allocate full amount button for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on allocate full amount
    And validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType |
      | TC_05    | Cash-In    |
#      | TC_06    | Cash-In    |
#      | TC_09    | Cash-In    |
#      | TC_26    | Cash-In    |

  @RepayMatured @Matured_CashIn_FT_Product_Enter_Amount  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  @TargetedMiniRegression
  Scenario Outline: Matured Cash In for FT product by enter amount for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
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

    Examples:
      | Scenario | MethodType |
      | TC45    | Cash-In    |

  @Matured_CashIn_Installment_SSA_ChildCare_Product_Enter_Amount
  Scenario Outline: Matured Cash In For Installment/Child-Care/SSA Using Enter amount for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User click on the manage button and select the parent Account
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
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_14    |



  @Matured_CashIn_Installment_SSA_ChildCare_Product_Allocate_Modal
  Scenario Outline: Matured Cash In For Installment/Child-Care/SSA Using Allocate full amount for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User click on the manage button and select the parent Account
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on allocate full amount
    And validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType |
      | TC_15    | Cash-In    |


  @Matured_CashIn_Edit_LessAmount_AllocateFullAmount_Modal @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Edit the order & allocate full amount  for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
  Then User enters Amount for Cashin
  And User clicks on the confirm button
    And User click on edit your order review page
    Then User Reenters Amount for Cashin
    And User clicks on the confirm button
  And validate "You must allocate full amount (allocate to cash)" modal content
    Examples:
      | TCNO | MethodType |
      | TC266_FTLessAmount    | Cash-In    |


  @Matured_CashIn_LessAmount_AllocateFullAmount_Modal @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Edit the order & allocate full amount  for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And validate "You must allocate full amount (allocate to cash)" modal content
    Examples:
      | TCNO | MethodType |
      | TC265_CashInLessAmount    | Cash-In    |

  @MatureddetailcashINCancel @Regression @UAT_Regression @Post_Login_Regression
  Scenario: Cancel from detail page for "TC269"
    Then login to Ireland State Savings with "TC269" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    Then User click cancel button on review page
    And verify user lands on dashboard page


  @MaturedVerifycashINCancel @Regression @UAT_Regression @Post_Login_Regression
  Scenario: Cancel from verification page for "TC269"
    Then login to Ireland State Savings with "TC269" as data reference
    When User selects the product and clicks on manage button
    Then User click "Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on allocate full amount
    And validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    Then User click cancel button on review page
    And verify user lands on dashboard page





