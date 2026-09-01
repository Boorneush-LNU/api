@PPO
Feature: Prize Payment Option

#  Queries [SSG]:
#  1.  Shankar to confirm the following tags if they could be removed ?
#  -  @K13Regression @K13RegPreprod  ==> Shan { Updated to @TargetedMiniRegression }
#  2. Need to discuss and agree if the following scenarios could be added in the regression pack and what is the dependency from the test data prep work ?
#  - @OptionCheck, @FirstUserLogin  ==> Shan { @OptionCheck is working fine & for @FirstUserlogin we need Fresh Registration }

  Background:
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page

#    Transfer winning must be enabled
    @PPOAutoReinvest @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
    @TargetedMiniRegression
  Scenario Outline: Enable auto reinvest prize payment option <TCName>
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And User select prize bond settings from profile and settings menu
    Then User decides "autoreinvest" for existing winnings
    And User clicks confirm on PPO modal
    Then User verifies the notification banner displayed for PPO settings changed

    Examples:
    |TCName  | data   |
    |TC62| PB_Range_1 |

 @PBPortfolio @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
    Scenario Outline: No PB for <data>
  Then login to Ireland State Savings with "<data>" as data reference
  Then click profile & settings on dashboard page
  And User select prize bond settings from profile and settings menu
  And Validate PB Page content

   Examples:
     | data |
     | TC24 |


   #    Autoreinvest must be enabled
  @PPOTransfer  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  @TargetedMiniRegression
  Scenario Outline: Enable cash in prize payment option <TCName>
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And User select prize bond settings from profile and settings menu
    Then User decides "transfer" for existing winnings
    And User clicks confirm on PPO modal
    Then User verifies the notification banner displayed for PPO settings changed

    Examples:
      |TCName  | data   |
      |TC63| PB_Range_2 |


  @OptionCheck
  Scenario Outline: Validate PPO either option is enabled for <data>
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And User select prize bond settings from profile and settings menu
    Then User decides "<PrizePaymentOption>" for existing winnings
    And User clicks confirm on PPO modal
    And User validates if one option is enabled then other option is disabled
    Examples:
      | data       | PrizePaymentOption |
      | PB_Range_1 | Transfer           |
      | PB_Range_1 | Autoreinvest       |

    #This scenario is not a part of regression set since we need manual intervention of creating specific data which involves fresh registration
  @FirstUserLogin
  Scenario: Validate PPO either option is disabled
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And User select prize bond settings from profile and settings menu
    Then Validate both PrizePayment option are disabled


#  Manual Intervention required to ensure that the data is not having IBAN added to profile or else Remove IBAN (Reference SSCN to be used - 242601006)
  @IBANPPo  @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: PPO enable transfer winnings by adding iban for <data>
    Then login to Ireland State Savings with "<data>" as data reference
    Then click profile & settings on dashboard page
    And User select prize bond settings from profile and settings menu
    Then User decides "<PrizePaymentOption>" for existing winnings
    And User click add bank details on PPO modal
    And User add IBAN according to "<Journey>""<IBAN>""<Mobilenumber>"

    Examples:
      | data       | PrizePaymentOption | Journey | IBAN   |
      |TC76    | Transfer           | Blank       |   |






