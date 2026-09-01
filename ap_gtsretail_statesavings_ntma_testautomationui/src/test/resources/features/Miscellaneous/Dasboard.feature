#Queries [SSG]:
#2. Is this feature only for Dashboard or is it also dealing with scenarios for Product Summary and Product Details features too ?  ==> Shan -> { No. This Feature does not deal with Prod Summary/Details }
# 3. Need to discuss and agree if validate summary details scenario can be added in scope of testing ? ==> Shan -> {  Yes. It can be added. }


@Dashboard
Feature: Dashboard

  Background:
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page

  @DashboardAddHoldings @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
   @TargetedMiniRegression
  Scenario: Add Holdings from dashboard <scenario>
    Then login to Ireland State Savings with "Generic User Products" as data reference
    Then click on "Request to add holdings" link
    Then validate "Add holding: Request to add holdings" slider content
    And select "Prize Bonds" from dropdown and add different holding
    Then click on add another product "Savings Bond 3 years" and add different holding
    And click remove button to remove "Savings Bond 3 years" product from the list
    Then click on confirm and download form button
    Then validate "Add holding:Thank you your holding is downloaded" slider content
    And validate "add holding form" is downloaded
   Then click close button on "add holding Thank you" slider

  @DashboardAddHoldingsCancelJourney  @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Add Holdings from dashboard <scenario>
    Then login to Ireland State Savings with "Generic User Products" as data reference
    Then click on "Request to add holdings" link
    Then validate "Add holding: Request to add holdings" slider content
    And select "Prize Bonds" from dropdown and add different holding
    Then click on add another product "Savings Bond 3 years" and add different holding
   Then click close button on "add holding" slider
    Then click on "Request to add holdings" link
    Then validate "Add holding: Request to add holdings" slider content
    Then Click on Close X button on the slider


  @ProductPortfolio @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario: Validate products displayed on dashboard
    Then login to Ireland State Savings with "TC129" as data reference
    Then validate product displayed on dashboard


  @HoldingDetails
  Scenario: Validate holding details on summary page
    Then login to Ireland State Savings with "TC_06" as data reference
    When User select State Savings Product and clicks on manage button
    Then validate product summary page

    @DownloadPDFDashboard
    Scenario: Download PDF Summary of your savings
      Then login to Ireland State Savings with "TC_02" as data reference
      Then click on "Download PDF Summary(dashboard)" link
      And validate "Your Savings PDF Summary" is downloaded

    @DownloadPDFSummary
    Scenario: Download PDF Summary of your savings
      Then login to Ireland State Savings with "TC_02" as data reference
      When User select State Savings Product and clicks on manage button
      And click on "Download PDF Summary(summary)" link
      And validate "Your Savings PDF Summary" is downloaded




