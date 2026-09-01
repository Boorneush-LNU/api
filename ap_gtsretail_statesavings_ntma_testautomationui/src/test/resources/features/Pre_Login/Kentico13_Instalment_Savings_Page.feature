@InstalmentSavings   @Regression  @Regression_PreLogin @UAT_Regression @PREPROD_Regression
Feature: Instalment Savings Page

  Scenario: Instalment Savings Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Our Products" Page
    And User clicks "Instalment Savings" Tile link on Our Products Page
    Then Validate the Header Section on "Instalment Savings" Page
    And Validate content for Banner section on "Instalment Savings" Page
    And Validate the content for Highlights section on "Instalment Savings" Page
    And Validate the content for Interest Rates and Returns section on "Instalment Savings" Page
    And Validate the content for Secondary Banner section
    And Validate the content for Frequently asked questions
    And Validate the content for Forms and Downloads section on "Instalment Savings" Page
    And Validate the Carousel section on "Instalment Savings" Page
    And Validate the Footer section for the "Instalment Savings" Page