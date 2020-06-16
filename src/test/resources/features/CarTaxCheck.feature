Feature: Check car tax registrations


  @cartaxcheck
  Scenario Outline: Compare car tax check from car registration

    Given I read the input file "<car input file>"
    When  I extract vehicle registration numbers based on pattern
    When  I fed each vehicle registration numbers extract from input file to cartaxcheck website and perform free car check
    Then  I compare the output returned with output file "<car output file>"

    Examples:

      | car input file | car output file |
      | car_input.txt  | car_output.txt  |
