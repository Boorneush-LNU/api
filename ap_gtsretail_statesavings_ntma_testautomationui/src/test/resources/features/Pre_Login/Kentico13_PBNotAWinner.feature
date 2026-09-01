@PrizeBondsNotAWinnerPage   @Regression  @Regression_PreLogin @PbNotAWinnerPage @UAT_Regression @PREPROD_Regression
Feature: Prize Bonds Not A Winner Page

  Scenario Outline: Validating Prize Bonds Not a Winner Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Prize Bonds" Page
    Then Validate the Header Section on "Prize Bonds" Page
    And Validate content for Banner section on Prize Bonds Page PB Not a Winner
    And Validate the content for Check My Numbers section for PB Not a Winner
    Then Enter your Not A Winning Prize Bond "<Value>"
    And Validate content for Banner section for Not a Winner Page
    And Validate the content for Buy Prize Bonds Section on "PBNotAWinner" Page in PbNotAWinner
    And Validate the content for Frequently asked questions in PBWinner Page
    And Validate the content for Forms and Downloads section on "Prize Bonds Winner" PBWinner Page
    And Validate the Footer section for the "PBWinner" Page
    And Click on Search Again Button Not a Winner

    Examples:
      |Value|
      |OS176157|