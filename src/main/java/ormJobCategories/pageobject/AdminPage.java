package ormJobCategories.pageobject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ormJobCategories.commonutils.CommonUtils;

public class AdminPage {
	private final WebDriver driver;
	private CommonUtils commonUtils;
	private LoginPage loginPage;

	public static void browserSetup(WebDriver driver) {

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}


	public AdminPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/span")
	private WebElement admnJobBtn;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[4]/a")
	private WebElement admnJbCatgryBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[1]/div/button")
	private WebElement addJbCatgryBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")
	private WebElement addJbCatgryNameFieldBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]")
	private WebElement saveJbCatgryBtn;

	//EDIT
	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")
	private WebElement editJbCatgryNameFieldBtn;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[2]/div/div/button")
	private WebElement deleteSelectBtn;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]")
	private WebElement yesBtn;
	

	

	public void addJobCategory(String jobCatgryName) throws InterruptedException {
		commonUtils.waitForElement(driver, admnJobBtn, 10);
		admnJobBtn.click();
		Thread.sleep(2000);
		commonUtils.waitForElement(driver, admnJbCatgryBtn, 10);
		admnJbCatgryBtn.click();
		Thread.sleep(1000);
		commonUtils.waitForElement(driver, addJbCatgryBtn, 10);
		addJbCatgryBtn.click();
		commonUtils.waitForElement(driver, addJbCatgryNameFieldBtn, 10);
		addJbCatgryNameFieldBtn.sendKeys(jobCatgryName);
		commonUtils.waitForElement(driver, saveJbCatgryBtn, 10);
		saveJbCatgryBtn.click();
		Thread.sleep(5000);
		
		try {
			WebElement alreadyExistsBtn = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/span"));
			commonUtils.waitForElement(driver, alreadyExistsBtn, 4);
//			if(alreadyExistsBtn != null) {
				String msg = alreadyExistsBtn.getText();
				if(msg.equals("Already exists")) {
						System.out.println("JOB CATEGORY ALREADY EXISTS");
//						System.exit(0);
//						loginPage.closeBrowser();
						driver.quit();
//		}
			}else {
				WebElement popBtn = driver.findElement(By.xpath("//*[@id=\"oxd-toaster_1\"]"));
				commonUtils.waitForElement(driver, popBtn, 10);
				popBtn.click();
				String popTxt = popBtn.getText();
				System.out.println("ADD JOB CATERORY STATUS :"+popTxt+" //end");
				
				if(popTxt.contains("Success")) {
					System.out.println("ADD JOB CATERORY STATUS :"+popTxt+" //end");
				}else {
					System.out.println("ALERT MESSAGE FROM ADD JOB CATEGORY PAGE :"+popTxt);
					System.exit(0);
				}
			}
		}catch (NoSuchElementException e) {
            // Catching the exception without performing any action
        } 
	}
	
	
	public void editJobCategory(String oldJobCatgryName, String newJobCatgryName) throws InterruptedException {
		commonUtils.waitForElement(driver, admnJobBtn, 10);
		admnJobBtn.click();
		Thread.sleep(2000);
		commonUtils.waitForElement(driver, admnJbCatgryBtn, 10);
		admnJbCatgryBtn.click();
		Thread.sleep(4000);
		String xpath = String.format("//div[contains(@class, 'oxd-table-row') and .//div[contains(text(), '%s')]]//button[contains(@class, 'oxd-icon-button')][2]" ,oldJobCatgryName);

 
        WebElement edit = driver.findElement(By.xpath(xpath));
        commonUtils.waitForElement(driver, edit, 10);
        edit.click();
		commonUtils.waitForElement(driver, editJbCatgryNameFieldBtn, 10);
		editJbCatgryNameFieldBtn.sendKeys(Keys.CONTROL + "a", Keys.ENTER); 
		editJbCatgryNameFieldBtn.sendKeys(newJobCatgryName);
		//Thread.sleep(1000);
		commonUtils.waitForElement(driver, saveJbCatgryBtn, 10);
		saveJbCatgryBtn.click();
		Thread.sleep(2000);
	}
	
	public void selectFrmDrpDwn(String string, WebElement btn) {
		CommonUtils.waitForElement(driver, btn, 2);
	    btn.click();
	    Actions actions = new Actions(driver);
	    if ( btn.isDisplayed() &&  btn.isEnabled()) {
	    	btn.sendKeys(Keys.ARROW_DOWN);
	    	while(! btn.getText().equals(string)) {
	    		actions.sendKeys(Keys.ARROW_DOWN).perform();
	    	}
	    	actions.sendKeys(Keys.ENTER).perform();
	    }
		
	}
	

	public void deleteJobCategory(String deleteValue) throws InterruptedException {
		commonUtils.waitForElement(driver, admnJobBtn, 10);
		admnJobBtn.click();
		Thread.sleep(2000);
		commonUtils.waitForElement(driver, admnJbCatgryBtn, 10);
		admnJbCatgryBtn.click();
		Thread.sleep(1000);
		String xpath = String.format(
				"//div[contains(text(), '%s')]/ancestor::div[contains(@class, 'oxd-table-row')]/descendant::span[contains(@class, 'oxd-checkbox-input')]/i[contains(@class, 'bi-check')]",
				deleteValue);

		WebElement checkbox = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);

		commonUtils.waitForElement(driver, deleteSelectBtn, 10);
		deleteSelectBtn.click();
		Thread.sleep(2000);
		commonUtils.waitForElement(driver, yesBtn, 10);
		yesBtn.click();
		Thread.sleep(2000);
	}
	
}


