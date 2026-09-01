@PBWebAdmin
@PBAdmin_Report
Feature: PB Admin Purchase Order Validation


  @PBAdmin_OrdersReport
    @InProgress07072025
  Scenario Outline: Orders Report validations for "<TestCaseName>"
    Given Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    When Click on the "Orders" menu tab
    And Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria status-"<Status>", source -"<Source_Option>", From Date - "<From_Date>", To Date - "<To_Date>", Results Per Page - "<ResultsPerPg>"
    And Click on Search Button
    And Store the Search Results Online Purchase transactions from Orders Summary Page and its respective Order details
    And Click on the "Reports" menu tab
    And Click on "Orders Report" sub-tab
    And In Reports tab, For "<TestCaseName>" - Select the Search criteria Status-"<Status>", Products-"<Products>", Source-"<Source_Option>", Date From - "<From_Date>", "<To_Date>" and Click Search button
    Then Report generated should match the results as generated from Orders tab
#    And Click on download button and Select Excel option from dropdown
#    And Purchase transaction displayed in Exported Report generated should match the Search results captured earlier

    Examples:
      | TestCaseName          | Products                            | Status | From_Date   | To_Date     | AdminUser_Type | Source_Option | ResultsPerPg |
#   All
      | SS_QA_Additional_TC72 | All                                 | All    | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | SS_QA_4.01            | All                                 | All    | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | NA            | All          |
      | SS_QA_4.02            | Savings Certificates                | All    | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | NA            | All          |
      | SS_QA_4.03            | Savings Bonds                       | All    | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | NA            | All          |
      | SS_QA_4.11                  | Prize Bonds                         | All    |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | NA            | All          |
      | SS_QA_4.12                  | National Solidarity Bonds (4 Year)  | All    |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | NA            | All          |
      | SS_QA_4.13                  | National Solidarity Bonds (10 Year) | All    | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | NA            | All          |
##  Approved
      | TC21                  | All                                 | Approved | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | SS_QA_Additional_TC73 | Savings Bonds                       | Approved | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC23                  | Savings Certificates                | Approved | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC24                  | Prize Bonds                         | Approved | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC25                  | National Solidarity Bonds (4 Year)  | Approved | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC26                  | National Solidarity Bonds (10 Year) | Approved | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
##  For Approval
#      | TC21         | All                                 | For Approval       | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | SS_QA_Additional_TC81         | Savings Bonds                       | For Approval       | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC23         | Savings Certificates                | For Approval       |26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC24         | Prize Bonds                         | For Approval       |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC25         | National Solidarity Bonds (4 Year)  | For Approval       |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC26         | National Solidarity Bonds (10 Year) | For Approval       |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
##      Transferred to IPS
#      | TC21         | All                                 | Transferred to IPS |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | SS_QA_Additional_TC82         | Savings Bonds                       | Transferred to IPS |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC23         | Savings Certificates                | Transferred to IPS | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC24         | Prize Bonds                         | Transferred to IPS |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC25         | National Solidarity Bonds (4 Year)  | Transferred to IPS |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC26         | National Solidarity Bonds (10 Year) | Transferred to IPS |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
##  Closed
      | TC21                  | All                                 | Closed | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC22 | Savings Bonds                       | Closed | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | SS_QA_Additional_TC74                  | Savings Certificates                | Closed | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC24                  | Prize Bonds                         | Closed | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC25                  | National Solidarity Bonds (4 Year)  | Closed | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC26                  | National Solidarity Bonds (10 Year) | Closed | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
##      Parked
#      | TC21         | All                                 | Parked             |   26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | SS_QA_Additional_TC77         | Savings Bonds                       | Parked             |  26_Jun_2025      |  07_Jul_2025     |All|All|
#      | TC23         | Savings Certificates                | Parked             |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC25         | National Solidarity Bonds (4 Year)  | Parked             |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC26         | National Solidarity Bonds (10 Year) | Parked             |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
##      Pending
#      | TC21         | All                                 | Pending            |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | SS_QA_Additional_TC78         | Savings Bonds                       | Pending            |   26_Jun_2025      |  07_Jul_2025     |All|All|
#      | TC23         | Savings Certificates                | Pending            |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC24         | Prize Bonds                         | Pending            |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC25         | National Solidarity Bonds (4 Year)  | Pending            |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
#      | TC26         | National Solidarity Bonds (10 Year) | Pending            |  26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
##  Declined
      | TC21                  | All                                 | Declined | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC22 | Savings Bonds                       | Declined | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC23                  | Savings Certificates                | Declined | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC24                  | Prize Bonds                         | Declined | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC25                  | National Solidarity Bonds (4 Year)  | Declined | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | SS_QA_Additional_TC75                  | National Solidarity Bonds (10 Year) | Declined | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
##  Failed-Error
      | TC21                  | All                                 | Failed-Error | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC22 | Savings Bonds                       | Failed-Error | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC23                  | Savings Certificates                | Failed-Error | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | SS_QA_Additional_TC76                  | Prize Bonds                         | Failed-Error | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC25                  | National Solidarity Bonds (4 Year)  | Failed-Error | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC26                  | National Solidarity Bonds (10 Year) | Failed-Error | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
##  Referred
      | TC21                  | All                                 | Referred | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | SS_QA_Additional_TC79 | Savings Bonds                       | Referred | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC23                  | Savings Certificates                | Referred | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC24                  | Prize Bonds                         | Referred | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC25                  | National Solidarity Bonds (4 Year)  | Referred | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC26                  | National Solidarity Bonds (10 Year) | Referred| 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
##  Refunded
      | TC21                  | All                                 | Refunded | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | SS_QA_Additional_TC80 | Savings Bonds                       | Refunded | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC23                  | Savings Certificates                | Refunded | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC24                  | Prize Bonds                         | Refunded | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC25                  | National Solidarity Bonds (4 Year)  | Refunded | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |
      | TC26                  | National Solidarity Bonds (10 Year) | Refunded | 26_Jun_2025 | 07_Jul_2025 | SS Admin User  | All           | All          |




  @PageStructure_Reports
  Scenario Outline: Validate the Orders Page Structure for PB Admin Page for "<TestCaseName>"
    Given Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Reports" menu tab
    Then Validate the Page Body Structure of the Reports page
    And Validate the Header and Footer section

    Examples:
      | TestCaseName              | AdminUser_Type |
      | SS_QA_Additional_TC69     | SS Admin User  |
      | SS_QA_Additional_TC69_Add | PB Admin User  |

  @PageStructure_OrdersReports
  Scenario Outline: Validate the Orders Page Structure for PB Admin Page for "<TestCaseName>"
    Given Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Reports" menu tab
    Then Validate the Page Body Structure of the Reports page
    And  Click on "Orders Report" sub-tab
    Then Validate the Page Body Structure of the Orders Report page
    And Validate the Header and Footer section

    Examples:
      | TestCaseName              | AdminUser_Type |
      | SS_QA_Additional_TC70     | SS Admin User  |
      | SS_QA_Additional_TC70_Add | PB Admin User  |