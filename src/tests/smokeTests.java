package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.Main;

public class smokeTests extends BaseTest {

	@Test(priority = 0)
	@Parameters({ "email", "email_message", "email_subject", "password", "url" })
	public void open_encrypted_email_test(String email, String email_message, String email_subject, String password, String url)
			throws InterruptedException {

		Main main = new Main(driver, wait);
		driver.get(url);
		main.login(email, password);
		main.click_email_subject(email_subject);
		main.click("unlock_message");
		main.switchToNewWindow();
		main.virtru_click_email(email);
		main.click("login_with_button");
		main.assertText("secure_message_contents", email_message);
	}

}
