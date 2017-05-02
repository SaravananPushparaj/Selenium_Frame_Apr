package PageObject_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pf_Homepage {
	
	//2nd section
	@FindBy(linkText="Sign In")
	public WebElement lnk_signin;
	
		
	@FindBy(className="proper")
	public WebElement valid_msg;
	
	@FindBy(partialLinkText="Out")
	public WebElement lnk_signout;
	
	@FindBy(id="srchword")
	public WebElement txtbox_Search;
	
	
	@FindBy(className="newsrchbtn")
	public WebElement btn_Search;
	
	
	@FindBy(className="sorrymsg")
	public WebElement Invalid_searchmsg;
	
	@FindBy(id="find")
	public WebElement Valid_searchmsg;
	
	@FindBy(xpath="//div[@class='bookbox'][1]//img")
	public WebElement img_book;
	//1st section
	
	public Pf_Homepage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		
	}
	
	
	public void Click_signin()
	{
		lnk_signin.click();
	}
	
	public String getValidmsg()
	{
		return valid_msg.getText();
	}
	
	public void Click_signout()
	{
		lnk_signout.click();
	}
	
	public void cl_Search(String Search_Item)
	{
		txtbox_Search.sendKeys(Search_Item);
		btn_Search.click();
		
	}
	
	public String getInvalidSearch()
	{
		return Invalid_searchmsg.getText();
	}
	

	public String getValidSearch()
	{
		return Valid_searchmsg.getText();
	}
	
	public void Click_Book()
	{
		img_book.click();
	}
}
