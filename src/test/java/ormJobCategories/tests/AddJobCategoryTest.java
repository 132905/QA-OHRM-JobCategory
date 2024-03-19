package ormJobCategories.tests;

import org.testng.annotations.Test;

public class AddJobCategoryTest extends BaseTest {

	@Test
	public void testAddJobCategory() throws InterruptedException {
		loginPage.doLogin("Admin", "admin123");

		loginPage.clickAdminButton();
		Thread.sleep(2000);

		adminPage.addJobCategory("Craf1223");
	}

}
