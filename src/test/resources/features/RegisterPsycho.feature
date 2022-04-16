Feature: Register Psychologist Functionality


  Scenario Outline: As a psychologist i want to register in psychohelp.
    Given I want to register as psychologist
    And I enter my own information like name <name>, dni <dni>, email <email>, password <password>, phone <phone>, specialization <specialization>, formation <formation>, about <about>, genre <genre>, sessionType <session> and code <cmp>
    Then I should be able to see my newly psychologist account

    Examples:
      | name   | dni         | email           | password      | phone       | specialization | formation              | about               | genre           | session    | cmp     |
      | "Luis" | "76139991"  |"fano@gmail.com" | "12345679"    | "987425689" | "Ansiedad"     | "Universitaria"        | "Soy una prueba"    |  "Masculino"    | "Virtual"  | "1235"  |