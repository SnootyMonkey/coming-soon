Feature: Contacts

	Scenario: Adding and retrieving contacts
		Given I have no contacts
		Then the contact count is 0
		When I add a contact for "zuck@facebook.com" with a referral from "http://facebook/cool-stuff"
		Then the contact count is 1