Feature: Listing Contacts

  Background:
    Given I have no contacts
    Then the contact count is 0
    When I add a contact for "zuck@facebook.com" with a referrer from "http://facebook.com/cool-stuff"
    And I delay a moment
    And I add a contact for "obama@whitehouse.gov" with a referrer from "http://cia.gov/secret-stuff"
    And I delay a moment
    And I add a contact for "biden@whitehouse.gov" with a referrer from "http://cia.gov/secret-stuff"
    And I delay a moment
    And I add a contact for "jonny@apple.com"
    Then the contact count is 4
    And the content store is sane

  Scenario: Listing all contacts in collected at order
    When I list all contacts
    Then the list contains 4 items
#     And the next contact is "zuck@facebook.com" with the id 1 and the referrer "http://facebook.com/cool-stuff"
#     And the next contact is "obama@whitehouse.gov" with the id 2 and the referrer "http://cia.gov/secret-stuff"
#     And the next contact is "biden@whitehouse.gov" with the id 3 and the referrer "http://cia.gov/secret-stuff"
#     And the next contact is "jonny@apple.com" with the id 4 and no referrer

  Scenario: Listing all emails in collected at order
    When I list all emails
    Then the list contains 4 items

  Scenario: Listing all referrers in referral count order
    When I list all referrals
    Then the list contains 2 items
#     And the next referrer is "http://cia.gov/secret-stuff" with a count of 2
#     And the next referrer is "http://facebook.com/cool-stuff" with a count of 1
