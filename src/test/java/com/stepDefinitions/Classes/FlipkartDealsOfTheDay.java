package com.stepDefinitions.Classes;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.Utils.BasePageObject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/*	
 * 
 * @Author: Sukumar.Kutagulla
 * 
 */
public class FlipkartDealsOfTheDay extends BasePageObject {

	BasePageObject basePageObject = PageFactory.initElements(driver,
			BasePageObject.class);
		static Properties props;
		WebElement element;
		List<WebElement> list;
		SoftAssert softAssert = new SoftAssert();
		
		@Given("^User is on Flipkart homepage$")
		public void user_is_on_Flipkart_homepage() throws Throwable {
			basePageObject.startDriver();
			props = getDataProperties();
			
		 element = driver.findElement(By.cssSelector(props.getProperty("closePopup")));
		if(element.isDisplayed() == true)
			element.click();
		}

	 @Given("^Deals of the day products should be shown$")
	 public void deals_of_the_day_products_should_be_shown() throws Throwable {
		 boolean count = false;
		 list = driver.findElements(By.cssSelector(props.getProperty("DealsOfTheDayProductsList")));
		 int size = list.size();
		 if(size != 0) {
			 count = true;
		 }
		 
		 softAssert.assertTrue(count, "Product list is empty");
	 }

	 @Then("^\"([^\"]*)\" title should be shown$")
	 public void title_should_be_shown(String arg1) throws Throwable {
		 String title = driver.findElement(By.xpath(props.getProperty("DealsOfTheDayTitle"))).getText();
		 softAssert.assertEquals(title, arg1);
	 }

	 @Then("^Deals of the day block should have carousel control operation$")
	 public void deals_of_the_day_block_should_have_carousel_control_operation() throws Throwable {
		 element = driver.findElement(By.cssSelector(props.getProperty("rightArrow")));	 
		 softAssert.assertTrue(element.isDisplayed(), "Right Arrow mark is not displayed");
	 }

	 @Then("^Check products have an image and discount in Deals of the day block$")
	 public void check_products_have_an_image_and_discount_in_Deals_of_the_day_block() throws Throwable {
		 boolean images = false;
		 String discount = null;
		list = driver.findElements(By.cssSelector(props.getProperty("DealsOfTheDayImages")));
		List<WebElement> discounts =driver.findElements(By.cssSelector(props.getProperty("discounts")));
		for(WebElement image: list) {
			images = true;
			for(WebElement disc: discounts) {
				discount = disc.getText();
			}
			softAssert.assertTrue(image.isDisplayed(), "Image is not displayed");
			softAssert.assertTrue(!discount.equals(null), "Discounts are not displayed");
		}
	 }

	 @Then("^Check color Trending offers text$")
	 public void check_color_Trending_offers_text() throws Throwable {
		 String colorString = driver.findElement(By.xpath(props.getProperty("TrendingOffers"))).getCssValue("class");
		 String[] arrColor = colorString .split("#");
		 softAssert.assertTrue(arrColor[1].equals(null), "Trending offers color does not match");
		 
		 // Trending offers does not have css value so it will fail.
	 }
	 
	 @Then("^close the driver$")
	 public void close_the_driver() throws Throwable {
	    driver.quit();
	 }
}
