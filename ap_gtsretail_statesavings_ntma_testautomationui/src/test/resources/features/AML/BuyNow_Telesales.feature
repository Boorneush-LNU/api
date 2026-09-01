@PurchaseJourneys @PurchaseJourneysTelesales @Regression @UAT_Regression @PREPROD_Regression
Feature: Buy Now - Telesales

  @RegressionPurchaseTelesales @PositiveBuynowFlow @UAT_Regression
  Scenario Outline: Telesales Guest sole purchase journey <TestCaseName>
    Given Navigate to telesales "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | TestCaseName |
      | TC117        |
      | TC118        |
      | TC119        |
      | TC120        |
      | TC122        |
      | TC123        |
      | TC124        |
      | TC126        |
      | TC127        |
      | TC128        |
      | TC129        |
      | TC130        |
      | TC131        |
      | TC133        |
      | TC134        |
      | TC135        |
      | TC137        |
      | TC138        |


  @RegressionPurchaseTelesales @Guest_PBGift_SoleHoldertelesales @PositiveBuynowFlow @UAT_Regression
  Scenario Outline: Telesales Guest sole purchase PB Gift <TestCaseName>
    Given Navigate to telesales "<TestCaseName>"
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
      | TC121        |
      | TC125        |
      | TC132        |
      | TC136        |


  @RegressionPurchaseTelesales @PositiveBuynowFlow @UAT_Regression
  Scenario Outline: Telesales Guest joint purchase journey <TestCaseName>
    Given Navigate to telesales "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | TestCaseName |
      | TC139        |
      | TC140        |
      | TC141        |
      | TC142        |
      | TC143        |
      | TC144        |
      | TC145        |
      | TC146        |
      | TC147        |
      | TC148        |
      | TC149        |
      | TC150        |
      | TC151        |
      | TC152        |
      | TC153        |
      | TC154        |
      | TC155        |
      | TC156        |
      | TC157        |
      | TC158        |
      | TC159        |
      | TC160        |
      | TC161        |
      | TC162        |
      | TC163        |
      | TC164        |
      | TC165        |
      | TC166        |
      | TC167        |
      | TC168        |
      | TC169        |
      | TC170        |
      | TC171        |
      | TC172        |
      | TC173        |
      | TC174        |


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