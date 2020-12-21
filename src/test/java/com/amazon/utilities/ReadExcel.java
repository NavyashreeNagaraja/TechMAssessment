package com.amazon.utilities;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel 
{
	static Sheet ExcelWSheet;
	public void readExcel(String filePath,String fileName,String sheetName) throws IOException 
	{
	    File file = new File(filePath+"\\"+fileName);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook ExcelWBook = null;
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	    if(fileExtensionName.equals(".xlsx"))
	    {
	    	ExcelWBook  = new XSSFWorkbook(inputStream);
	    }
	    else if(fileExtensionName.equals(".xls"))
	    {
	    	ExcelWBook  = new HSSFWorkbook(inputStream);
	    }
	    ExcelWSheet = ExcelWBook.getSheet(sheetName);
	}

	public int getTotalNumberOfColumns()
	{
		   XSSFRow row = (XSSFRow) ExcelWSheet.getRow(0);
		    int TotalColumn_Number = row.getLastCellNum();
		    System.out.println("Total Number of Columns in the excel is : "+TotalColumn_Number);
		    return TotalColumn_Number;
	}
	public int getTotalNumberOfRows()
	{
		  XSSFRow row = (XSSFRow) ExcelWSheet.getRow(0);
		  int TotalRow_Number = ExcelWSheet.getLastRowNum()+1;
		  System.out.println("Total Number of Rows in the excel is : "+TotalRow_Number);
		  return TotalRow_Number;
	}

    public static String getCellData(int RowNum, int ColNum) throws Exception
    {
		try
		{
			XSSFCell Cell = (XSSFCell) ExcelWSheet.getRow(RowNum).getCell(ColNum);
		   String CellData = Cell.getStringCellValue();
		   return CellData;
		}
		catch (Exception e)
		{
			e.printStackTrace();		
		}
		return RowNum + "row " +  ColNum + " column "+ "does not exists";
		}
}
