package com.tutorialsninja.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.utils.Utilities;

public class Register {
	
	@Test(priority=1)
	public static void verifyRegisteringAccountWithMandatoryFields() {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\Desktop\\SeleniumProjects\\TutorialsNinjaProj\\Drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).clear();
		driver.findElement(By.id("input-firstname")).sendKeys("TomC");
		
		driver.findElement(By.id("input-lastname")).clear();
		driver.findElement(By.id("input-lastname")).sendKeys("Hanry");
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		
		driver.findElement(By.id("input-telephone")).clear();
		driver.findElement(By.id("input-telephone")).sendKeys("0076543210");
		

		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("12345");
		
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		
		driver.findElementByXPath("//input[@name='agree']").click();
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
	String actualSuccessMessage=driver.findElementByXPath("//div[@id='content']/h1").getText();
	Assert.assertEquals(actualSuccessMessage, "Your Account Has Been Created!", "The Account Creation is not displayed");
	
	driver.close();
	}
	

	@Test(priority=2)
	public static void verifyRegisteringAccountWithAllFields() {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\Desktop\\SeleniumProjects\\TutorialsNinjaProj\\Drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).clear();
		driver.findElement(By.id("input-firstname")).sendKeys("TomC");
		
		driver.findElement(By.id("input-lastname")).clear();
		driver.findElement(By.id("input-lastname")).sendKeys("Hanry");
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		
		driver.findElement(By.id("input-telephone")).clear();
		driver.findElement(By.id("input-telephone")).sendKeys("0076543210");
		

		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("12345");
		
		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		
		driver.findElementByXPath("//input[@name='newsletter'][@value='1']").click();
		
		driver.findElementByXPath("//input[@name='agree']").click();
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
	String actualSuccessMessage=driver.findElementByXPath("//div[@id='content']/h1").getText();
	Assert.assertEquals(actualSuccessMessage, "Your Account Has Been Created!", "The Account Creation is not displayed");
	
	driver.close();
	}
}
