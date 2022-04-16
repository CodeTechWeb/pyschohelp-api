Feature: Post a Publication Functionality


  Scenario Outline: As a psychologist i want to post in psychohelp .
    Given I want to post a publication
    And I post a publication with title <title>, description <description>, tags <tags>, photo <photo> and content <content>
    Then I should be able to see my newly publication

    Examples:
      | title              | description       | tags   | photo       | content                      |
      | "My first test"    | "Hello World"     | "test" | "photo.com" | "This is a publication test" |