Feature: Trivago Test

Scenario: Choose Hotel Room
Given Open Browser and set Properties
And Open URLT
And Type Destination
And Choose Date
And Select Room
And Choose occupants 
And Click Confirm button and click Search
And Select Accommodation type as Hotels only and choose 4 stars
And Select Guest rating as Very Good
And Set Hotel Location as Agra Fort and click Done
And Select Air conditioning, Restaurant and WiFi and click Done
When Sort the result as Rating & Recommended
Then Print the Hotel name, Rating, Number of Reviews and Click View Deal
And Print the URL of the Page
And Print the Price of the Room and click Choose Your Room
And Click Reserve and I'll Reserve
And Close the browser