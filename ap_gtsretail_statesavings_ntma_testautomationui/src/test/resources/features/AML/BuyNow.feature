@PurchaseJourneys @Regression @UAT_Regression @PREPROD_Regression
Feature: Purchase Journeys

  Background:
    Given Launch the Ireland State Savings Online Homepage

  @RegressionPurchaseNonTelesales  @NonTelesales @PositivePurchaseJourney @UAT_Regression @PREPROD_Regression
  Scenario Outline: Guest sole purchase journey <TestCaseName>
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | TestCaseName |
      | TC1          |
      | TC2          |
      | TC3          |
      | TC4          |
      | TC6          |
      | TC7          |
      | TC8          |
      | TC10         |
      | TC11         |
      | TC12         |
      | TC13         |
      | TC14         |
      | TC15         |
      | TC17         |
      | TC18         |
      | TC19         |
      | TC21         |
      | TC22         |


  @RegressionPurchaseNonTelesales   @GuestPBGift @NonTelesales @PositivePurchaseJourney @UAT_Regression @PREPROD_Regression
  Scenario Outline: Guest sole purchase PB Gift <TestCaseName>
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enters person details Prize Bond Gift
    Then User enters Prize Bond holder details
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | TestCaseName |
      | TC5          |
      | TC9          |
      | TC16         |
      | TC20         |
      | TC193        |
      | TC194        |
      | TC195        |
      | TC196        |
      | TC197        |
      | TC198        |
      | TC199        |
      | TC200        |
      | TC201        |
      | TC202        |
      | TC203        |
      | TC204        |
      | TC205        |
      | TC206        |
      | TC207        |
      | TC208        |
      | TC209        |
      | TC210        |


  @RegressionPurchaseNonTelesales  @NonTelesales @PositivePurchaseJourneys @UAT_Regression @PREPROD_Regression
  Scenario Outline: Sign in sole purchase journey <TestCaseName>
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | TestCaseName |
      | TC23         |
      | TC24         |
      | TC25         |
      | TC26         |
      | TC28         |
      | TC29         |
      | TC30         |
      | TC32         |
      | TC33         |
      | TC34         |
      | TC35         |
      | TC36         |
      | TC37         |
      | TC39         |
      | TC40         |
      | TC41         |
      | TC43         |
      | TC44         |


  @RegressionPurchaseNonTelesales @SignIn_Joint @rerunPurchase @NonTelesales @PositivePurchaseJourneys @UAT_Regression @PREPROD_Regression
  Scenario Outline: Sign in joint purchase journey <TestCaseName>
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | TestCaseName |
      | TC45         |
      | TC46         |
      | TC47         |
      | TC48         |
      | TC49         |
      | TC50         |
      | TC51         |
      | TC52         |
      | TC53         |
      | TC54         |
      | TC55         |
      | TC56         |
      | TC57         |
      | TC58         |
      | TC59         |
      | TC60         |
      | TC61         |
      | TC62         |
      | TC63         |
      | TC64         |
      | TC65         |
      | TC66         |
      | TC67         |
      | TC68         |
      | TC69         |
      | TC70         |
      | TC71         |
      | TC72         |
      | TC73         |
      | TC74         |
      | TC75         |
      | TC76         |
      | TC77         |
      | TC78         |
      | TC79         |
      | TC80         |


  @RegressionPurchaseNonTelesales @SignIn_PBGift @rerunPurchase @NonTelesales @PositivePurchaseJourneys @UAT_Regression @PREPROD_Regression
  Scenario Outline: Sign in sole purchase PB Gift journey <TestCaseName>
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enters Prize Bond holder details
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | TestCaseName |
      | TC27         |
      | TC31         |
      | TC38         |
      | TC42         |
      | TC175        |
      | TC176        |
      | TC177        |
      | TC178        |
      | TC179        |
      | TC180        |
      | TC181        |
      | TC182        |
      | TC183        |
      | TC184        |
      | TC185        |
      | TC186        |
      | TC187        |
      | TC188        |
      | TC189        |
      | TC190        |
      | TC191        |
      | TC192        |


  @RegressionPurchaseNonTelesales @Guest_Joint  @NonTelesales @PositivePurchaseJourneys @UAT_Regression @PREPROD_Regression
  Scenario Outline: Guest joint purchase journey <TestCaseName>
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | TestCaseName |
      | TC81         |
      | TC82         |
      | TC83         |
      | TC84         |
      | TC85         |
      | TC86         |
      | TC87         |
      | TC88         |
      | TC89         |
      | TC90         |
      | TC91         |
      | TC92         |
      | TC93         |
      | TC94         |
      | TC95         |
      | TC96         |
      | TC97         |
      | TC98         |
      | TC99         |
      | TC100        |
      | TC101        |
      | TC102        |
      | TC103        |
      | TC104        |
      | TC105        |
      | TC106        |
      | TC107        |
      | TC108        |
      | TC109        |
      | TC110        |
      | TC111        |
      | TC112        |
      | TC113        |
      | TC114        |
      | TC115        |
      | TC116        |



  @BuyNowError   @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest sole purchase journey <TestCaseName> Buy Now Error
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate your order page amount:
      | Blank |
      | Min   |
      | Max   |
    Then Validate error on review page
    Then Validate product and amount on review page
    Then Validate error on additional information page
    Then User selects customer level critical data elements

    Examples:
      | TestCaseName          |
      | GuestErrorCDE-Y       |
      | GuestJointErrorCDE-NN |
      | GuestJointErrorCDE-YN |
      | SignInErrorCDE-N      |
      | SignInErrorCDE-YY     |
      | SignInErrorCDE-NY     |


  @DashboardModal  @PositivePurchaseJourneys @UAT_Regression
  Scenario Outline: DashBoard Buynow Modal <TestCaseName>
    Then Click sign in button on home page
    Then login to Ireland State Savings with "<Data>" as data reference
    Then User clicks on PB Buynow from Dashboard Modal
    And validate "Purchase Prize Bonds Modal" modal content
    Examples:
      | Data                    | TestCaseName |
      | NewTC11_BuynowDashboard | NewTC_11     |


  @RemoveProduct  @PositivePurchaseJourneys @UAT_Regression
  Scenario Outline: Remove Product <TestCaseName>
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page & Remove Product
    And validate "Remove Product" modal content
    Examples:
      | TestCaseName |
      | TC1          |


  @DashBoardBuynow  @PositivePurchaseJourneys  @PreprodRegression @UAT_Regression @PREPROD_Regression @Post_Login_Regression @Regression
  Scenario Outline: Sign in sole Dashboard Buy now Complete Journey <TestCaseName>
    Then Click sign in button on home page
    Then login to Ireland State Savings with "<Data>" as data reference
    Then User clicks on PB Buynow from Dashboard
    Then User can sign-in & perform the journey
    Then Navigate to YourOrderPage "<TestCaseName>"
    Then validate Your Order Page Prize Bond
    Then validate Your Review Page Prize bond
    Then Enter payment details click pay PB

    Examples:
      | Data                    | TestCaseName |
      | NewTC11_BuynowDashboard | NewTC_11     |


  @SSCN_PPSN    @BuynowErrorScenarios @PreprodRegression @UAT_Regression @PREPROD_Regression @Post_Login_Regression @Regression
  Scenario Outline: Guest sole purchase journey <TestCaseName> Enter SCD N PPSN Number
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"

    Examples:
      | TestCaseName | scenario |
      | TC223PPSN    | PPSN     |


  @Random_SSCN  @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest sole purchase journey <TestCaseName> Enter Random SSCN
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"

    Examples:
      | TestCaseName    | scenario        |
      | TC224RandomSSCN | EnterRandomSSCN |


  @SSCN_And_PPSN @RegressionBN @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest sole purchase journey <TestCaseName> Enter SCD N SSCN/PPSN Number
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName   | scenario  |
      | TC225PPSN_SSCN | PPSN_SSCN |

  @Invalidppsn_sscn  @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Primary User Sign in joint purchase journey <TestCaseName> Invalid SSCN & PPSN
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
#    Then Validate Your Order page
    Examples:
      | TestCaseName            | scenario            |
      | TC226JointPrimarySignIn | JointApplicantSCD-N |
      | TC227JointPrimarySignIn | JointApplicantSCD-N |

  @GuestSoleInvalidSSCNJoint @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest sole purchase journey <TestCaseName> Enter SCD-N SSCN in Joint
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName    | scenario         |
      | TC228JointGuest | InvalidSSCNJoint |

  @GuestSoleInvalidPrimaryJoint @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest sole purchase journey <TestCaseName> Enter SCD-N SSCN in Both Applicant
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName           | scenario                |
      | TC229JointGuestInvalid | InvalidSSCNPrimaryJoint |
#
  @TelesalesGuestInvalidSSCNJoint @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Telesales Guest joint purchase journey <TestCaseName>
    Given Navigate to telesales "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName             | scenario         |
      | TC230JointGuestTelesales | InvalidSSCNJoint |

  @TelesalesGuestSolePPSN_SSCN @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Telesales Guest Sole purchase journey <TestCaseName>
    Given Navigate to telesales "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName            | scenario  |
      | TC231SoleGuestTelesales | PPSN_SSCN |

  @MinorDOB  @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest sole purchase journey <TestCaseName> Enter Minor DOB
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName  | scenario |
      | TC66_MinorDOB | MinorDOB |

  @GuestSoleFTInvalidSSCN @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest sole FT purchase journey <TestCaseName> Invalid SSCN
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName     | scenario    |
      | TC67_InvalidSSCN | InvalidSSCN |

  @GuestSoleFTInvalidEmail @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest sole FT purchase journey <TestCaseName> Invalid Email
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName      | scenario     |
      | TC68_InvalidEmail | InvalidEmail |

  @GuestSolePBGiftInvalidEmail @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest sole purchase PB Gift <TestCaseName> Invalid Email
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enters person details Prize Bond Gift
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName            | scenario           |
      | TC75_PBGiftInvalidEmail | InvalidEmailPbGift |

  @GuestSolePBGiftInvalidDOB @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest sole purchase PB Gift <TestCaseName>
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enters person details Prize Bond Gift
    Then User enters Prize Bond holder details PG as Gift
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName          | scenario     |
      | TC77_PBGiftInvalidDOB | PBInvalidDOB |


  @SignInSolePBInvalidDOB @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: SignIn sole purchase PB Gift <TestCaseName>
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enters Prize Bond holder details PG as Gift
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName                | scenario     |
      | TC98_SignInPBGiftInvalidDOB | PBInvalidDOB |


  @GuestJointFTSameAppJoint @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest Joint FT purchase journey <TestCaseName> Same Applicant Name
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName        | scenario            |
      | TC81JointApplicants | SameApplicantsJoint |

  @GuestJointFTInvalidDOB @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest Joint FT purchase journey <TestCaseName> Invalid DOB
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName                  | scenario   |
      | TC83JointApplicantsInvalidDOB | InvalidDOB |

  @GuestJointFTInvalidSSCNPrimaryJoint @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest Joint FT purchase journey <TestCaseName> Invalid SSCN
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName                   | scenario                |
      | TC84JointApplicantsInvalidSSCN | InvalidSSCNPrimaryJoint |

  @GuestJointFTInvalidEmailprimarySec @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Guest Joint FT purchase journey <TestCaseName> Invalid Email
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName                    | scenario               |
      | TC85JointApplicantsInvalidEmail | InvalidEmailPrimarySec |

  @SignInSoleRemoveAmount @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Sign in sole purchase journey <TestCaseName> Remove Amount
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName            | scenario           |
      | TC92RemoveDefaultAmount | PrepopulatedAmount |


  @SignInSoleRemoveAmountMiniMax @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Sign in sole purchase journey <TestCaseName> Remove Amount MaxiMini
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName | scenario           |
      | TC93Min      | PrepopulatedAmount |
      | TC93Max      | PrepopulatedAmount |


  @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Sign in sole purchase journey <TestCaseName> Remove Amount PB MaxiMini
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page PB
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName   | scenario       |
      | TC93PBMaxJoint | PBMiniMaxJoint |


  @SignInJointSameApplicantsJoint @UAT_Regression
  Scenario Outline: Sign in Joint purchase journey <TestCaseName> Same Applicant Error
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page SignIn Joint
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName         | scenario            |
      | TC102_SameApplicants | SameApplicantsJoint |


  @SignInJointMinorDOB @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Sign in Joint purchase journey <TestCaseName> Second Applicant MinorDOB
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page SignIn Joint
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName               | scenario |
      | TC103_SecApplicantMinorDOB | MinorDOB |

  @SignInJointSecondAppInvalidDOB @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Sign in Joint purchase journey <TestCaseName> Second Applicant InvalidDOB
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page SignIn Joint
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName                 | scenario      |
      | TC104_SecApplicantInvalidDOB | SecInvalidDOB |

  @SignInJointSecondAppInvalidSSCN @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Sign in Joint purchase journey <TestCaseName> Second Applicant InvalidSSCN
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page SignIn Joint
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName                  | scenario         |
      | TC105_SecApplicantInvalidSSCN | InvalidSSCNJoint |

  @SignInJointSecondAppInvalidEmail @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Sign in Joint purchase journey <TestCaseName> Second Applicant InvalidEmail
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page SignIn Joint
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName                   | scenario        |
      | TC106_SecApplicantInvalidEmail | SecInvalidEmail |

  @SignInSoleJointRemoveAmount @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: Sign in sole purchase journey <TestCaseName> Remove Amount
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName | scenario                |
      | TC107Remove  | PrepopulatedAmountJoint |

  @SignInSoleInvalidPBTwoHoldersInvalidDOB @BuynowErrorScenarios @UAT_Regression
  Scenario Outline: SignIn Sole purchase PB Gift <TestCaseName> InvalidDOB
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enters Prize Bond holder details PG as Gift
    Then Validate error message on Buynow details page when "<scenario>"
    Examples:
      | TestCaseName    | scenario               |
      | TC100_SignJoint | InvalidDOBPBTwoHolders |

  @CancelModal @UAT_Regression
  Scenario Outline: Cancel in Details Page <TestCaseName>
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate your order for buy now
    Then User click on cancel button on your details page
    Examples:
      | TestCaseName |
      | TC15         |


  @IrishBuynow @UAT_Regression
  Scenario Outline: Non Telesales-Guest-Sole-FT Purchase CDE N <TestCaseName>
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC202_Irish  |


  @IrishBuynow @UAT_Regression
  Scenario Outline: Non Telesales-Guest-Sole-PB Purchase CDE Y <TestCaseName>
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC203_Irish  |

  @IrishBuynow @UAT_Regression
  Scenario Outline: Non Telesales-Guest-Sole-Muti Purchase [FT] Purchase CDE N <TestCaseName>
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC204_Irish  |

  @IrishBuynow @UAT_Regression
  Scenario Outline: Non Telesales-Guest-Sole-Multi Purchase [FT+PB] Purchase CDE Y <TestCaseName>
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC205_Irish  |

  @IrishBuynow @UAT_Regression
  Scenario Outline: Non Telesales-Guest-Sole-PB as a GIFT Purchase CDE Y <TestCaseName>
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then User enters person details Prize Bond Gift in Irish
    Then User enters Prize Bond holder details in Irish
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC206_Irish  |

  @IrishBuynow @UAT_Regression
  Scenario Outline: Non Telesales-Guest-Sole-Prize Bonds as a GIFT (2 Holders) CDE Y <TestCaseName>
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then User enters person details Prize Bond Gift in Irish
    Then User enters Prize Bond holder details in Irish
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC210_Irish  |

  @IrishBuynow @UAT_Regression
  Scenario Outline: Non Telesales-SignIn-Sole-Multi Purchase [FT+Pb+PB Gift] Purchase CDE N
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then Click on Back to State Saving
    Then Select the Language as Gaeilge
    Given Click buy now
    When User select product for purchase in Irish SignIn
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC207_Irish  |

  @IrishBuynow  @UAT_Regression
  Scenario Outline: Non Telesales-Signin-Sole-Prize Bonds + Prize Bonds Gift Purchase CDE N
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then Click on Back to State Saving
    Then Select the Language as Gaeilge
    Given Click buy now
    When User select product for purchase in Irish SignIn
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC208_Irish  |

  @IrishBuynow @UAT_Regression
  Scenario Outline: Non Telesales-SignIn-Sole-Multi Purchase [FT+Pb+PB Gift] Purchase CDE N
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then Click on Back to State Saving
    Then Select the Language as Gaeilge
    Given Click buy now
    When User select product for purchase in Irish SignIn
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC209_Irish  |

  @IrishBuynow @UAT_Regression
  Scenario Outline: Non Telesales-SignIn-Joint-Multi Purchase [FT+Prize Bonds+Prize Bonds Gift (2 Holders)] Purchase CDE YY
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then Click on Back to State Saving
    Then Select the Language as Gaeilge
    Given Click buy now
    When User select product for purchase in Irish SignIn
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC211_Irish  |


  @IrishBuynow @UAT_Regression
  Scenario Outline: Non Telesales-Signin-Joint-Prize Bonds + Prize Bonds Gift (2 Holders) Purchase CDE NY
    Then Select the Language as Gaeilge
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase in Irish
    Then User can continue as guest or sign-in during the journey in Irish
    Then Click on Back to State Saving
    Then Select the Language as Gaeilge
    Given Click buy now
    When User select product for purchase in Irish SignIn
    Then Validate Your Order page in Irish
    Then Validate product and amount on review page in Irish
    Then User selects customer level critical data elements in Irish
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC212_Irish  |