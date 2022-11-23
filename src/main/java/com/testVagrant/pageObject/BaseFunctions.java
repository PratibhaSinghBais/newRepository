package com.testVagrant.pageObject;

import com.testVagrant.utils.Utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

import com.testVagrant.utils.ReadInputData;

public class BaseFunctions
{
	WebDriver driver;

	Utilities utils = new Utilities();
	LocatorsInImdbWikipedia pob;
	String  movie, releaseDateIMDB,countryIMDB,releaseDateWiki,countryWiki;
	//***********Constructor to initialize the driver**************
	
	public BaseFunctions(WebDriver driver) throws Exception
	{
		this.driver = driver;
		pob = new LocatorsInImdbWikipedia(driver);
	}
	
	

	//*******************Read Data from Input sheet********************
	public void getData(String str) throws Exception
	{
		ReadInputData read = new ReadInputData();
		movie = read.readData(str);
		System.out.println("Movie Name: "+movie);
	}
	
	public void searchMovieDetails(String platform) throws Exception
	{
		try {
			searchIMDB(platform);
			getReleaseDateIMDB(platform);
			getCountryIMDB(platform);
			openWikipedia();
			searchWiki(platform);
			getReleaseDateWiki(platform);
			getCountryWiki(platform);
			compareValuesCountry();
			compareValuesReleaseDate();

		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	//*******************Navigating to Wiki*******************
	public void openWikipedia() throws Exception
	{
		String wikiUrl =  utils.readProperties("wikiUrl");
		driver.get(wikiUrl);
		
	}
	//*******************Searching the movie in IMDB*******************
	public void searchIMDB(String platform) throws Exception
	{
		try {
			if(platform.equalsIgnoreCase("IMDB"))
			{
				LocatorsInImdbWikipedia.searchInIMDB.click();
				LocatorsInImdbWikipedia.searchInIMDB.sendKeys(movie);
				Thread.sleep(3000);
				LocatorsInImdbWikipedia.selectInIMDB.click();
			}}
		catch(Exception e)
		{
			System.out.println("Not able to search the movie in IMDB");
			
		}
		
		}
	//*******************Searching the movie in Wiki*******************
	public void searchWiki(String platform) throws Exception
	{
		
		try {
			if(platform.equalsIgnoreCase("Wikipedia"))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				LocatorsInImdbWikipedia.searchInWikipedia.click();
				LocatorsInImdbWikipedia.searchInWikipedia.sendKeys(movie);
				Thread.sleep(3000);
				LocatorsInImdbWikipedia.selectInWikipedia.click();
			}
		}catch(Exception e)
		{
			System.out.println("Not able to search the movie in Wiki");
		}
	}
	
	//*******************Fetching Country value from IMDB*******************
	public void getCountryIMDB(String platform) throws Exception
	{
		try {
			if(platform.equalsIgnoreCase("IMDB"))
			{

				countryIMDB = LocatorsInImdbWikipedia.countryInIMDB.getText();

				System.out.println("Country IMDB: "+countryIMDB);
			}
		}catch(Exception e)
		{
			System.out.println("Not able to fetch Release Country from IMDB");
		}
	}
	
	//*******************Fetching Country Value from Wiki*******************
	public void getCountryWiki(String platform) throws Exception
	{
		try
		{
				if(platform.equalsIgnoreCase("Wikipedia"))
			{
				countryWiki= LocatorsInImdbWikipedia.countryInWikipedia.getText();
				System.out.println("Country Wiki: "+countryWiki);
	
			}
		}catch(Exception e)
		{
			System.out.println("Not able to fetch Release Country from Wiki");
		}

	}


	//*******************Fetching Release Date value from IMDB*******************
	public void getReleaseDateIMDB(String platform) throws Exception
	{
		try {
			JavascriptExecutor j = (JavascriptExecutor) driver;
			if(platform.equalsIgnoreCase("IMDB"))
			{
				
				j.executeScript("arguments[0].scrollIntoView();", LocatorsInImdbWikipedia.detailPage);
				releaseDateIMDB = LocatorsInImdbWikipedia.releaseDateInIMDB.getText();
				System.out.println("Release Date IMDB: "+releaseDateIMDB);
			}
		}
		catch(Exception e){
			System.out.println("Not able to fetch Release Date from IMDB");
		}
	}
	
	//*******************Fetching Release Date Value from Wiki*******************
	public void getReleaseDateWiki(String platform) throws Exception
	{
		try
		{
			JavascriptExecutor j = (JavascriptExecutor) driver;
			if(platform.equalsIgnoreCase("Wikipedia"))
			{
				j.executeScript("arguments[0].scrollIntoView();", LocatorsInImdbWikipedia.releaseDateInWikipedia);
				releaseDateWiki= LocatorsInImdbWikipedia.releaseDateInWikipedia.getText();
				System.out.println("Release Date Wikipedia: "+releaseDateWiki);
			}
		}catch(Exception e)
		{
			System.out.println("Not able to fetch Release Date from Wiki");
		}

	}
	
	//*******************Comparing Country values in IMDB and Wiki*******************
	public boolean compareValuesCountry()
	{
		System.out.println("IMDB Release Country: " +countryIMDB);
		System.out.println("Wiki Release Country: " +countryWiki);

		if((countryWiki.equalsIgnoreCase(countryIMDB)))
		{
			System.out.println("Country in IMDB and Wiki matched");
			return true;
		}
		else
		{
			System.out.println("Country in IMDB and Wiki did not match");
			return false;
		}
		
		
	}
	
	//***************Comparing Release Date values in IMDB and Wiki***************
	public boolean compareValuesReleaseDate()
	{
		System.out.println("IMDB Release Date: " +releaseDateIMDB);
		System.out.println("Wiki Release Date: " +releaseDateWiki);
		String[] dateIMDB=releaseDateIMDB.split(" ");
		String[] dateWiki=releaseDateWiki.split(" ");

		dateIMDB[1]=dateIMDB[1].substring(0, 2);
		
		
		
		dateWiki[2]=dateWiki[2].substring(0, 4);
		
		if((dateIMDB[0].equalsIgnoreCase(dateWiki[1]) && (dateIMDB[1].equalsIgnoreCase(dateWiki[0]) && (dateIMDB[2].equalsIgnoreCase(dateWiki[2])))))
		{
			System.out.println("Release Date in IMDB and Wiki matched");
			return true;
		}
		else
		{
			System.out.println("Release Date in IMDB and Wiki did not match");
			return false;
		}
		
		
	}
	
	


}
