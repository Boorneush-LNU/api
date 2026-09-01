@ReinvestPB
Feature: Reinvest From PB

    Background:
      Given Launch the Ireland State Savings Online Homepage
        Then Click sign in button on home page

#      Queries [SSG]:
#  1. Shankar to review the tags below to confirm if they could be removed ?
#  - @K13Regression  ==> Shan -> { Updated to @TargetedMiniRegression }
#  - @K13RegPreprod  ==> Shan -> { Updated to @TargetedMiniRegression }
#  - @runAutomationReg  ==> Shan -> { Will remove these tag as it is used for my Ref (Unwanted Tags) }

  @Check_Repay_Reinvest_Link_Displayed
  @TargetedMiniRegression
  Scenario Outline: ReinvestLink_Enter_Amount PB into single/multiple product for <TCNO>
        Then login to Ireland State Savings with "<TCNO>" as data reference
        When User selects the product and clicks on manage button
    And Check if the Repay-Reinvest link displayed
    Examples:
      | TCNO  |
      | TC_01 |
#      |TC_09  |


  @Reinvest_PB_SelectMultipleProduct_&_AllocateteToCash_RemainingAmount @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest From PB choose amount with MultipleProduct & allocateTocash Remaining amount <TCNO>
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
   And User clicks on confirm button
    Then validate "PB :You must allocate full amount(allocate to cash)" modal content
    And User clicks on allocate to cash modal
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    Examples:
      | TCNO                          | MethodType |
      | NewTC_4_PBFull |  Reinvest   |



  @PB_IBAN_NOT_ADDED_MODAL_REMAINING_AMOUNT  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Remaining amount modal when iban not added for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then validate "You must allocate full amount (IBAN not added)" modal content
    And User click on allocate modal confirm button
    Then validate "Your Bank Details-Add IBAN" slider content
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And User redirected to thank you page

    Examples:
      | TCNO  |
      | TC323 |

#Below test case is same as above but with different scenario name
#  @PB_Iban_Not_Added_Modal_Confirm_Button
#  Scenario Outline: PB Reinvest_Enter_Amount Iban not added confirm button modal journey
#    Then login to Ireland State Savings with "<TCNO>" as data reference
#    When User selects the product and clicks on manage button
##    And Click on the reinvest and cashin link
#    Then User click "Prize Bond Reinvest Cash In" link on the summary page
#    Then User click investment type on choose option page
#    And Verify the choose amount page
#    And User enter the amount on choose amount page
#    And Click confirm button to finalize the chosen amount on choose amount page
#    And user selects a product from the dropdown list and proceeds to enter the desired amount
#    And User clicks on confirm button to finalize product and amount for reinvestment
#    Then validate "Your Bank Details-Add IBAN" slider content
#    And User enter the iban
#    And Tick the checkbox on the iban page
#    Then User click on the verify bank button
#    Then validate "Your Bank Details-Verification code" slider content
#    Then enter the verification code
#    And click on confirm button on verification code slider
#    And Validate the detail on review transaction page
#    And Click confirm button on review page to finalize the transaction
#    Then Verify content on the otp page
#    And Verify the thank you message displayed
#    And Click on back button thank you page
#    Examples:
#      | TCNO    | MethodType |
#      | TC_Test | Reinvest   |

  @PB_IBAN_Not_Added_Allocate_Button_Modal
  Scenario Outline: PB Reinvest_Enter_Amount iban not added Allocate button modal journey for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
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
    Then Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And User redirected to thank you page

    Examples:
      | TCNO |
      | TC_90 |

  @PB_Range_Iban_Not_Added_Allocate_Button_Modal
  Scenario Outline: PB Reinvest_Bond_Range iban not added allocate button modal for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on choose bond range window
    And Verify the choose bond range window
    Then Select from bond range: "<bond range>"
    And User click on the confirm button
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
    Then Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And User redirected to thank you page
    Examples:
      | TCNO  | bond range            |
      | TC_91 | ADD466991 - ADD467002 |

  @PB_Range_Iban_Not_Added_Confirm_Button_Modal  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: PB Reinvest_Bond_Range iban not added confirm button modal journey for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on choose bond range window
    And Verify the choose bond range window
    Then Select from bond range: "<bond range>"
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then validate "You must allocate full amount (IBAN not added)" modal content
    And User click on allocate modal confirm button
    Then validate "Your Bank Details-Add IBAN" slider content
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    Then validate "Your Bank Details-Verification code" slider content
    Then enter the verification code
    And click on confirm button on verification code slider
    Then Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And User redirected to thank you page

    Examples:
      | TCNO  | bond range            |
      | TC51 | ADI060761 - ADI060772 |

  @Reinvest_From_PB_Enter_Amount_Cancel_Journey
  Scenario Outline: Reinvest PB Cancel Journey for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then Substantiate particulars on review transaction page
    Then User click cancel button on review page
    Examples:
      | TCNO  |
      | TC_01 |

  @Reinvest_From_PB_Enter_Amount  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: Reinvest_Enter_Amount PB into single/multiple product for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And User redirected to thank you page

    Examples:
      | TCNO  |
      | TC54 |


  @Reinvest_From_PB_Enter_Amount_Remaining_Amount @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest_Enter_Amount PB into single/multiple product_Remaining_Amount for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
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
    And Thank you page is displayed
    And Click on back button thank you page

    Examples:
      | TCNO  |
      | TC324 |

  @Reinvest_From_PB_Allocate_Modal_Reinvest_Page
  Scenario Outline: Reinvest_Allocate_Full_Amount PB into Multiple/Single product for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
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
    Examples:
      | TCNO  |
      | TC_19 |

  @Reinvest_PB_Enter_PB_Amount
  Scenario Outline: Allocate Full Amount on Enter Amount Page for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount (reinvest into PB modal)" modal content
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    Examples:
      | Scenario |
    |   TC_11       |

  @Reinvest_From_PB_Range  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  @TargetedMiniRegression
  Scenario Outline: PB Reinvest-in prize bond range for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on choose bond range window
    And Verify the choose bond range window
    Then Select from bond range: "<bond range>"
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
      And User clicks on confirm button to finalize product and amount for reinvestment
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button

      Examples:
        | Scenario | bond range            |
        | TC53         |AB907001 - AB907020|

  @Reinvest_PB_Bond_Range_Remaining_Amount
  Scenario Outline: PB Reinvest prize bond range Remaining amount modal for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on choose bond range window
    And Verify the choose bond range window
    Then Select from bond range: "<bond range>"
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then validate "PB :You must allocate full amount(allocate to cash)" modal content
    And User clicks on allocate to cash modal
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    Examples:
      | Scenario | bond range |
      | TC_13    | ADA663240 - ADA663251   |
      | TC_16    | ADA577856 - ADA577859   |

  @Reinvest_PB_Range_All_Select
  Scenario Outline: PB Reinvest Bond range select all PB Range for "<Scenario>"
    Then login to Ireland State Savings with "<Scenario>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And User click on choose bond range window
    And Verify the choose bond range window
    And User selects all prize bonds
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    Examples:
      | Scenario |
      | TC_32     |


   # Shan created this Scenario. Regression Suite
  @Reinvest_PB_choose_amount_edit_&_AllocatetefullAmount @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression
  Scenario Outline: Reinvest From PB choose amount edit your order allocate full amount  for "<TCNO>"
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Substantiate particulars on review transaction page
    And User click on edit your order review page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    Then validate "Allocate full amount" modal content
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed

    Examples:
      | TCNO                          | MethodType |
      | TC55_Edit | Reinvest   |

  @PB_Reinvest_MinimumAmount_ConfirmButton_ErrorMessage  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: PB Reinvest Product not selected click on Confirm button for "<Data>"
    Then login to Ireland State Savings with "<Data>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    Then Validate error message on details page when "<scenario>"
    Examples:
      | Data |scenario|
      | TC52_ReinvestSingleProd |FT-below minimum purchase amount|


  @PB_Reinvest_NoproductSelect_ConfirmButton_ErrorMessage  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: PB Reinvest Product not selected click on Confirm button for "<Data>"
    Then login to Ireland State Savings with "<Data>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    Then Validate error message on details page when "<scenario>"
    Examples:
      | Data |scenario|
      | TC53_ReinvestNoProduct |no product selected|

  @PB_Reinvest_FT_NoAmount_ConfirmButton  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: PB Reinvest Select FT Product & Directly click on Confirm button for "<Data>"
    Then login to Ireland State Savings with "<Data>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    Then Validate error message on details page when "<scenario>"
    Examples:
      | Data |scenario|
      | TC54_ReinvestNoAmount |FT-no amount entered|


  @PB_Reinvest_Error_On_Prize_Bond_Range_Window  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: PB Reinvest Error on bond range window for "<Scenario>"
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
      | TC51_ReinvestBondRange |


  @PB_Reinvest_FT_LessAmount_ConfirmButton @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: PB Reinvest Select FT Product & Enter 0 Directly click on Confirm button for "<Data>"
    Then login to Ireland State Savings with "<Data>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    Then Validate error message on details page when "<scenario>"
    Examples:
      | Data |scenario|
      | TC55_ReinvestInvalidAmount |FT-below minimum purchase|

  @Reinvest_POSB_EmptyAccNo_Amount_Confirm @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest POSB Account without AccountNo & Amount for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    Then Validate error message on details page when "<scenario>"
    Examples:
      | TCNO |scenario|
      | TC56_ReinvestPosbAccNo |DA-account number and amount error|

  @Reinvest_POSB_InvalidAccNo_Amount_Confirm @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest POSB Account with Invalid AccountNo & Valid Amount for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    Then Validate error message on details page when "<scenario>"
    Examples:
      | TCNO |scenario|
      | TC57_ReinvestInvalidAccNo |DA-account number error|

  @Reinvest_POSB_NoAccNo_InvalidAmount_Confirm  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: Reinvest POSB Account with NO AccountNo & InValid Amount for "<TCNO>"
    Then login to Ireland State Savings with "<TCNO>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    Then Validate error message on details page when "<scenario>"
    Examples:
      | TCNO |scenario|
      | TC58_ReinvestNoAccNoInvalidAmount |DA-No account number and Invalid amount error|

  @PB_Reinvest_PB_NoAmount_ConfirmButton  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: PB Reinvest Select PB Product & Directly click on Confirm button for "<Data>"
    Then login to Ireland State Savings with "<Data>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    Then Validate error message on details page when "<scenario>"
    Examples:
      | Data |scenario|
      | TC59_ReinvestPBNoAmount |PB-no amount entered|

  @PB_Reinvest_PB_LessAmount_ConfirmButton  @Regression @UAT_Regression @Post_Login_Regression
  Scenario Outline: PB Reinvest Select PB Product & Enter 0 Directly click on Confirm button for "<Data>"
    Then login to Ireland State Savings with "<Data>" as data reference
    When User selects the product and clicks on manage button
    Then User click "Prize Bond Reinvest Cash In" link on the summary page
    Then User click investment type on choose option page
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    Then Validate error message on details page when "<scenario>"
    Examples:
      | Data |scenario|
      | TC60_ReinvestPBInvalidAmount |PB-below minimum purchase|