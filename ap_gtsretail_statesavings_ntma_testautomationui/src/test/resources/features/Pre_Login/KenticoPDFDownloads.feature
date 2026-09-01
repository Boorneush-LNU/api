@H&S_PDFDownloads  @Regression  @UAT_Regression @PREPROD_Regression
#@Regression_PreLogin

#   Change to Yes
Feature: PDFDownloads

  Background:
    Given Launch the Ireland State Savings Online Homepage

  #EnableDownload flag has to set as Yes -to download forms
  @HelpAndSupportFormsDownloads
  Scenario: HelpAndSupportFormsDownloads
    And Select HelpandSupport tab
    Then Validate FormsDownloads Links as English "download"
    And Validate Pdf Contents "English"

  @HelpAndSupportFormsDownloadsAsGaeilge @EnableDownloadFormsAsGaeilge
  Scenario: HelpAndSupportFormsDownloadsAsGaeilge
    And Select HelpandSupport tab
    Then Validate FormsDownloads Links as Gaeilge "download"
    And Validate Pdf Contents "Irish"