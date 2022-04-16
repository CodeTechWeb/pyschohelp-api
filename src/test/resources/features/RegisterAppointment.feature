Feature: Register an Appointment Functionality


  Scenario Outline: As a psychologist i want to schedule an appointment in psychohelp .
    Given I want to schedule
    And I schedule an appointment with url <url>, motive <motive>, history <history>, test <test>, treatment <treatment> and date <date>
    Then I should be able to see my newly appointment

    Examples:
      | url        | motive              | history   | test       | treatment | date     |
      | "meet.com" | "Appointment 1"     | "None"    | "test.com" | "Pills "  |   "Today"|