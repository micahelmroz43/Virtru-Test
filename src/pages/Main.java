package pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main extends BasePage {

	public Main(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	String sign_in_text = "//div[@jsname='paFcre']/div/h1[text()[contains(.,'Sign in')]]";
	String email_textbox = "//div[1]/div/div[1]/input[@name='identifier']";
	String password_textbox = "//div[1]/div/div[1]/input[@name='password']";
	String login_next_button = "//span[text()[contains(.,'Next')]]";
	String login_with_button = "//a[text()[contains(.,'Login With')]]";
	String compose_button = "//div/div/div/div[1]/div/div[text()[contains(.,'COMPOSE')]]";
	String unlock_message = "//td/table/tbody/tr/td/a[starts-with(@data-saferedirecturl, 'https://www.google.com/url?hl=en&q=https://secure.virtru.com')]";
	String secure_message_contents = "//div[3]/div/span/div/a";

	public void login(String email, String password) throws InterruptedException {

		writeText("email_textbox", email);
		click("login_next_button");
		writeText("password_textbox", password);
		click("login_next_button");
	}

	public void click(String element) throws InterruptedException {
		Actions actions = new Actions(driver);
		String locator = null;

		switch (element) {

		case "email_textbox":
			locator = email_textbox;
			break;

		case "login_next_button":
			locator = login_next_button;
			break;

		case "login_with_button":
			locator = login_with_button;
			break;

		case "password_textbox":
			locator = password_textbox;
			break;

		case "unlock_message":
			locator = unlock_message;
			break;

		default:
			break;
		}

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

		WebElement button = driver.findElement(By.xpath(locator));
		actions.moveToElement(button).click().perform();

	}

	public void click_email_subject(String locator) throws InterruptedException {
		Actions actions = new Actions(driver);

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(
				By.xpath("//table/tbody/tr/td[6]/div/div/div/span[text()[contains(.,'" + locator + "')]]")));

		WebElement button = driver.findElement(
				By.xpath("//table/tbody/tr/td[6]/div/div/div/span[text()[contains(.,'" + locator + "')]]"));
		actions.moveToElement(button).click().perform();

	}

	public void virtru_click_email(String locator) throws InterruptedException {
		Actions actions = new Actions(driver);

		// Sometimes a blank page appears, this should
		// keep the test moving.
		String actualTitle = driver.getTitle();
		String expectedTitle = "Secure Reader | Virtru";

		if (actualTitle != expectedTitle) {
			driver.navigate().refresh();
		}

		(new WebDriverWait(driver, 10)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[3]/a[1]/div/span[text()[contains(.,'" + locator + "')]]")));

		WebElement button = driver
				.findElement(By.xpath("//div[3]/a[1]/div/span[text()[contains(.,'" + locator + "')]]"));
		actions.moveToElement(button).click().perform();

	}

	public void assertText(String locator, String text) throws InterruptedException {
		new Actions(driver);
		switch (locator) {

		case "secure_message_contents":
			locator = secure_message_contents;
			break;

		default:
			break;
		}
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

		String actualText = driver.findElement(By.xpath(locator)).getText();
		assertTrue(actualText.contains(text));
	}

	public void writeText(String element, String text) throws InterruptedException {
		String locator = null;

		switch (element) {

		case "email_textbox":
			locator = email_textbox;
			break;

		case "password_textbox":
			locator = password_textbox;
			break;

		default:
			break;
		}
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

		driver.findElement(By.xpath(locator)).sendKeys(text);
	}

	public void switchToNewWindow() {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}

}
