Feature: BigBasket Test

Scenario: Chekout the cart and Take SnapShot
Given Open Browser and set wait
And Open URLBB
And Open Shop By Category tab
And Hover to FOODGRAINS, OIL & MASALA -> RICE & RICE PRODUCTS
And Click on BOILED & STEAM RICE
And Check the URL of the page with the site Navigation link
And Choose the Brand as bb Royal
And Go to Ponni Bolied Rice and select 10 kg bag from Dropdown
And Click Add button
And Go to search box and type Dal
And Add Toor/Arhar Dal 2kg and set Qty 2 from the list
And Click Address
And Select Chennai as City, Alandur-600016,Chennai as Area and click Continue
When Mouse over on My Basket
Then Take a screen shot
And Click view Basket and Checkout
And Click the close button and close the browser