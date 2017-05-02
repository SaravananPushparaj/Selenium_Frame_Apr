package DataProvider_Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import Generic_Component.ExcelRW;

public class DataProvider_Loaddata {
	
	@DataProvider(name="dp_Invalidlogin")
	public static Iterator<Object[]> getInvalidlogindata() throws Exception
	{
		return commondp_logic("Login","InvalidLogin");
	}
	
	@DataProvider(name="dp_Validlogin")
	public static Iterator<Object[]> getValidlogindata() throws Exception
	{
		return commondp_logic("Login","ValidLogin");
	}
	
	
	@DataProvider(name="dp_InvalidSearch")
	public static Iterator<Object[]> getInvalidSearch() throws Exception
	{
		return commondp_logic("Search","InvalidSearch");
	}
	
	@DataProvider(name="dp_ValidSearch")
	public static Iterator<Object[]> getValidSearch() throws Exception
	{
		return commondp_logic("Search","ValidSearch");
	}
	
	@DataProvider(name="dp_AddCart")
	public static Iterator<Object[]> getAddCart() throws Exception
	{
		return commondp_logic("Cart","AddCart");
	}
	
	public static Iterator<Object[]> commondp_logic(String Sheetname,String sname) throws Exception
	{
		ExcelRW xl= new ExcelRW("D:\\Selenium_proj_Framework\\TestData\\TestData.xlsx");
		int RowCount = xl.getRow(Sheetname);
		int Columncount = xl.getColum(Sheetname);
		
		List<Object[]> arr_list=new ArrayList<Object[]>();
		
		for(int i=1;i<=RowCount;i++)
		{
			String Execute_Flag = xl.readCell(Sheetname, i, 2);
			String Script_name = xl.readCell(Sheetname, i, 3);
			
			if((Execute_Flag.equalsIgnoreCase("Y")) && (Script_name.equalsIgnoreCase(sname)))
			{
				Object[] x= new Object[1];
				Map<String,String> dMap= new HashMap<String,String>();
				
				for(int j=0;j<Columncount;j++)
				{
					String Skey = xl.readCell(Sheetname, 0, j);
					String Value = xl.readCell(Sheetname, i, j);
					
					dMap.put(Skey, Value);
				}
				
				x[0]=dMap;
				arr_list.add(x);
				
				
			}//end of if
			
			
			
		}//end of for
		
		
		
		return arr_list.iterator();
		
		
		
		
	}

}
