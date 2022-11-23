package com.testVagrant.utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	static ExtentReports extent;
	
//***********Method to generate extend report***********	
	
	public static ExtentReports getReport()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date today = Calendar.getInstance().getTime();
		String date = dateFormat.format(today);
		String name = "report "+date;
		String path = System.getProperty("user.dir")+"\\ExtentReports\\"+name+".html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("TestVagrant Assignment Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Test Bot", "Mahima Jaiswal");
		
		return extent;
		
	}

}
