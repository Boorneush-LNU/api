@SignInPage @Regression @Regression_PreLogin @UAT_Regression @PREPROD_Regression
Feature: Sign In Page

  Scenario: Sign In Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Sign in" Page
    Then Validate the Header Section on "Sign in" Page
    And Validate content for Banner Section for Sign In page
    And Validate content in Sign In Panel section for Sign In page
    And Validate content in Highlights or Criteria section for "Sign in" Page
    And Validate the Footer section for the "Sign in" Page



