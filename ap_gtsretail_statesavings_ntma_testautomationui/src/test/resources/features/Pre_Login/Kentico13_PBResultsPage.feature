@PBResults  @Regression  @Regression_PreLogin @PbResult @UAT_Regression @PREPROD_Regression
Feature: Prize Bonds Result Page

  Scenario: Prize Bonds Results Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Prize Bonds" Page
    Then Validate the Header Section on "Prize Bonds" Page
    And Validate content for Banner section on Prize Bonds Page
    And Validate the content for Prize Bond Draw section Results
    Then Validate Dropdown contents in Pb results page
    And Validate the content for Buy Prize Bonds Section on "PBMapPage" Page in PbWinner Map View
    And Validate the content for Frequently asked questions in PBWinner Page
    And Validate the content for Forms and Downloads section on "PBMapPage" PBWinner Page
    And Validate the Footer section for the "PBWinner" Page
    And Click on List Button
  And Validate the Winners in the draw Header
And Navigate through all the buttons in Winner in draw
  And Validate the Table Header and contents in Pb Results
    And Validate the content for Frequently asked questions in PBWinner Page
    And Validate the content for Forms and Downloads section on "PBListPage" PBWinner Page
    And Validate the Footer section for the "PBWinner" Page


