package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
	
	
	@DataProvider(name="LoginData")
	public String[][] getLoginData() throws IOException
	{
		System.out.println("start Get Login Data");
		
		System.out.println(path);
		XLUtility x1 = new XLUtility(path);
		
		int rownum=x1.getRowCount("Login");
		int colcount=x1.getCellCount("Login",1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for (int i=1;i<=rownum;i++)
			{
				for (int j=0;j<colcount;j++)
				 {
					apidata[i-1][j]=x1.getCellData("Login",i, j);
				 }
			}
		return apidata;
	}	
	
	
			
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		
		XLUtility x1=new XLUtility(path);
		
		int rownum = 2;        //x1.getRowCount("UpdateUser");
		int colcount=x1.getCellCount("PostUser",1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for (int i=1;i<=rownum;i++)
			{
				for (int j=0;j<colcount;j++)
				 {
					apidata[i-1][j]=x1.getCellData("PostUser",i, j);
				 }
			}
		return apidata;
	}
	
	
	
	
	@DataProvider(name="UserNames")
	public String[] getuserNames() throws IOException
	{
		
		XLUtility x1=new XLUtility(path);
		
		int rownum=2;//x1.getRowCount("UpdateUser");
		
		String apidata[]=new String[rownum];
		
		for (int i=1;i<=rownum;i++)
			{
				
					apidata[i-1]=x1.getCellData("PostUser",i, 0);
				
			}
		return apidata;
	}
	
	
	
	@DataProvider(name="UpdateData")
	public String[][] getAllUpdateData() throws IOException
	{
		
		XLUtility x1=new XLUtility(path);
		
		int rownum=2;//x1.getRowCount("UpdateUser");
		int colcount=x1.getCellCount("UpdateUser",1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for (int i=1;i<=rownum;i++)
			{
				for (int j=0;j<colcount;j++)
				 {
					apidata[i-1][j]=x1.getCellData("UpdateUser",i, j);
				 }
			}
		return apidata;
	}	
	
	
}




