package com.techelevator.npgeek.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ParkDetailsPage {

	private WebDriver webDriver;

	public ParkDetailsPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public String getParkName() {
		return webDriver.findElement(By.className("parkTileHeader")).getText();
	}
	
}
