package com.techelevator.npgeek.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FavoriteParksPage {

	private WebDriver webDriver;

	public FavoriteParksPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public List<WebElement> getParkNameList(String parkName) {
		return webDriver.findElements(By.xpath("//*[contains(text(),'" + parkName + "')]"));
	}
	
}
