Feature: verify Deals of the Day block
	
@test
Scenario: verify Deals of the day content
Given User is on Flipkart homepage
And Deals of the day products should be shown
Then "Deals of the Day" title should be shown
And Deals of the day block should have carousel control operation
And Check products have an image and discount in Deals of the day block
And Check color Trending offers text
And close the driver




