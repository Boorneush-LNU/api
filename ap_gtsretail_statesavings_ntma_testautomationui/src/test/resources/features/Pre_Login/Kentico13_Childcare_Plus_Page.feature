@ChildcarePlus @Regression_PreLogin @Regression @UAT_Regression @PREPROD_Regression
Feature: Childcare Plus Page

  Scenario: Childcare Plus Page Content and Redirections
    Given Launch the Ireland State Savings Online Homepage
    When User Navigates to "Our Products" Page
    And User clicks "Childcare Plus" Tile link on Our Products Page
    Then Validate the Header Section on "Childcare Plus" Page
    And Validate content for Banner section on "Childcare Plus" Page
    And Validate the content for Highlights section on "Childcare Plus" Page
    And Validate the content for Interest Rates and Returns section on "Childcare Plus" Page
    And Validate the content for Secondary Banner section
    And Validate the content for Frequently asked questions
    And Validate the content for Forms and Downloads section on "Childcare Plus" Page
    And Validate the Carousel section on "Childcare Plus" Page
    And Validate the Footer section for the "Childcare Plus" Page