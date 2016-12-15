@browserstack
Feature: Google can search

Background:
	Given I am on Google

Scenario: Search for a term
	When I fill in "q" with "BrowserStack"
	And I submit
	Then I should see the title "BrowserStack - Google Search"