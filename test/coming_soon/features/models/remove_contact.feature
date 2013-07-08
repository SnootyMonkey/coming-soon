Feature: Removing Contacts

	Background:
		Given the system knows about the following contacts
			|email								|referrer												|
			|zuck@facebook.com		|http://facebook.com/cool-stuff	|
			|obama@whitehouse.gov	|http://cia.gov/secret-stuff		|
			|jonny@apple.com 			|http://apple.com/pretty-stuff	|
		Then the contact count is 3
    And the content store is sane

	Scenario: Removing a contact by email
		When I remove the contact for "zuck@facebook.com"
		Then the contact count is 2
		And the contact "zuck@facebook.com" does not exist
		And the contact "obama@whitehouse.gov" exists
		And the contact "jonny@apple.com" exists
    And the content store is sane

	Scenario: Removing a contact by id
		When I remove the contact with id "2"
		Then the contact count is 2
		And the contact "zuck@facebook.com" exists
		And the contact "obama@whitehouse.gov" does not exist
		And the contact "jonny@apple.com" exists
    And the content store is sane

	Scenario: Erasing all contacts
		When I erase all contacts
		Then the contact count is 0
		And the contact "zuck@facebook.com" does not exist
		And the contact "obama@whitehouse.gov" does not exist
		And the contact "jonny@apple.com" does not exist
    And the content store is sane

	Scenario: Removing a contact that does not exist
		When I remove the contact for "zuck@facebook.co"
		Then the contact count is 3
		And the contact "zuck@facebook.com" exists
		And the contact "obama@whitehouse.gov" exists
		And the contact "jonny@apple.com" exists
		When I remove the contact with id "4"
		Then the contact count is 3
		And the contact "zuck@facebook.com" exists
		And the contact "obama@whitehouse.gov" exists
		And the contact "jonny@apple.com" exists
    And the content store is sane