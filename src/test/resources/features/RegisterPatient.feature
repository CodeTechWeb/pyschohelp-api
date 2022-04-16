Feature: Register Patient Functionality


  Scenario Outline: As a patient i want to register in psychohelp.
    Given I want to register
    And I enter my own information like firstname <firstname>, lastname <lastname>, email <email>, password <password>, phone <phone> and gender <gender>
    Then I should be able to see my newly account

    Examples:
      | firstname | lastname | email           | password      | phone       | gender             |
      | "Luis"    | "Panta"  |"fano@gmail.com" | "12345679"    | "987425689" | "Masculino"        |