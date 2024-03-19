package ormJobCategories.pageobject;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ormJobCategories.commonutils.CommonUtils;

public class LoginPage {

	private WebDriver driver;
	private CommonUtils commonUtils;

	public static String loadProperties() throws IOException {
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("src/main/resources/config.properties");
		properties.load(file);
		String browser = properties.getProperty("browser");
		return browser;

	}

	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void browserSetup(WebDriver driver)

	{

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

	}

	@FindBy(how = How.CSS, using = "[name='username']")

	private WebElement usernameElement;

	@FindBy(how = How.CSS, using = "[name='password']")

	private WebElement passwordElement;

	@FindBy(how = How.XPATH, using = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")

	private WebElement loginButton;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a")

	private WebElement adminButton;

	public LoginPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	public void doLogin(String username, String password) {
		commonUtils.waitForElement(driver, usernameElement, 2);
		usernameElement.sendKeys(username);
		commonUtils.waitForElement(driver, passwordElement, 2);
		passwordElement.sendKeys(password);
		commonUtils.waitForElement(driver, loginButton, 2);
		loginButton.click();
	}

	public void clickAdminButton() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(adminButton));

		adminButton.click();

	}

	public boolean isLoginSuccessful() {

		return driver.getCurrentUrl().equals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");

	}

}
