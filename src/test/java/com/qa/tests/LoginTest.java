package com.qa.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	
	WebDriver driver;
	
	By username=By.id("username");
	By password=By.id("password");
	By login=By.id("loginBtn");
	By signUp=By.linkText("Sign up");
	
	By homePageHeader=By.xpath("//h1[@class='private-page__title']");
	
	// HA and SA
	// Assert vs verify
	
	SoftAssert softAssert;
	@BeforeMethod
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	//	driver.manage().window().fullscreen();
	 driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://app.hubspot.com");
		softAssert=new SoftAssert();
	}
	
	@Test(priority=1)
	public void loginPageTest()
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains("HubSpot Login"));

		String title = driver.getTitle();
		System.out.println(" page title is : " + title);

		if (title.equals("HubSpot Login")) {
			System.out.println("Pass");
		} else {
			System.out.println("FAIL");
		}
	}
	
	@Test(priority=2)
	public void signUpLinkTest()
	{
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("Sign up")));
		
	boolean flag =	driver.findElement(By.linkText("Sign up")).isDisplayed();
	Assert.assertEquals(flag, true," Sign up is not displayed");
	
	Assert.assertTrue(flag);
	}
	
	
	@Test(priority=3,enabled=false)
	public void loginTest()
	{
		driver.findElement(username).sendKeys("arjunkumayan18@gmail.com");
		driver.findElement(password).sendKeys("Defence@5050");
		driver.findElement(login).click();

		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePageHeader));
	
		
		String homePageHeaderVal=driver.findElement(homePageHeader).getText();
	    Assert.assertEquals(homePageHeaderVal, "Sales Dashboard"," Home page header is not displayed ");
	    Assert.assertTrue(homePageHeaderVal.contains("Sales Dashboard"));
	
	}

	@Test(priority=3)
	public void login1Test()
	{
		driver.findElement(username).sendKeys("arjunkumayan18@gmail.com");
		driver.findElement(password).sendKeys("Defence@5050");
		driver.findElement(login).click();

		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePageHeader));
	
		
		String homePageHeaderVal=driver.findElement(homePageHeader).getText();
		//softAssert.assertEquals(homePageHeaderVal, "Sales Dashboard1"," Home page header is not displayed ");
		softAssert.assertTrue(homePageHeaderVal.contains("Sales Dashboard"));
	
		softAssert.assertEquals(driver.getTitle(), "Reports dashboard"," home page title test");
	
		softAssert.assertAll();
	}
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
