Feature: Honda Two Wheeler

Scenario: Enquire Honda Scooters
Given Open the Browser and set Properties
And Go to the honda url
And Click on scooters and click Dio
And Click on Specifications and mouseover on Engine
And Put all the engine details as key and value into Map
And Click on scooters and click Activa125
And Click on Specifications and mouseover on Engine
And Put all the engine details as key and value into Map
And Compare Maps and print the different values of the samekeys
And Click FAQ from Menu
And Click Dio under Browse By Product
And Click  Vehicle Price and Select scooter, Dio BS-VI from the dropdown and click submit
And Click the price link
And Go to the new Window
And Select the state, city
And Print the price and model
When Click Product Enquiry and Fill all the mandatory field except Mobile, check the terms and conditions box and click submit
Then Verify the error message under the mobile number field
And Close the honda bike enquiry browser