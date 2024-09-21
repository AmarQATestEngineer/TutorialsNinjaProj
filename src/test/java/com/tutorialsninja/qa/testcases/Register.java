package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends Base {

	static WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver=initializeBrowserAndOpenApplicationURL("edge");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public static void verifyRegisteringAccountWithMandatoryFields() {

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

		driver.findElement(By.xpath("//input[@name='agree']")).click();

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualSuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessMessage, "Your Account Has Been Created!",
				"The Account Creation is not displayed");
	}

	@Test(priority = 2)
	public static void verifyRegisteringAccountWithAllFields() {

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

		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();

		driver.findElement(By.xpath("//input[@name='agree']")).click();

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualSuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessMessage, "Your Account Has Been Created!",
				"The Account Creation is not displayed");

	}

	@Test(priority = 3)
	public static void verifyRegisteringAccountWithExistingEmailAddress() {

		driver.findElement(By.id("input-firstname")).clear();
		driver.findElement(By.id("input-firstname")).sendKeys("TomC");

		driver.findElement(By.id("input-lastname")).clear();
		driver.findElement(By.id("input-lastname")).sendKeys("Hanry");

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("dfdddddddddddf@gmail.com");

		driver.findElement(By.id("input-telephone")).clear();
		driver.findElement(By.id("input-telephone")).sendKeys("0076543210");

		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys("12345");

		driver.findElement(By.id("input-confirm")).clear();
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		Assert.assertTrue(actualWarningMessage.contains("Warning: E-Mail Address is already registered!"),
				"Warning message for email didn't displayed");

	}

	@Test(priority = 4)
	public static void verifyRegisteringWithoutFillingAnyDetails() {

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String actualPrivacyPolicyMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		Assert.assertTrue(actualPrivacyPolicyMessage.contains("Warning: You must agree to the Privacy Policy!"),
				"The privacy policy message is not matched");

		String actualFirstNameMessage = driver
				.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertTrue(actualFirstNameMessage.contains("First Name must be between 1 and 32 characters!"),
				"The firstName message is not matched");

		String actualLastNameMessage = driver
				.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertTrue(actualLastNameMessage.contains("Last Name must be between 1 and 32 characters!"),
				"The lastname message is not matched");

		String actualEmailMessage = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div"))
				.getText();
		Assert.assertTrue(actualEmailMessage.contains("E-Mail Address does not appear to be valid!"),
				"The email message is not matched");

		String actualTelephoneMessage = driver
				.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertTrue(actualTelephoneMessage.contains("Telephone must be between 3 and 32 characters!"),
				"The Telephone message is not matched");

		String actualPasswordMessage = driver
				.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertTrue(actualPasswordMessage.contains("Password must be between 4 and 20 characters!"),
				"The password message is not matched");
	}
}
