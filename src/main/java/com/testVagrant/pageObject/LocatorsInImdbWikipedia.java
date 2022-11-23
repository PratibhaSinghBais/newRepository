package com.testVagrant.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocatorsInImdbWikipedia {
	WebDriver driver;
	
	@FindBy(xpath = "//input[@type='text' and @placeholder = 'Search IMDb']")
	public static WebElement searchInIMDB;

	@FindBy(xpath = "(//a[@data-testid='search-result--const'])[1]")
	public static WebElement selectInIMDB;

	@FindBy (xpath = "//button[.='Country of origin']//following-sibling::div/ul/li/a")
	public static WebElement countryInIMDB;

	@FindBy(xpath = "//a[.='Release date']//following-sibling::div/ul/li/a")
	public static WebElement releaseDateInIMDB;

	@FindBy(xpath = "//input[@type='search' and @placeholder = 'Search Wikipedia']")
	public static WebElement searchInWikipedia;

	@FindBy(xpath = "(//a[@class='mw-searchSuggest-link'])[1]")
	public static WebElement selectInWikipedia;

	@FindBy (xpath = "//th[.='Country']//following-sibling::td")
	public static WebElement countryInWikipedia;

	@FindBy(xpath = "(//div[.='Release date']//parent::th)//following-sibling::td/div/ul/li")
	public static WebElement releaseDateInWikipedia;

	@FindBy(xpath = "(//*[@id='__next'])/main/div/section[1]/div/section/div/div[1]/section[9]")
	public static WebElement detailPage;
	

	public LocatorsInImdbWikipedia(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
