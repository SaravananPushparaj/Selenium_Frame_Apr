package Generic_Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Base_Class {
	
	public WebDriver driver;
	public static Logger log= Logger.getLogger(Base_Class.class);
	public static ExtentReports extentreport;
	public ExtentTest extenttest;
	
	Utility_Class c1= new Utility_Class();
	
	@BeforeMethod(groups={"smoke","regression"})
	public void Launchapp() throws IOException
	{
		
		String browsertype = c1.Reading_properties("btype");
		
		if(browsertype.equals("firefox"))
		{		
		driver= new FirefoxDriver();
		
		}
		else if(browsertype.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium_Testing\\chromedriv\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		else if(browsertype.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", "D:\\Selenium_Testing\\IEDriver\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();
		}
			
			
			
			
			
			
		driver.get(c1.Reading_properties("URL"));
		driver.manage().window().maximize();
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		
	}
	
	
	public String Capture_Screenshot(String TC_ID,String Order_Set) throws IOException
	{
		
		Date date= new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		String str= df.format(date)+".png";
		
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("D:\\Selenium_proj_Framework\\Screenshot\\"+TC_ID+"-"+Order_Set+"-"+str));
		
		String path="D:\\Selenium_proj_Framework\\Screenshot\\"+TC_ID+"-"+Order_Set+"-"+str;
		return path;
		
	}
	
	@BeforeSuite(groups={"smoke","regression"})
	public void Report_Extent()
	{
		Date date= new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String str2=df.format(date);
		
		extentreport= new ExtentReports("D:\\Selenium_proj_Framework\\Report\\"+"Booksrediff"+"-"+str2+".html",false);
		
	}
	
	
	
	
	@AfterMethod(groups={"smoke","regression"})
	public void tearDown()
	{
		driver.quit();
		
		extentreport.endTest(extenttest);
		extentreport.flush();
	}

}
