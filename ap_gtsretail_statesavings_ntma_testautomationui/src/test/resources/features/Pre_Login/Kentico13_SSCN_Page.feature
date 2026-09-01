@SSCNPage @Regression @Regression_PreLogin @UAT_Regression @PREPROD_Regression
Feature: SSCN Page

  Scenario: SSCN Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Help and Support" Page
    When User Click "Manage my details" link
    When User Click "What is the State Savings Customer Number (SSCN)?" link
    When User Click "Watch Video: ‘Your State Savings Customer Number (SSCN)’" link
    Then Validate the Header Section on "SSCN" Page
    And Validate the content on SSCN page
    And Validate the Footer section for the "SSCN" Page
