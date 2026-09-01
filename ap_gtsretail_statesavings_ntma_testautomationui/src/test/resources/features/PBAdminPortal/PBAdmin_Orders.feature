#Saahil to review and update

@PBWebAdmin
@PBAdmin_Orders
Feature: PB Admin Purchase Order Validation

  @PBAdmin_TestDataSubmission
  @NotCompleted1
  Scenario: Online Purchase Transaction submission and Test Data creation
    Given User Submits State Savings FT purchase transactions from the State Savings portal for Set-"DataSet1"
    When User Submits State Savings FT and PB purchase transactions from the State Savings portal for Set-"DataSet2"
#    And User Submits Telesales State Savings FT purchase transactions from the State Savings portal for Set-"DataSet3"
#    And User Submits Telesales State Savings FT and PB purchase transactions from the State Savings portal for Set-"DataSet4"
#    And User Submits Telesales State Savings PB only purchase transactions from the State Savings portal for Set-"DataSet5"
#    And User Submits Telesales State Savings PB Gift purchase transactions from the State Savings portal for Set-"DataSet6"
    And User Submits Prize Bonds only purchase transactions from the State Savings portal for Set-"DataSet7"
    And User Submits Prize Bonds Gift only purchase transactions from the State Savings portal for Set-"DataSet8"
    And Launch the PB Admin portal and login with valid credentials as "PB Admin User"
    And Click on the "Orders" menu tab
    Then Validate that all the "PB Admin User" specific purchase reference transactions are displayed on the Order Summary Page
    Then Validate the purchase order transaction details from Order Details page
    And Launch the PB Admin portal and login with valid credentials as "SS Admin User"
    And Click on the "Orders" menu tab
    Then Validate that all the "SS Admin User" specific purchase reference transactions are displayed on the Order Summary Page
    Then Validate the purchase order transaction details from Order Details page

 #######################################################################################################################

  @PBAdmin_Recent_SearchResults_Status_FromDate_ToDate
  Scenario Outline: Website Orders Search Results functionality for "<TestCaseName>"
    Given Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    When Click on the "Orders" menu tab
    When Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria status-"<Status>", source -"<Source_Option>", From Date - "<From_Date>", To Date - "<To_Date>", Results Per Page - "<ResultsPerPg>" and Click Search button
    And Validate search results should be displayed as per the criteria set for From Date - "<From_Date>", To Date - "<To_Date>"
    And Validate search results should be displayed as per the criteria set for Status - "<Status>"
    And Validate search results should be displayed as per the criteria set for Source - "<Source_Option>"

    Examples:
      | TestCaseName    | ResultsPerPg | Status       | Source_Option | From_Date   | To_Date     | AdminUser_Type |
      | SS_QA_2.24      | All          | Approved      | All       | 01_Jun_2025 | 10_Jun_2025 | PB Admin User  |
      | SS_QA_Additional_TC46 | All           | Default | All  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_2.25 | All           | All | Tele Sales (FX)  | 01_Apr_2025 | 10_Jun_2025 | PB Admin User  |
      | SS_QA_2.26 | All           | All | Prize Bonds Only (PB)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_2.27 | All           | All | Mixed Orders (SS)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_2.28 | All           | All | Prize Bonds Only (PB)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_2.29 | All           | All | Mixed Orders (SS)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_2.30 | All           | For Approval | Mixed Orders (SS)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_2.31 | All           | Approved | Prize Bonds Only (PB)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_2.32 | All           | Parked | Prize Bonds Only (PB)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_2.33 | All           | Refunded | Mixed Orders (SS)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_Additional_TC47 | All          | For Approval | Tele Sales (FX)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_Additional_TC48 | All           | All | Mixed Orders (SS)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_Additional_TC49 | All           | Closed | Mixed Orders (SS)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |
      | SS_QA_Additional_TC52 | All           | Pending | Mixed Orders (SS)  | 01_Apr_2025 | 01_Jul_2025 | PB Admin User  |


  @PBAdmin_Recent_SearchResults_ResultsPerPg
  Scenario Outline: Website Orders Search Results functionality for Results Per Page drop down results for "<TestCaseName>"
    Given Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    When Click on the "Orders" menu tab
    When Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria status-"<Status>", source -"<Source_Option>", From Date - "<From_Date>", To Date - "<To_Date>", Results Per Page - "<ResultsPerPg>"
    And Click on Search Button
    Then Validate search results should be displayed as per the criteria set for Results per page and user should be able to navigate to different pages using pagination to view Website Orders Summary

    Examples:
      | TestCaseName    | ResultsPerPg | Status       | Source_Option | From_Date   | To_Date     | AdminUser_Type |
      | SS_QA_Additional_TC1     | 10          | Default      | Default       | 01_Jun_2025 | 01_Jul_2025 | SS Admin User  |
      | SS_QA_Additional_TC2     | 25          | Default      | Default       | 01_Jun_2025 | 01_Jul_2025 | SS Admin User  |
      | SS_QA_Additional_TC3     | 50          | Default      | Default       | 01_Jun_2025 | 01_Jul_2025 | SS Admin User  |
      | SS_QA_Additional_TC4     | 100          | Default      | Default       | 01_Jun_2025 | 01_Jul_2025 | SS Admin User  |
      | SS_QA_Additional_TC5     | All          | Default      | Default       | 01_Jun_2025 | 01_Jul_2025 | SS Admin User  |

  @PBAdminIndividualScenario
    @PBAdmin_Orders
  Scenario Outline: Online Purchase Transaction submission and validation in Admin portal for "<TestCaseName>"
    Given User Submits a purchase transaction from the State Savings portal for "<TestCaseName>" including "<transactionType>"
    When Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Orders" menu tab
    And Search the purchase transaction using Payment Reference field
    Then Validate that the Purchase Reference transaction details should be displayed
    And Click on the Purchase Order Number from Order Summary Page and Validate the Purchase Order Details for the Payment Reference

    Examples:
      | TestCaseName          | AdminUser_Type | transactionType |
#      | SS_QA_Additional_TC14 | SS Admin User  | FT              |
#      | SS_QA_Additional_TC15 | PB Admin User  | PB              |
#      | SS_QA_Additional_TC16 | SS Admin User  | FT              |
#      | SS_QA_Additional_TC17 | SS Admin User  | FT and PB       |
#      | SS_QA_Additional_TC18 | PB Admin User  | PB Gift       |
#      | SS_QA_Additional_TC19 | SS Admin User  | FT and PB       |
#      | SS_QA_Additional_TC20 | PB Admin User  | PB       |
#      | SS_QA_Additional_TC21 | SS Admin User  | FT and PB       |
      | SS_QA_Additional_TC22 | PB Admin User  | PB Gift       |

#      | SS_QA_Trial          |   SS Admin User            |FT and PB|
#      | SS_QA_2.20          |   PB Admin User            |PB Gift|
#      | SS_QA_2.14         |   PB Admin User            |PB|
#      | SS_QA_2.15          |   SS Admin User            |FT|


  @PBAdminIndividualScenario
    @PBAdmin_Orders
    @PBAdmin_SearchPayRefField
  Scenario Outline: Online Purchase Transaction submission and validation in Admin portal using Pay Ref Option field for "<TestCaseName>"
    Given User Submits a purchase transaction from the State Savings portal for "<TestCaseName>" including "<transactionType>"
    When Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Orders" menu tab
    Then Search the purchase transaction using Payment Reference field value created
    And Validate the Purchase Order Details for the Payment Reference
    #Add steps to approve the purchase from the transaction details screen

    Examples:
      | TestCaseName          | AdminUser_Type |transactionType|
      |   SS_QA_2.37       | SS Admin User  |                  |
      |   SS_QA_2.38          | SS Admin User  |               |


  @PBAdminIndividualScenario
    @PBAdmin_Orders
    @PBAdmin_SearchPayRefField
  Scenario Outline: Online Purchase Transaction submission and validation in Admin portal using Pay Ref Option field for "<TestCaseName>"
    Given Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    When Click on the "Orders" menu tab
    And Search the purchase transaction using Payment Reference field value as "<payRefTextFieldValue>" for "<TestCaseName>"
    And Validate the Purchase Order Details for the Payment Reference

    Examples:
      | TestCaseName          | AdminUser_Type | payRefTextFieldValue |
      | SS_QA_2.35            | SS Admin User  | SS2025411338         |
      | SS_QA_2.36            | SS Admin User  | SS20251001477        |
      | SS_QA_Additional_TC83 | PB Admin User  | PB20251001466        |
      | SS_QA_Additional_TC84 | PB Admin User  | PB2025411770         |


  @PBAdmin_Orders_Approve
    @14082025
  Scenario Outline: Online Purchase Transaction submission and Approve the case in the PB Web Admin portal for "<TestCaseName>"
    Given User Submits a purchase transaction from the State Savings portal for "<TestCaseName>" including "<transactionType>"
    When Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Orders" menu tab
    And Search the purchase transaction using Payment Reference field
    And Validate that the Purchase Reference transaction details should be displayed
    And Click on "Approve" checkbox for the Purchase Transaction from Orders Summary Page
    And Complete the process to "Approve" the selected transaction from the Order Summary Page
    Then In Orders Summary Page, Assign the Search criteria "Approved" from status drop down and Click Search button
    And Validate that the transaction should be displayed on the "Approved" list of purchases
    And Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria "For Approval" from status drop down and Click Search button
    But Validate that the transaction should not be displayed on the "For Approval" list of purchases

    Examples:
      | TestCaseName | AdminUser_Type |transactionType|
#      | SS_QA_2.09   | PB Admin User  |               |
#      | SS_QA_2.10| PB Admin User  |                  |
#      | SS_QA_2.13| PB Admin User  |                  |
#      | SS_QA_2.15         |            SS Admin User  ||
#      | SS_QA_2.16         |         SS Admin User     ||
#      | SS_QA_2.18         |         SS Admin User     ||
#      | SS_QA_2.19         |           SS Admin User   ||
      | SS_QA_2.20         |           PB Admin User   |PB Gift|



  @PBAdmin_Orders_Park
  Scenario Outline: Online Purchase Transaction submission and Park the case in the PB Web Admin portal for "<TestCaseName>"
    Given User Submits a purchase transaction from the State Savings portal for "<TestCaseName>" including "<transactionType>"
    When Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Orders" menu tab
    And Search the purchase transaction using Payment Reference field
    And Validate that the Purchase Reference transaction details should be displayed
    And Click on "Park" checkbox for the Purchase Transaction from Orders Summary Page
    And Complete the process to "Park" the selected transaction from the Order Summary Page
    Then In Orders Summary Page, Assign the Search criteria "Parked" from status drop down and Click Search button
    And Validate that the transaction should be displayed on the "Parked" list of purchases
    And Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria "For Approval" from status drop down and Click Search button
    But Validate that the transaction should not be displayed on the "For Approval" list of purchases

    Examples:
      | TestCaseName |AdminUser_Type|transactionType|
      | SS_QA_2.21        |   PB Admin User           ||
      | SS_QA_2.22|   SS Admin User           |        |
      | SS_QA_2.23|   SS Admin User           |        |
      | SS_QA_Additional_TC25|   SS Admin User           ||


  @PBAdmin_Orders_Bulk_Approval
  Scenario Outline: Bulk Approval of Purchase transactions submitted Online for "<TestCaseName>"
    Given User Submits purchase transactions from from the State Savings portal for Set-"<DataSetName>"
#    Given User Submits purchase transactions from the State Savings portal for Set-"<DataSetName>"
    When Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Orders" menu tab
    Then Validate that all the purchase reference transactions are displayed on the Order Summary Page
    And Click on each Purchase transaction to validate the transaction details on the Order Details page
    And Bulk "Approve" all the purchase transactions using the checkbox on Order Summary Page
    And Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria "Approved" from status drop down and Click Search button
    And Validate that "<DataSetName>" transactions should be displayed on the "Approved" list of purchases
    And Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria "For Approval" from status drop down and Click Search button
    But Validate that "<DataSetName>" transactions should not be displayed on the "For Approval" list of purchases

    Examples:
      | TestCaseName    |DataSetName|AdminUser_Type|
      | SS_QA_Additional_TC34|DataSet3|  PB Admin User          |
#      check test case name needs to be added and data to be mapped
#      | SS_QA_Additional_TC34_Add |DataSetX3|  PB Admin User          |



  @PBAdmin_Orders_Bulk_Park
  Scenario Outline: Bulk Park of Purchase transactions submitted Online for "<TestCaseName>"
    Given User Submits purchase transactions from from the State Savings portal for Set-"<DataSetName>"
#    Given User Submits purchase transactions from the State Savings portal for Set-"<DataSetName>"
    When Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Orders" menu tab
    Then Validate that all the purchase reference transactions are displayed on the Order Summary Page
    And Click on each Purchase transaction to validate the transaction details on the Order Details page
    And Bulk "Park" all the purchase transactions using the checkbox on Order Summary Page
    And Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria "Parked" from status drop down and Click Search button
    And Validate that "<DataSetName>" transactions should be displayed on the "Parked" list of purchases
    And Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria "For Approval" from status drop down and Click Search button
    But Validate that "<DataSetName>" transactions should not be displayed on the "For Approval" list of purchases

    Examples:
      | TestCaseName    |DataSetName|AdminUser_Type|
      | SS_QA_Additional_TC33|DataSet3|  PB Admin User          |
    #      check test case name needs to be added and data to be mapped
#      | SS_QA_Additional_TC33_Add |DataSetX3|  PB Admin User          |


  @PBAdmin_BulkApproveAndPark
  Scenario Outline: Bulk Approval and Parking of Purchase transactions submitted Online for "<TestCaseName>"
    Given User Submits purchase transactions from from the State Savings portal for Set-"<DataSetName>"
#    Given User Submits purchase transactions from the State Savings portal for Set-"<DataSetName>"
    When Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Orders" menu tab
    Then Validate that all the purchase reference transactions are displayed on the Order Summary Page
    And For "<DataSetName>", Bulk "Approve and Park" alternate purchase transactions using the checkbox on Order Summary Page
    And Validate that "<DataSetName>" transactions should be displayed on the appropriate search results list of purchases

    Examples:
      | TestCaseName    |DataSetName|AdminUser_Type|
#      | SS_QA_Additional_TC7_ADD |DataSetX3|  PB Admin User          |
#      check test case name needs to be added and data to be mapped
      | SS_QA_Additional_TC7 |DataSet3|  PB Admin User          |

  @PageStructure_Orders
  Scenario Outline: Validate the Orders Page Structure for PB Admin Page for "<TestCaseName>"
    Given Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Orders" menu tab
    Then Validate the Page Body Structure of the Orders page
    And Validate the Header and Footer section

    Examples:
      |TestCaseName|AdminUser_Type|
      |SS_QA_Additional_TC66|SS Admin User|
      |SS_QA_Additional_TC66_Add|PB Admin User|


############################################################################################################################################

  @PBAdmin_PayRefSearch_Change_Status
    @Manual
  Scenario Outline: Website Orders Search Result using Pay Ref Option functionality for "<TestCaseName>"
    Given User Submits a purchase transaction from the State Savings portal for "<TestCaseName>" including "<transactionType>"
    When Launch the PB Admin portal and login with valid credentials as "<AdminUser_Type>"
    And Click on the "Orders" menu tab
#    And For "<TestCaseName>", In Orders Summary Page, Enter Pay Ref field value-"<payRefOption>" and Click Search button
    And For "<TestCaseName>", In Orders Summary Page, Enter Pay Ref field value created earlier and Click Search button
    Then Search results displayed in the Order Details Page should satisfy the search criteria set
    And Update the order status as "<Status_to_Change>", Update comments and click Update Order
    When Click on the "Orders" menu tab
    And Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria "<Previous_Status>" from status drop down and Click Search button
    But Validate that the transaction should not be displayed on the "<Previous_Status>" list of purchases
    And Click on New Search Button
    And In Orders Summary Page, Assign the Search criteria "<Status_to_Change>" from status drop down and Click Search button
    And Validate that the transaction should be displayed on the "<Status_to_Change>" list of purchases
    And Click on the Purchase Order Number from Order Summary Page
    And Purchase Order Details screen should be displayed with the appropriate comments and updated status

    Examples:
      | TestCaseName | payRefOption                            | Status_to_Change|Previous_Status|AdminUser_Type|transactionType|
#      All
      | SS_QA_Additional_TC35| 	SS20251001463| Approved   | For Approval  |SS Admin User |                                      |