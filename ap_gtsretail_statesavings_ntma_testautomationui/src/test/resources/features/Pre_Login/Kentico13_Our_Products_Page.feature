@OurProducts  @Regression  @Regression_PreLogin @UAT_Regression @PREPROD_Regression
Feature: Our Products Page

  Scenario: Our Products Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Our Products" Page
    Then Validate the Header Section on "Our Products" Page
    And Validate content for Banner section on the "Our Products" Page
    And Validate the Section and Tile Header Order is correct on the Page
    And Validate the content for "Fixed Term Savings Products" section
    And Validate the content for "Prize Bonds" section
    And Validate the content for "Regular Saving Products" section
    And Validate the content for "Deposit Accounts" section
    And Validate content in Reasons to save section for "Our Products" Page
    And Validate the content for Need a little more help deciding? section
    And Validate the Savings Calculator Icon presence on "Our Products" page
    And Validate the Footer section for the "Our Products" Page