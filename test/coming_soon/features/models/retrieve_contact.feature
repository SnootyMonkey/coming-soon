# Feature: Retrieving Contacts

#   Background:
#     Given the system knows about the following contacts
#       |email                |referrer                       |
#       |zuck@facebook.com    |http://facebook.com/cool-stuff |
#       |obama@whitehouse.gov |http://cia.gov/secret-stuff    |
#       |jonny@apple.com      |http://apple.com/pretty-stuff  |
#     Then the contact count is 3
#     And the content store is sane

#   Scenario: Retrieving a contact by email
#     When I retrieve the contact for "zuck@facebook.com" the id is "1"
#     When I retrieve the contact for "zuck@facebook.com" the referrer is "http://facebook.com/cool-stuff"
#     When I retrieve the contact for "obama@whitehouse.gov" the id is "2"
#     When I retrieve the contact for "obama@whitehouse.gov" the referrer is "http://cia.gov/secret-stuff"
#     When I retrieve the contact for "jonny@apple.com" the id is "3"
#     When I retrieve the contact for "jonny@apple.com" the referrer is "http://apple.com/pretty-stuff"

#   Scenario: Retrieving a non-existing contact by email
#     When I retrieve the contact for "zuck@faceboo.com" it doesn't exist

#   Scenario: Retrieving a contact by id
#     When I retrieve the contact for id "1" the email is "zuck@facebook.com"
#     When I retrieve the contact for id "1" the referrer is "http://facebook.com/cool-stuff"
#     When I retrieve the contact for id "2" the email is "obama@whitehouse.gov"
#     When I retrieve the contact for id "2" the referrer is "http://cia.gov/secret-stuff"
#     When I retrieve the contact for id "3" the email is "jonny@apple.com"
#     When I retrieve the contact for id "3" the referrer is "http://apple.com/pretty-stuff"

#   Scenario: Retrieving a non-existing contact by id
#     When I retrieve the contact for id "4" it doesn't exist