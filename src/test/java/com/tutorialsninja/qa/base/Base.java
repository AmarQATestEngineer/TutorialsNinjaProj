package com.tutorialsninja.qa.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base {
	 
	public static WebDriver driver;
	
	public static WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {

		
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		
		return driver;
	}

}
