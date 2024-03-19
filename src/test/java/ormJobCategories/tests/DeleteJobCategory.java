package ormJobCategories.tests;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

public class DeleteJobCategory extends BaseTest {
	@Test
	public void testDeleteJobCategory() throws InterruptedException {
		loginPage.doLogin("Admin", "admin123");

		loginPage.clickAdminButton();
		Thread.sleep(2000);

		
		try {
			adminPage.deleteJobCategory("Craft Workers");
			}catch(NoSuchElementException e) {
				adminPage.addJobCategory("Craft Workers");
				adminPage.deleteJobCategory("Craft Workers");
			}
	}
}
