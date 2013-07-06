Feature: Deleting Contacts

	Background:
		Given the system knows about the following contacts
			|id	|email								|referrer												|
			|1	|zuck@facebook.com		|http://facebook.com/cool-stuff	|
			|2	|obama@whitehouse.gov	|http://cia.gov/secret-stuff		|
			|3	|jonny@apple.com 			|http://apple.com/pretty-stuff	|
		Then the contact count is 3

	Scenario: Deleting a contact by email
		When I delete the contact for "zuck@facebook.com"
		Then the contact count is 2
		And the contact "zuck@facebook.com" does not exist
		And the contact "obama@whitehouse.gov" exists
		And the contact "jonny@apple.com" exists

	Scenario: Deleting a contact by id
		When I delete the contact with id "2"
		Then the contact count is 2
		And the contact "zuck@facebook.com" exists
		And the contact "obama@whitehouse.gov" does not exist
		And the contact "jonny@apple.com" exists

	Scenario: Erasting all contacts
		When I erase all contacts
		Then the contact count is 0
		And the contact "zuck@facebook.com" does not exist
		And the contact "obama@whitehouse.gov" does not exist
		And the contact "jonny@apple.com" does non exist

	Scenario: Deleting a contact that does not exist
		When I delete the contact for "zuck@facebook.co"
		Then the contact count is 3
		And the contact "zuck@facebook.com" exists
		And the contact "obama@whitehouse.gov" exists
		And the contact "jonny@apple.com" exists
		When I delete the contact with id "2"
		Then the contact count is 3
		And the contact "zuck@facebook.com" exists
		And the contact "obama@whitehouse.gov" exists
		And the contact "jonny@apple.com" exists