package com.testVagrant.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.rmi.CORBA.Util;


public class BrowserSelection {

	public static WebDriver driver;
	//int flag;

	//Utilities
	Utilities utils = new Utilities();
	//Cross Browser
	public WebDriver invokeChromeBrowser() throws Exception {

		String driverPreference = utils.readProperties("browserPreference");
		

		if(driverPreference.equalsIgnoreCase("chrome")) {

			//Assign Chrome Browser
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver2.exe");
			driver = new ChromeDriver(options);
			

		}
		
		//Assign edge browser
		else if (driverPreference.equalsIgnoreCase("edge")) {
		
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		//Assign Firefox browser
		else if (driverPreference.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		else {
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		//Launch IMDB
		
		
		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

		return driver;
	}

}
