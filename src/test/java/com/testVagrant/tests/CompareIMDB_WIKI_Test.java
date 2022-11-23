package com.testVagrant.tests;

import com.testVagrant.utils.Utilities;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.testVagrant.pageObject.BaseFunctions;
import com.testVagrant.utils.ExtentReport;
import com.testVagrant.utils.ScreenShot;
import com.testVagrant.utils.BrowserSelection;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CompareIMDB_WIKI_Test
{

	WebDriver driver;
	BrowserSelection browser = new BrowserSelection();

	Utilities utils = new Utilities();
	static ExtentReports extent = ExtentReport.getReport();
	BaseFunctions MovieSearch;


	@BeforeClass(alwaysRun=true)
	//Invoke Browser
	public void openBrowser() throws Exception 
	{
		driver = browser.invokeChromeBrowser();
		String baseUrl = utils.readProperties("baseUrl");
		driver.get(baseUrl);
		MovieSearch = new BaseFunctions(driver);
		
	}


	@Test(priority = 0)
	//homePageTitle Test - Pass if title contains "IMDb" else fail
	public void homePageTitleIMDB() throws Exception {

		ExtentTest test = extent.createTest("Homepage Title - IMDB");

		String homePageTitle = driver.getTitle();

		boolean a = false;
		if (homePageTitle.contains("IMDb")) {

			a = true;
			test.log(Status.PASS, "Homepage title contains - IMDb");
			ScreenShot sc = new ScreenShot(driver);
			sc.captureScreen();
			test.pass("Check screenshot for title", MediaEntityBuilder.createScreenCaptureFromPath(sc.screenshotPath).build());

		}
		else {
			test.log(Status.FAIL, "Homepage title doesn't contain - IMDb");
			ScreenShot sc = new ScreenShot(driver);
			sc.captureScreen();
			test.fail("Check screenshot for failure", MediaEntityBuilder.createScreenCaptureFromPath(sc.screenshotPath).build());

		}

		Assert.assertTrue(a);
	}	

	@Test (priority = 1, groups = "smoke", dependsOnMethods = {"homePageTitleIMDB"})
	//Test to get data from input sheet and searching movie details in IMDB

	public void searchMovieInIMDB() throws Exception
	{


		ExtentTest test = extent.createTest("Search Movie- IMDb");


		MovieSearch.getData("Movie");
		MovieSearch.searchMovieDetails("IMDB");


	}

	@Test (priority = 2)
	//homePageTitle Test - Pass if title contains "Wikipedia" else fail
	public void homePageTitleWikipedia() throws Exception {

		ExtentTest test = extent.createTest("Check Homepage Title Is Wikipedia");


		//sf.navigateToWiki();
		String homePageTitle = driver.getTitle();

		if (homePageTitle.contains("Wikipedia")) {


			test.log(Status.PASS, "Homepage title contains Wikipedia");
			ScreenShot sc = new ScreenShot(driver);
			sc.captureScreen();
			test.pass("Check screenshot for title", MediaEntityBuilder.createScreenCaptureFromPath(sc.screenshotPath).build());

		}
		else {
			test.log(Status.FAIL, "Homepage title doesn't contain Wikipedia");
			ScreenShot sc = new ScreenShot(driver);
			sc.captureScreen();
			test.fail("Check screenshot for failure", MediaEntityBuilder.createScreenCaptureFromPath(sc.screenshotPath).build());
			Assert.fail();
		}

	}	

	@Test (priority = 3, groups = "smoke", dependsOnMethods = {"homePageTitleWikipedia"})
	//Test to search movie in Wiki
	public void searchMovieInWikipedia() throws Exception
	{

		ExtentTest test = extent.createTest("Search Movie In Wikipedia");


		MovieSearch.getData("Movie");
		MovieSearch.searchMovieDetails("Wikipedia");


	}

	@Test (priority = 4, groups = "smoke", dependsOnMethods = {"searchMovieInWikipedia", "searchMovieInIMDB"})
	//Test to compare Country values in IMDB and Wiki
	public void compareCountry() throws Exception
	{

		ExtentTest test = extent.createTest("Search Movie In Wikipedia");

		boolean a = MovieSearch.compareValuesCountry();

		if (a)
		{
			test.log(Status.PASS, "Country name is same in both");
		}
		else 
		{
			test.log(Status.FAIL, "Country name is different");
			ScreenShot sc = new ScreenShot(driver);
			sc.captureScreen();
			test.fail("Check screenshot for failure", MediaEntityBuilder.createScreenCaptureFromPath(sc.screenshotPath).build());

		}

		Assert.assertTrue(a);

	}
	
	@Test (priority = 5, groups = "smoke", dependsOnMethods = {"searchMovieInWikipedia", "searchMovieInIMDB"})
	//Test to compare Release Date in IMDB and Wiki
	public void compareReleaseDate() throws Exception 
	{

		ExtentTest test = extent.createTest("Search Movie In Wikipedia");

		boolean b = MovieSearch.compareValuesReleaseDate();

		if (b) 
		{
			test.log(Status.PASS, "Release Date are same in both");
		}
		else 
		{
			test.log(Status.FAIL, "Release Date are different");
			ScreenShot sc = new ScreenShot(driver);
			sc.captureScreen();
			test.fail("Check screenshot for failure", MediaEntityBuilder.createScreenCaptureFromPath(sc.screenshotPath).build());

		}

		Assert.assertTrue(b);

	}
	@AfterMethod
	//To write and generate each test case in extent report
	public void extentWrite() 
	{
		extent.flush();
	}
	
	@AfterClass(alwaysRun=true)
	//Close Browser
	
	public void closeBrowser() 
	{
		
		driver.quit();
	}
}
