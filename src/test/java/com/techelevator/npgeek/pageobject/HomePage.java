package com.techelevator.npgeek.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver webDriver;

	public HomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	 
	public HomePage clickPictureLink() {
		WebElement parkLink = webDriver.findElement(By.id("Glacier National Park"));
		parkLink.click();
		return new HomePage(webDriver);
	}
	
	public ParkDetailsPage clickAdditionalParkInfo() {
		WebElement parkLink = webDriver.findElement(By.linkText("View Additional Park Details"));
		parkLink.click();
		return new ParkDetailsPage(webDriver);
	}
	
	public SurveyPage clickSurveyLink() {
		WebElement surveyLink = webDriver.findElement(By.linkText("SURVEY"));
		surveyLink.click();
		return new SurveyPage(webDriver);
	}
	
	public FavoriteParksPage clickFavoriteParksLink() {
		WebElement calculatorLink = webDriver.findElement(By.linkText("PARK RANKING"));
		calculatorLink.click();
		return new FavoriteParksPage(webDriver);
	}
}
