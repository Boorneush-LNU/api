@SavingsBonds @Regression @Regression_PreLogin @UAT_Regression @PREPROD_Regression
Feature: Savings Bonds Page

  Scenario: Savings Bonds Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Our Products" Page
    And User clicks "Savings Bonds" Tile link on Our Products Page
    Then Validate the Header Section on "Savings Bonds" Page
    And Validate content for Banner section on "Savings Bonds" Page
    And Validate the content for Highlights section on "Savings Bonds" Page
    And Validate the content for Interest Rates and Returns section on "Savings Bonds" Page
    And Validate the content for Secondary Banner section
    And Validate the content for Frequently asked questions
    And Validate the content for Forms and Downloads section on "Savings Bonds" Page
    And Validate the Carousel section on "Savings Bonds" Page
    And Validate the Savings Calculator Icon presence on "Savings Bonds" page
    And Validate the Footer section for the "Savings Bonds" Page