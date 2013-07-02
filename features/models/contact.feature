# Behavioral driven unit tests for the contact model

Feature: Adding Contacts

	The system should collect contacts so they can be notified later about important developments.

	Background:
		Given I have no contacts
		Then the contact count is 0

	Scenario: Adding a contact with an email
		When I add a contact for "zuck@facebook.com"
		Then the contact count is 1
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has no referral
		
	Scenario: Adding a Contact with an email and a referral
		When I add a contact for "zuck@facebook.com" with a referral from "http://facebook/cool-stuff"
		Then the contact count is 1
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has a referral of "http://facebook/cool-stuff"

	Scenario: Adding multiple contacts
		When I add a contact for "zuck@facebook.com" with a referral from "http://facebook/cool-stuff"
		Then the contact count is 1
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has a referral of "http://facebook/cool-stuff"
		When I add a contact for "obama@whitehouse.gov" with a referral from "http://cia.gov/cool-stuff"
		Then the contact count is 2
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has a referral of "http://facebook/cool-stuff"
		And the contact "obama@whitehouse.gov" exists
		And the contact can be retrieved by "obama@whitehouse.gov"
		And the contact "obama@whitehouse.gov" has a referral of "http://cia.gov/cool-stuff2"
		And the contact "zuck@facebook.com" has an id before the contact "obama@whitehouse.gov"
		And the contact "zuck@facebook.com" and the contact "obama@whitehouse.gov" have unique ids

	Scenario: Adding the same contact multiple times
		When I add a contact for "zuck@facebook.com" with a referral from "http://facebook/cool-stuff"
		Then the contact count is 1
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has a referral of "http://facebook/cool-stuff"
		When I add a contact for "zuck@facebook.com" with a referral from "http://facebook/awesome-stuff"
		Then the contact count is 1
		And the contact "zuck@facebook.com" exists
		And the contact can be retrieved by "zuck@facebook.com"
		And the contact "zuck@facebook.com" has a referral of "http://facebook/awesome-stuff"

	# updated-at

# Feature: Retrieving Contacts

	# negative cases of retrieving what doesn't exist
	# retrieval by ID

# Feature: Deleting Contacts

	# negative cases of deleting what doesn't exist
	# by ID
	# by email
	# erase!

# Feature: Listing Contacts

	# all contacts
	# all email
	# all referrals
