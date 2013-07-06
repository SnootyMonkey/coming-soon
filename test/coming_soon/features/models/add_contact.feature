# Behavioral driven unit tests for the contact model

Feature: Adding Contacts

	The system should collect valid contacts so they can be notified later about important developments.

	Background:
		Given I have no contacts
		Then the contact count is 0

	Scenario: Adding a contact with an email
		When I add a contact for "zuck@facebook.com"
		Then the contact count is 1
		And the contact "obama@whitehouse.gov" does not exist
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has no referrer
		And the contact "zuck@facebook.com" has an updated-at of just before now
		
	Scenario: Adding a Contact with an email and a referrer
		When I add a contact for "zuck@facebook.com" with a referrer from "http://facebook.com/cool-stuff"
		Then the contact count is 1
		And the contact "obama@whitehouse.gov" does not exist
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has a referrer of "http://facebook.com/cool-stuff"
		When I delay a moment
		Then the contact "zuck@facebook.com" has an updated-at of just before now

	Scenario: Adding multiple contacts
		When I add a contact for "zuck@facebook.com" with a referrer from "http://facebook.com/cool-stuff"
		Then the contact count is 1
		And the contact "obama@whitehouse.gov" does not exist
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has a referrer of "http://facebook.com/cool-stuff"
		And the contact "zuck@facebook.com" has an updated-at of just before now
		When I delay a moment
		Then I add a contact for "obama@whitehouse.gov" with a referrer from "http://cia.gov/secret-stuff"
		Then the contact count is 2
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has a referrer of "http://facebook.com/cool-stuff"
		And the contact "obama@whitehouse.gov" exists
		And the contact can be retrieved by "obama@whitehouse.gov"
		And the contact "obama@whitehouse.gov" has a referrer of "http://cia.gov/secret-stuff"
		And the contact "obama@whitehouse.gov" has an updated-at of just before now
		And the contact "zuck@facebook.com" has an id before the contact "obama@whitehouse.gov"
		And the contact "zuck@facebook.com" and the contact "obama@whitehouse.gov" have unique ids
		And the contact "zuck@facebook.com" has an updated-at before the updated-at for contact "obama@whitehouse.gov"

	Scenario: Adding the same contact multiple times
		When I add a contact for "zuck@facebook.com" with a referrer from "http://facebook.com/cool-stuff"
		Then the contact count is 1
		And the contact "obama@whitehouse.gov" does not exist
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has a referrer of "http://facebook.com/cool-stuff"
		And the contact "zuck@facebook.com" has an updated-at of just before now
		When I delay a moment
		Then I add a contact for "zuck@facebook.com" with a referrer from "http://facebook.com/awesome-stuff"
		Then the contact count is 1
		And the contact "obama@whitehouse.gov" does not exist
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has a referrer of "http://facebook.com/awesome-stuff"
		And the contact "zuck@facebook.com" has a more recent updated-at than before

	Scenario: Don't add a contact with an invalid email
		When I add a contact for "zuck@facebook" with a referrer from "http://facebook.com/cool-stuff"
		Then the contact count is 0
		And the contact "zuck@facebook" does not exist