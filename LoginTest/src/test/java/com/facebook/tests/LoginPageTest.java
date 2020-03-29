package com.facebook.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest {

WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vikash\\Desktop\\LidoLearning\\SeleniumJarFiles\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/login/");
	}
	
	@Test(priority=1)
	// public void logoCheckTest() {
	//	boolean flag = driver.findElement(By.xpath("")).isDisplayed();
	//	Assert.assertTrue(flag);
	// }
	public void titleCheckTest() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Log in to Facebook | Facebook");
	}
	
	@Test(priority=2)
	public void userLoginTest() {
		
		driver.findElement(By.xpath("//div[@id='loginform']//input[@id='email']")).sendKeys("kumarv.rana@gmail.com");
		driver.findElement(By.xpath("//div[@id='loginform']//input[@id='pass']")).sendKeys("xxxxxxxxxxxxxx");
		driver.findElement(By.xpath("//div[@id='loginform']//button[@id='loginbutton']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//div[@id='u_0_c']")).click();
	}
	
	@Test(priority=3)
	public void invalidUserLoginTest() {
		
		driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String url = driver.getCurrentUrl();
		driver.navigate().to("https://www.facebook.com/login/");
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("kumarv.rana@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456#");
		// System.out.println(url);
		driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
		Assert.assertEquals(url, "https://www.facebook.com/login/");
	}
	
	@Test(priority=4)
	public void userRegisterTest() {
		driver.findElement(By.xpath("//div[@class='signup_box clearfix']//a")).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.navigate().back();
		driver.findElement(By.xpath("//div[@id='login_link']//a[@id='reg-link']")).click();
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Vikash");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Rana");
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("ranaviki163@gmail.com");
		driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).sendKeys("ranaviki163@gmail.com");
		driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys("lido@1234");
		
		Select selDay = new Select(driver.findElement(By.xpath("//select[@id='day']")));
		selDay.selectByValue("4");
		
		Select selMonth = new Select(driver.findElement(By.xpath("//select[@id='month']")));
		selMonth.selectByVisibleText("Mar");
		
		Select selYear = new Select(driver.findElement(By.xpath("//select[@id='year']")));
		selYear.selectByValue("1996");
		
		driver.findElement(By.id("u_0_7")).click();
		driver.findElement(By.id("u_0_13")).click();
	}
	
	@Test(priority=5)
	public void forgetPasswordTest() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@id='login_link']//a[@id='forgot-password-link']")).click();
		driver.findElement(By.xpath("//div[@class='identify_yourself_block']//input")).sendKeys("kumarv.rana@gmail.com");
		driver.findElement(By.xpath("//label[@id='did_submit']")).click();
		driver.findElement(By.xpath("//button[@name='reset_action']")).click();
	}
	
	@Test(priority=6)
	public void footerLinkTest() {
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'Sign Up')]")).click();
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("kumarv.rana@gmail.com");
		driver.findElement(By.xpath("//a[contains(text(),'Log In')]")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("kumarv.rana@gmail.com");
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
}
