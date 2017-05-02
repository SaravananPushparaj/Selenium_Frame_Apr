package PageObject_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pf_Productdetails {
	
	
	@FindBy(className="buynowbtnbig")
	public WebElement btn_buynow;
	
	
	
	public Pf_Productdetails(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}
	
	
	public void Click_Buynow()
	{
		btn_buynow.click();
	}

}
