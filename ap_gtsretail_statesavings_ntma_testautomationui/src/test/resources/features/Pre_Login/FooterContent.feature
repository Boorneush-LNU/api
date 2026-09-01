@FooterContent  @Regression @Regression_PreLogin @UAT_Regression @PREPROD_Regression
Feature: Footer Content Pages

  Background:
    Given Launch the Ireland State Savings Online Homepage

  @AboutIrelandStateSavings
  Scenario: About Ireland State Savings Content Page
    And Select the About Ireland State Savings
    Then Validate About Ireland State Savings content

  @SiteUseTermsAndConditions
  Scenario: Site Use Terms And Conditions Content Page
    And Select the Site Use Terms and conditons
    Then Validate Site Use Terms and conditons Content


  @TermsAndConditions
  Scenario: Terms And Conditions Content Page
    And Select the Terms and conditions
    Then Validate Terms and conditions Content


  @AccessibilityElements
  Scenario: Accessibility Content Page
    Then Validate Accessibility Contents

  @DataProtectionElements
  Scenario: DataProtection Content Page
    Then Validate DataProtection Contents


  @CookiePolicyElements
  Scenario: CookiePolicy Content Page
    Then Validate CookiePolicy Contents


  @PrizeBondCompanyElements
  Scenario: PrizeBondCompany Content Page
    Then Validate PrizeBondCompany Contents


  @CookieWeUseElements
  Scenario: CookieWe use Page
    Then Validate CookieWeUse Contents