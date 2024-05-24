package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public XLUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetName)throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		System.out.println("Rowcount is " + rowcount);
		
		workbook.close();
		System.out.println("Workbook is close ");
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetName, int rownum)throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		System.out.println("Cellcount is " + cellcount);
		workbook.close();
		fi.close();
		return 	cellcount;
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter=new DataFormatter();
		String data="";
		if(cell != null)
		{
			if(cell.getCellType() == CellType.NUMERIC)
			{
				BigDecimal cellValue = new BigDecimal(cell.toString());
				// get the double value of it
				double dblItemCode = cellValue.doubleValue();
				// or get a String in plain notation
				String strItemCode = cellValue.toPlainString();
				data=Double.toString(cell.getNumericCellValue());
			}
			else
			{
				data= cell.getStringCellValue();
			}
			//System.out.println("Value is " + data);
		}
		return data;
	}
}




