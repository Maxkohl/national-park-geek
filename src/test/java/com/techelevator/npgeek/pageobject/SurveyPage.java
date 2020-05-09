package com.techelevator.npgeek.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SurveyPage {

	private WebDriver webDriver;

	public SurveyPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public SurveyPage enterPark(String parkEntered) {
		Select parkField = new Select(webDriver.findElement(By.id("selectPark")));
		parkField.selectByVisibleText(parkEntered);
		return this;
	}
	
	public SurveyPage enterEmail(String emailAddress) {
		WebElement emailField = webDriver.findElement(By.name("email"));
		emailField.sendKeys(emailAddress);
		return this;
	}
	
	public SurveyPage enterState(String stateEntered) {
		Select stateField = new Select(webDriver.findElement(By.name("state")));
		stateField.selectByVisibleText(stateEntered);
		return this;
	}
	
	public SurveyPage enterActivity(String activityLevel) {
		WebElement activityField = webDriver.findElement(By.id("activityButtons"));
		webDriver.findElement(By.id(activityLevel)).click();
		return this;
	}

	
	public FavoriteParksPage submitForm() {
		WebElement submitButton = webDriver.findElement(By.id("submit"));
		submitButton.click();
		return new FavoriteParksPage(webDriver);
	}
}