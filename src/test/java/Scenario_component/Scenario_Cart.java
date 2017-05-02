package Scenario_component;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Generic_Component.Base_Class;
import PageObject_Component.Pf_Cart;
import PageObject_Component.Pf_Homepage;
import PageObject_Component.Pf_Productdetails;

public class Scenario_Cart extends Base_Class {
	
	
	@Test(dataProvider="dp_AddCart",dataProviderClass=DataProvider_Component.DataProvider_Loaddata.class)
	public void testAddCart(Map<String, String> Cart) throws IOException
	{
		SoftAssert sAssert= new SoftAssert();
		
		String Search_Item = Cart.get("Search_Item");
		String Exp_Result = Cart.get("Exp_Result");
		String TC_ID = Cart.get("TC_ID");
		String Order_Set = Cart.get("Order_Set");
		
		log.info("Executing the Testcase " +TC_ID +" Order set is "+Order_Set);
		
		Pf_Homepage home_page= new Pf_Homepage(driver);
		home_page.cl_Search(Search_Item);
		
		//explicit wait
		WebDriverWait wait= new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(home_page.Valid_searchmsg));
		
		home_page.Click_Book();
		
		Pf_Productdetails product_details= new Pf_Productdetails(driver);
		product_details.Click_Buynow();
		
		
		
		Pf_Cart cart_page= new Pf_Cart(driver);
		
		driver.switchTo().frame(cart_page.frm_cart);
		String Actual_Result = cart_page.getAddCartmsg();
		
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Actual Result is "+Actual_Result+" Expected Result is "+Exp_Result);
		}
		else
		{
			log.info("Failed as Actual Result is "+Actual_Result+" Expected Result is "+Exp_Result);
			Capture_Screenshot(TC_ID, Order_Set);
			sAssert.fail("Failed as Actual Result is "+Actual_Result+" Expected Result is "+Exp_Result);
			
		}
		
		
		
		sAssert.assertAll();
	}

}
