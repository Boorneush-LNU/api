@NationalSolidarityBonds   @Regression  @Regression_PreLogin @UAT_Regression @PREPROD_Regression
Feature: National Solidarity Bonds Page

  Scenario: National Solidarity Bonds Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Our Products" Page
    And User clicks "National Solidarity Bonds" Tile link on Our Products Page
    Then Validate the Header Section on "National Solidarity Bonds" Page
    And Validate content for Banner section on "National Solidarity Bonds" Page
    And Validate the content for Highlights section on "National Solidarity Bonds" Page
    And Validate the content for Interest Rates and Returns section on "National Solidarity Bonds" Page
    And Validate the content for Secondary Banner section
    And Validate the content for Frequently asked questions
    And Validate the content for Forms and Downloads section on "National Solidarity Bonds" Page
    And Validate the Carousel section on "National Solidarity Bonds" Page
    And Validate the Savings Calculator Icon presence on "National Solidarity Bonds" page
    And Validate the Footer section for the "National Solidarity Bonds" Page


