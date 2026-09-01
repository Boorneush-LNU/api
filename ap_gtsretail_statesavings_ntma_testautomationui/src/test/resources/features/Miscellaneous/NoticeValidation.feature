@NoticeValidations
Feature: NoticeValidation
#  Queries [SSG]
#Shankar to update the code for scenarios where feature file examples are having data reference and add it to Excel and pull it from there. ==> Shan -> { Need to work on this task, will complete this ASAP }

  Background:
    Given Launch the Ireland State Savings Online Homepage
    Then Click sign in button on home page

@Joint_Notice_Check @Regression @UAT_Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
Scenario Outline: Joint Content Validation for matured and non matured holdings
    Given login to Ireland State Savings with "<data>" as data reference
    When User select StateSavingProduct and clicks on manage button
    Then User clicks on joint button
  And User selects joint holding with mentioned Name
  Then User verify notice button with mentioned HoldingId
    Then User clicks on button and verifies the modal content
    When User click on more details and verifies button present is same as on holdings page
    Examples:
      |data|
      |TC1_NV|
      |TC2_NV|

  @Notice_Check_Installment&Childcare @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Content Validation for installment and Childcare <Scenario> for <caseType>
    Given login to Ireland State Savings with "<Scenario>" as data reference
    When User clicks on manage button and select monthly instalment savings
    And User opens matured holding page selects method type

    Examples:
      | Scenario    |              caseType                   |
      | TC12_NV     |    Notice_UAT_Install_Matured           |
      | TC57_NV     |      Notice_UAT_Child_Donor             |
      | TC16_NV     |       Notice_UAT_Child_Matured          |
      | TC17_NV     |     Notice_UAT_Child_Non_Matured        |
      | TC10_NV     |      Notice_UAT_Install_Non_Matured     |
      |TC14_NVCashin|       Notice_UAT_Install_Flushed        |


  @Notice_Check_FTProducts @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Content Validation For FT <TestCaseName>
    Given login to Ireland State Savings with "<Scenario>" as data reference
    When User select State Savings Product and clicks on manage button
    And User opens matured holding page selects method type
    Examples:
      | Scenario   |
      | TC33_NV    |
      | TC41_NV    |
      | TC43_NV_NSB4  |
      | TC26_NV    |
      | TC3_NV     |
      | TC7_NV_SB  |
      | TC20_NV    |
      | TC52_NV    |
      | TC9_NV     |
      | TC50_NV    |
      | TC54_NV    |
      | TC37_NV    |
      | TC48_NV    |


  @Notice_Check_FTProducts @Regression @Post_Login_Regression @PREPROD_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Content Validation For FT <TestCaseName>
    Given login to Ireland State Savings with "<Scenario>" as data reference
    When User select State Savings Product and clicks on manage button
    And User opens matured holding page selects method type
    Examples:
      | Scenario   |
      | TC3_NV     |
      | TC7_NV_SB  |


   @NSBJoint @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: NSB Joint Content Validation for matured and non matured holdings
    Given login to Ireland State Savings with "<data>" as data reference
     When User select StateSavingProduct and clicks on manage button
    Then User clicks on joint button
     And User selects joint holding with mentioned Name
     Then User verify notice button with mentioned HoldingId

    Examples:
      |data|
      |    TC34_NV  |
      |    TC44_NV  |
      |    TC49_NV  |
      |    TC51_NV  |
      |    TC53_NV  |
      |    TC4_NV   |
      |    TC8_NV   |
      |    TC27_NV  |


     @ChildcareInstalmentNonMatured @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Child&Instalment Joint Content Validation for matured and non matured holdings
    Given login to Ireland State Savings with "<data>" as data reference
       When User select StateSavingProduct and clicks on manage button
    Then User clicks on joint button
       And User selects joint holding with mentioned Name
       Then User clicks on Summary page
       Then User verify notice button with mentioned HoldingId
    Then User clicks on button and verifies the modal content
       When User click on more details and verifies button present is same as on holdings page
    Examples:
      |data|
      |    TC56_NV  |
      |    TC18_NV  |
      |    TC11_NV  |


  @InstalmentJoint @Regression @UAT_Regression @Post_Login_Regression @UAT_Regression_NonRepayReinvest
  Scenario Outline: Joint Content Validation for matured and non matured holdings Installment Saving
    Given login to Ireland State Savings with "<data>" as data reference
    When User select StateSavingProduct and clicks on manage button
    Then User clicks on joint button
    And User selects joint holding with mentioned Name
    Then User clicks on Summary page
    Then Navigate to the Holding Id
    When User click on more details and verifies button present is same as on holdings page in IS

    Examples:
      |data|
      |    TC13_NV  |

