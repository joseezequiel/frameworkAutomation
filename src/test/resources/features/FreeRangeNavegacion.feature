@Navigation
Feature: Navigation bar
    To see the subpages
    Without logging in
    I can click the navigation bar links

    Background: I am on the Free Range Testers web withouy logging in.
        Given I navigate to www.freerangetesters.com

    Scenario Outline: I can access the subpages through the navigation bar
        # Given I navigate to www.freerangetesters.com
        When I go to <section> using the navigation bar
        Examples:
            | section   |
            | Cursos    |
            | Recursos  |
            | Blog      |
            | Mentorías |
            | Udemy     |
            | Academia  |

    @Courses
    Scenario: Courses are presented correctly to potential customers
        # Given I navigate to www.freerangetesters.com
        When I go to Cursos using the navigation bar
        And I select Introduccion al Testing

    @Plans @Courses
    Scenario: Users can select a plan when signing up
        # Given I navigate to www.freerangetesters.com
        When I selects Elegir Plan
        Then The client can validate the options in the checkout page