@AboutUsPage @Regression_PreLogin @Regression @UAT_Regression @PREPROD_Regression
Feature: About Us Page

  Scenario: About Us Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "About Us" Page
    Then Validate the Header Section on "About Us" Page
    And Validate content for Banner Section for About Us Page
    And Validate content in Highlights section for About Us Page
    And Validate content in Simple ways to save Carousel section for "About Us" Page
    And Validate content in Reasons to save section for the "About Us" Page
    And Validate the Footer section for the "About Us" Page



