@Homepage @Regression_PreLogin @Regression @UAT_Regression @PREPROD_Regression
Feature: Ireland State Savings Online Homepage

  Scenario: Homepage Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    Then Validate the Header Section on HomePage
    And Validate content for Main Banner section on Homepage
    And Validate content for Secondary Banner section on Homepage
    And Validate the content for Highlights section on Homepage
    And Validate the content for Carousel section on Homepage
    And Validate the content for Prize Bonds Section on Homepage
    And Validate the content for Saving Ideas section on Homepage
    And Validate the Footer section for the "Home" Page

