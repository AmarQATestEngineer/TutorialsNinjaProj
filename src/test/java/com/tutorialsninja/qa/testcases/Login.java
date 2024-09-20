package com.tutorialsninja.qa.testcases;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.hc.core5.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	
	static WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		String browserName="chrome";
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\Desktop\\SeleniumProjects\\TutorialsNinjaProj\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();	
		} 
		else if(browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver","C:\\Users\\LENOVO\\Desktop\\SeleniumProjects\\TutorialsNinjaProj\\Drivers\\geckodriver.exe");
		    driver = new FirefoxDriver();
		}
		 else if(browserName.equals("edge")){
				System.setProperty("webdriver.edge.driver","C:\\Users\\LENOVO\\Desktop\\SeleniumProjects\\TutorialsNinjaProj\\Drivers\\msedgedriver.exe");
			    driver = new EdgeDriver();
			}
		 else if(browserName.equals("Safari")){
			 driver = new SafariDriver();
			}
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\Users\\LENOVO\\Desktop\\SeleniumProjects\\TutorialsNinjaProj\\Drivers\\chromedriver.exe"
		 * ); driver = new ChromeDriver();
		 */
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() {
		
		/*
		 * WebDriverManager.chromedriver().setup(); ChromeDriver driver=new
		 * ChromeDriver();
		 */
		
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("dfdddddddddddf@gmail.com");
		//password admin123
		
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("admin123");
		
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
		//Already aDded in @AfterMethod as tearDown because after assertion fails no code will run..but after method will run
		//driver.quit();
	}
	
	@Test(priority=2)
	public void verifyLoginWithInValidCredentials() {
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("actionthisclassis"+generateTimeStamp()+"notgood@gmail.com");
		//password admin123
		
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("5656565656");
		
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "This is test case failed");
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).isDisplayed());
		
		//driver.quit();
	}
	
	@Test(priority=3)
	public void verifyLoginWithValidPasswordAndInvalidEmail() {
		
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("actionthisclassis"+generateTimeStamp()+"notgood@gmail.com");
		//password admin123
		
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("admin123");
		
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "This is test case failed");
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).isDisplayed());
		
		//Already aDded in @AfterMethod as tearDown
		//driver.quit();
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("dfdddddddddddf@gmail.com");
		//password admin123
		
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("admin12563");
		
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "This is test case failed");
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).isDisplayed());
		
		//driver.quit();
	}
	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("");
		//password admin123
		
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("");
		
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "This is test case failed");
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).isDisplayed());
		
	}
	public String generateTimeStamp() {
		Date date=new Date();
		return date.toString().replace(" ", "_").replace(" ", "_");
	}

}
