@tag
Feature: Navigate to the FitPeo Revenue Calculator Page and Validate Recurring Reimbursment Values

  Background:
    Given Open the Fitpeo Official Website
    When Navigate to Revenue Calculator Tab

  @guinavigation
  Scenario: GUI Validation to navigate to checkboxes and click on the check box
    When Scroll Down to the Slider section
    And Adjust the Slider and set value to 820
    And Enter the value 560 in the text field
    Then Validate input value should be match with slider value
    And Select the checkboxes for following values
      |values   |
      |CPT-99091 |
      |CPT-99453 |
      |CPT-99454 |
      |CPT-99474 |
    And quit driver

  @datavalidation
  Scenario: Data Validation once checkbox are clicked
    When Enter the value 820 in the text field
    And Select the checkboxes for following values
      |values   |
      |CPT-99091 |
      |CPT-99453 |
      |CPT-99454 |
      |CPT-99474 |
    Then Validate values of Total Recurring Reimbursement for all Patients Per Month is $110700
    And quit driver