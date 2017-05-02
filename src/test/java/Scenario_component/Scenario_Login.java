package Scenario_component;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import Generic_Component.Base_Class;
import PageObject_Component.Pf_Homepage;
import PageObject_Component.Pf_Signin;

public class Scenario_Login extends Base_Class {
	
	@Test(dataProvider="dp_Invalidlogin",dataProviderClass=DataProvider_Component.DataProvider_Loaddata.class,groups={"smoke"})
	public void testInvalidLogin(Map<String,String> login) throws IOException
	{
		SoftAssert sAssert= new SoftAssert();
		
		String Uname = login.get("Uname");
		String Pwd = login.get("Pwd");
		String Exp_Result = login.get("Exp_Result");
		String TC_ID = login.get("TC_ID");
		String Order_Set = login.get("Order_Set");
		
		log.info("Executing the Testcase " +TC_ID+ " Order set is "+Order_Set);
		
		extenttest=extentreport.startTest(TC_ID);		
		extenttest.log(LogStatus.PASS, "Executing the Testcase " +TC_ID+ " Order set is "+Order_Set);
		
		Pf_Homepage home_page= new Pf_Homepage(driver);
		home_page.Click_signin();
		
		Pf_Signin signin_page= new Pf_Signin(driver);
		signin_page.cl_login(Uname, Pwd);
		String Actual_Result = signin_page.getInvalidmsg();
		
				
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Actual Result is  " +Actual_Result+ " Expected Result is "+Exp_Result);
			extenttest.log(LogStatus.PASS, "Passed as Actual Result is  " +Actual_Result+ " Expected Result is "+Exp_Result,extenttest.addScreenCapture(Capture_Screenshot(TC_ID, Order_Set)));
		}
		else
		{
			log.info("Failed as Actual Result is  " +Actual_Result+ " Expected Result is "+Exp_Result);
			extenttest.log(LogStatus.FAIL, "Failed as Actual Result is  " +Actual_Result+ " Expected Result is "+Exp_Result,extenttest.addScreenCapture(Capture_Screenshot(TC_ID, Order_Set)));
			Capture_Screenshot(TC_ID, Order_Set);
			sAssert.fail("Failed as Actual Result is  " +Actual_Result+ " Expected Result is "+Exp_Result);
		}
		
		
		sAssert.assertAll();
		
	}
	
	
	@Test(dataProvider="dp_Validlogin",dataProviderClass=DataProvider_Component.DataProvider_Loaddata.class,groups={"regression"})
	public void testValidLogin(Map<String,String> login) throws IOException
	{
		SoftAssert sAssert= new SoftAssert();
		
		String Uname = login.get("Uname");
		String Pwd = login.get("Pwd");
		String Exp_Result = login.get("Exp_Result");
		String TC_ID = login.get("TC_ID");
		String Order_Set = login.get("Order_Set");
		
		log.info("Executing the Testcase " +TC_ID+ " Order set is "+Order_Set);
		Pf_Homepage home_page= new Pf_Homepage(driver);
		home_page.Click_signin();
		
		Pf_Signin signin_page= new Pf_Signin(driver);
		signin_page.cl_login(Uname, Pwd);
		String Actual_Result = home_page.getValidmsg();
		
		if(Actual_Result.contains(Exp_Result))
		{
			log.info("Passed as Actual Result is  " +Actual_Result+ " Expected Result is "+Exp_Result);
		}
		else
		{
			log.info("Failed as Actual Result is  " +Actual_Result+ " Expected Result is "+Exp_Result);
			Capture_Screenshot(TC_ID, Order_Set);
			sAssert.fail("Failed as Actual Result is  " +Actual_Result+ " Expected Result is "+Exp_Result);
		}
		
		
		home_page.Click_signout();
		sAssert.assertAll();
		
	}

}
