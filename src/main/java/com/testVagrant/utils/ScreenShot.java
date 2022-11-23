package com.testVagrant.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	
	
	WebDriver driver;
	public String screenshotPath;
	
	//Constructor
	public ScreenShot(WebDriver driver) {
		this.driver = driver;
	}
		
	
	//ScreenShot
	public void captureScreen() throws Exception {
			
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\ss " +timeStamp+ ".png";
			
		TakesScreenshot ScrObj = (TakesScreenshot) driver;
		File CaptureImg = ScrObj.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(CaptureImg, new File(screenshotPath));
	}

}
