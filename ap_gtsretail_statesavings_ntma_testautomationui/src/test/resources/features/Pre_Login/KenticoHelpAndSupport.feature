@HelpAndSupport  @Regression @Regression_PreLogin @UAT_Regression @PREPROD_Regression
Feature: HelpAndSupport Page

  Background:
    Given Launch the Ireland State Savings Online Homepage

  @HelpAndSupportPage
  Scenario: HelpAndSupportPage
    And Select HelpandSupport tab
    Then Validate HelpandSupport Page

  @HelpAndSupportFAQLinks
  Scenario: HelpAndSupportFAQLinks
    And Select HelpandSupport tab
    Then Validate HelpandSupport FAQ Links

  @AboutLinks
  Scenario: AboutLinks
    And Select HelpandSupport tab
    Then Validate About Links

  @GettingStartedLinks
  Scenario: GettingStartedLinks
    And Select HelpandSupport tab
    Then Validate GettingStarted Links

  @NotificationLinks
  Scenario: NotificationLinks
    And Select HelpandSupport tab
    Then Validate Notification Links

  @PersonalInformationLinks
  Scenario: PersonalInformationLinks
    And Select HelpandSupport tab
    Then Validate PersonalInformation Links


  @SecurityLinks
  Scenario: SecurityLinks
    And Select HelpandSupport tab
    Then Validate Security Links

  @YourSavingsLinks
  Scenario: Your Savings Links
    And Select HelpandSupport tab
    Then Validate YourSavings Links

  @CloseServiceLinks
  Scenario: Close Ireland Service
    And Select HelpandSupport tab
    Then Validate Closing Your Service Links


  @StateSavingsProduct
  Scenario: Ireland State savings Product
    And Select HelpandSupport tab
    Then Validate IrelandStateSavings Links


  @PrizeBondsLinks
  Scenario: PrizeBondsLinks
    And Select HelpandSupport tab
    Then Validate PrizeBonds Links

  @HowToPurchaseLinks
  Scenario: HowToPurchaseLinks
    And Select HelpandSupport tab
    Then Validate HowToPurchase Links

  @ManageMyDetails
  Scenario: ManageMyDetailsLinks
    And Select HelpandSupport tab
    Then Validate ManageMyDetails Links


  @Repayments
  Scenario: RepaymentsLinks
    And Select HelpandSupport tab
    Then Validate Repayments Links

  @BereavementGuideSupport
  Scenario: BereavementGuideSupportLinks
    And Select HelpandSupport tab
    Then Validate BereavementGuideSupport Links

  @Complaints
  Scenario: ComplaintsLinks
    And Select HelpandSupport tab
    Then Validate Complaints Links

  @DormantAccounts
  Scenario: DormantAccountsLinks
    And Select HelpandSupport tab
    Then Validate DormantAccounts Links

  @ContactUs
  Scenario: ContactUsLinks
    And Select HelpandSupport tab
    Then Validate ContactUs Links


  @SecurityFinancialCrime
  Scenario: SecurityFinancialCrimeLinks
    And Select HelpandSupport tab
    Then Validate SecurityFinancialCrime Links

  @FinancialLiteracy
  Scenario: FinancialLiteracyLinks
    And Select HelpandSupport tab
    Then Validate FinancialLiteracy Links

#    Saahil to review why this test case is not included in the regression pack and recommend path forward
  @SearchH&S
  Scenario: Search functionality for help articles
    When Click sign in button on home page
    And login to Ireland State Savings with "Generic User" as data reference
    And  Click on Backtostatesavings
    And Select HelpandSupport tab
    And enter keyword in search box
    Then validate results are displayed

