@PBWebAdmin
  @PBAdmin_Home
Feature: PB Admin Home Page Validation

  @PageStructure_Home
  Scenario Outline: Validate the Home Page Structure for PB Admin Page for "<TestCaseName>"
    Given Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    Then Validate the Page Body Structure of the Home page
    And Validate the Header and Footer section
    And Click on "Version Information" link from the footer section
    And Validate the About Page

  Examples:
    |TestCaseName|AdminUser_Type|
    |SS_QA_Additional_TC66|SS Admin User|
    |SS_QA_Additional_TC66_Add|PB Admin User|
