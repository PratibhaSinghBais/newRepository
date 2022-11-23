package com.testVagrant.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadInputData {

	public String searchMovie;


	public String readData(String sheetname) throws Exception
	{
		//location of file
		File src= new File(System.getProperty("user.dir")+"\\testData\\InputSheet.xlsx");

		FileInputStream fis = new FileInputStream(src);

		//loads the complete workbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		//to load a specific sheet
		XSSFSheet sheet1 = wb.getSheet(sheetname); 

		if(sheetname.equalsIgnoreCase("Movie"))
		{
			DataFormatter df = new DataFormatter();
			searchMovie= df.formatCellValue(sheet1.getRow(1).getCell(0));

			wb.close();
			return searchMovie;  
		}
		else
			return null;

	}


}


