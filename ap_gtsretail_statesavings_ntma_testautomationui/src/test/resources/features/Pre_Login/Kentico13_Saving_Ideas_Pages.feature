@SavingsIdeasPages @Regression @Regression_PreLogin @UAT_Regression @PREPROD_Regression
Feature: Savings Ideas Pages

Background:
  Given Launch the Ireland State Savings Online Homepage

  @SavingForAHouseDepositPage
  Scenario: Saving for a House Deposit Page Content and Redirections
    When User Navigates to "Saving for a House Deposit" Page from the Savings Ideas Section
    Then Validate content for Banner Section for Savings Ideas Page - "Saving for a House Deposit"
    And Validate content in General Context Paragraph section for "Saving for a House Deposit" page
    And Validate content in Carousel section for Savings Ideas Page - "Saving for a House Deposit"
    And Validate the Footer section for the "Saving for a House Deposit" Page

  @SavingForYourChildsFuture
  Scenario: Saving for your Child's Future Page Content and Redirections
    When User Navigates to "Saving for your Child’s Future" Page from the Savings Ideas Section
    Then Validate content for Banner Section for Savings Ideas Page - "Saving for your Child’s Future"
    And Validate content in General Context Paragraph section for "Saving for your Child’s Future" page
    And Validate content in Carousel section for Savings Ideas Page - "Saving for your Child’s Future"
    And Validate the Footer section for the "Saving for your Child’s Future" Page

  @SavingForRetirement
  Scenario: Saving For Retirement Page Content and Redirections
    When User Navigates to "Saving for Retirement" Page from the Savings Ideas Section
    Then Validate content for Banner Section for Savings Ideas Page - "Saving for Retirement"
    And Validate content in General Context Paragraph section for "Saving for Retirement" page
    And Validate content in Carousel section for Savings Ideas Page - "Saving for Retirement"
    And Validate the Footer section for the "Saving for Retirement" Page

