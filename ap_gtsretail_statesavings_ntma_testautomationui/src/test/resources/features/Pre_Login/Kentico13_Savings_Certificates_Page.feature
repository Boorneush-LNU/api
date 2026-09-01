@SavingsCertificates @Regression @Regression_PreLogin @UAT_Regression @PREPROD_Regression
Feature: Savings Certificates Page

  Scenario: Savings Certificates Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Our Products" Page
    And User clicks "Savings Certificates" Tile link on Our Products Page
    Then Validate the Header Section on "Savings Certificates" Page
    And Validate content for Banner section on "Savings Certificates" Page
    And Validate the content for Highlights section on "Savings Certificates" Page
    And Validate the content for Interest Rates and Returns section on "Savings Certificates" Page
    And Validate the content for Secondary Banner section
    And Validate the content for Frequently asked questions
    And Validate the content for Forms and Downloads section on "Savings Certificates" Page
    And Validate the Carousel section on "Savings Certificates" Page
    And Validate the Savings Calculator Icon presence on "Savings Certificates" page
    And Validate the Footer section for the "Savings Certificates" Page