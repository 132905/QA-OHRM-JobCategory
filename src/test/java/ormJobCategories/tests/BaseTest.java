package ormJobCategories.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ormJobCategories.commonutils.CommonUtils;
import ormJobCategories.pageobject.AdminPage;
import ormJobCategories.pageobject.LoginPage;

public class BaseTest {
	protected WebDriver driver;
	protected LoginPage loginPage;
	protected AdminPage adminPage;
	

	@BeforeMethod
	public void setUp() throws IOException {
		driver = CommonUtils.createDriver(LoginPage.loadProperties());
		loginPage = new LoginPage(driver);
		adminPage = new AdminPage(driver);
		
		loginPage.browserSetup(driver);
		adminPage.browserSetup(driver);
		
	}

	@AfterMethod
	public void tearDown() {

		loginPage.closeBrowser();
	}

}
