#Saahil to review and update

Feature: Admin Portal

  @PBAdmin
  Scenario: Admin portal positive flow
    Given Launch the admin portal
    Then click on "Orders" tab
    Then scrap data from orders table
