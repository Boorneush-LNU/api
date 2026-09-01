@PrizeBondsWinnerPage   @Regression  @Regression_PreLogin @PbWinnerPage @UAT_Regression @PREPROD_Regression
Feature: Prize Bonds Winner Page

  Scenario Outline: Validating Prize Bonds Winner Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Prize Bonds" Page
    Then Validate the Header Section on "Prize Bonds" Page
    And Validate content for Banner section on Prize Bonds Page PBWinner
    And Validate the content for Check My Numbers section for PB As Winner
    Then Enter your Winning Prize Bond "<Value>"
    And Validate content for Banner section on Winner Page
    And Validate the content for Buy Prize Bonds Section on "PBWinner" Page in PbWinner
    Then Validate Table contents in PrizeBond Winner Page
    And Validate the content for Frequently asked questions in PBWinner Page
    And Validate the content for Forms and Downloads section on "Prize Bonds Winner" PBWinner Page
    And Validate the Footer section for the "PBWinner" Page
    And Click on Search Again Button


    Examples:
      |Value|
      |OS176151|