@BeginRegistrationPage @Regression_PreLogin @Regression @UAT_Regression @PREPROD_Regression
Feature: Begin Registration Page

  Scenario: Begin Registration Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Begin Registration" Page
    Then Validate content for Banner Section for Begin Registration Page
    And Validate content in Benefits of Registering section for Begin Registration page
    And Validate content in Who can register for Ireland State Savings Online? section for "Begin Registration" Page.
    And Validate the Footer section for the "Begin Registration" Page
