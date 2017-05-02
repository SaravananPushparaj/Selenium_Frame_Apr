package PageObject_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pf_Cart {
	
	@FindBy(xpath="//span[contains(text(),'Flood and Fire')]")
	public WebElement AddCart_msg;
	
	@FindBy(id="frmCart")
	public WebElement frm_cart;
	
	
	public Pf_Cart(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}
	
	public String getAddCartmsg()
	{
		return AddCart_msg.getText();
	}

}
