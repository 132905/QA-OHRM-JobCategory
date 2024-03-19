package ormJobCategories.tests;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

public class EditJobCategoryTest extends BaseTest {
	@Test
	public void testEditJobCategory() throws InterruptedException {
		loginPage.doLogin("Admin", "admin123");

		loginPage.clickAdminButton();
		Thread.sleep(2000);
		try {
		adminPage.editJobCategory( "abc","category111");
		}catch(NoSuchElementException e) {
			adminPage.addJobCategory("abc");
			adminPage.editJobCategory("abc","category111");
		}
	
	}

}
