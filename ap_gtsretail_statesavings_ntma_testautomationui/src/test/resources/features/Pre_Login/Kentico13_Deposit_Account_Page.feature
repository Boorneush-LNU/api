@Deposit_Account @Regression_PreLogin @Regression @UAT_Regression @PREPROD_Regression
Feature: Deposit Account Page

  Scenario: Deposit Account Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Our Products" Page
    And User clicks "Deposit Account" Tile link on Our Products Page
    Then Validate the Header Section on "Deposit Account" Page
    And Validate content for Banner section on "Deposit Account" Page
    And Validate the content for Highlights section on "Deposit Account" Page
    And Validate the content for Frequently asked questions
    And Validate the content for Forms and Downloads section on "Deposit Account" Page
    And Validate the Carousel section on "Deposit Account" Page
    And Validate the Footer section for the "Deposit Account" Page