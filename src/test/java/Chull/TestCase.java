package Chull;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://chull.tv/");

	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Test Case 1: Login with phone number
	@Test
	public void loginWithPhoneNumber() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
		Thread.sleep(4000);
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();
	}

	// Test Case 2: Filter button click
	@Test
	public void filterButtonClickTest() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('.dropdown-menu').scrollBy(0,100)");
		WebElement button = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary mt-2']")));
		button.click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://aaryaadigital.com/LanguageData/26", "URL mismatch");
	}

	// Test Case 3: Profile update
	@Test
	public void updateProfileTest() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
		Thread.sleep(4000);
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();
		Thread.sleep(4000);

		driver.findElement(By.xpath("//a[@href='/Profile']")).click();

		WebElement editBtn = driver.findElement(By.xpath("//button[@class=\"edit-button\"]"));
		editBtn.click();
		WebElement input = driver.findElement(By.xpath("//input[@class='input-name']"));
		input.clear();
		input.sendKeys("Hey this update message");
		driver.findElement(By.xpath("//button[@class='edit-button']")).click();
	}

	// Test Case 4: Verify current URL
	@Test
	public void verifyCurrentUrlTest() {
		String url = driver.getCurrentUrl();

		String expected = "https://chull.tv/";
		Assert.assertEquals(url, expected);

		if (url.equals(expected)) {
			System.out.println("True ");
		} else {
			System.out.println("url mismatch");
		}
	}

	// Test Case 5: Subscription check
	/*
	 * @Test
	 * 
	 * public void subscriptionTest() { WebElement subscriptionIcon =
	 * driver.findElement(By.xpath("//img[@class='subscription-logo']"));
	 * subscriptionIcon.click(); WebElement alertBox =
	 * driver.findElement(By.xpath("//div[@class='alert alert-success']"));
	 * Assert.assertTrue(alertBox.isDisplayed(),
	 * "Subscription message not displayed"); }
	 */

	// Test Case 6: Search video
	@Test
	public void searchVideoTest() throws InterruptedException {

		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
		Thread.sleep(4000);
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();
		Thread.sleep(4000);

		driver.findElement(By.xpath("//a[@href='/search']")).click();
		Thread.sleep(3000);
		WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));
		searchBox.sendKeys("Love kills", Keys.ENTER);
		Thread.sleep(3000);
		WebElement checkonvideo = driver.findElement(By.xpath("//img[@class=\"img-top\"]"));
		if (checkonvideo.isDisplayed()) {
			System.out.println("Video is visible");
		} else {
			System.out.println("video is not displayed");
		}

	}

	// Test Case 7: Wrong credentials test
	@Test
	public void wrongOtpLoginTest() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.name("otp")).sendKeys("1235");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[normalize-space(text())=\"Verify OTP\"]")).click();

		String buttonText = driver.findElement(By.xpath("//button[@type='submit']")).getText();
		Assert.assertNotEquals(buttonText, "Incorrect OTP entered. Please enter again.");
		System.out.println(buttonText);
	}

	// Test Case 8: Play video

	@Test
	public void playVideoTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Step 1: Login
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		WebElement enterPhone = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Mobile Number']")));
		enterPhone.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();

		WebElement otpBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='otp']")));
		otpBox.sendKeys("1234");

		WebElement verifyOtp = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Verify OTP']")));
		verifyOtp.click();

		// Step 2: Search
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/search']"))).click();
		WebElement searchBox = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		searchBox.sendKeys("Love kills", Keys.ENTER);

		WebElement videoThumb = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Love kills']")));
		videoThumb.click();

		// Step 3: Watch Video
		WebElement watchVideo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'sc-fUnMCh')]")));
		watchVideo.click();
		Thread.sleep(5000);

		// Step 4: Close popup if exists
		try {
			WebElement popup = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='cancelbtn']")));
			popup.click();
			System.out.println("Popup closed.");
		} catch (TimeoutException e) {
			System.out.println("No popup found.");
		}

		// Step 5: Detect player iframe
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Total iframes found: " + iframes.size());

		WebElement playerFrame = null;
		for (WebElement iframe : iframes) {
			String src = iframe.getAttribute("src");
			System.out.println("Iframe src: " + src);
			if (src != null && src.contains("chull.tv/player.html")) {
				playerFrame = iframe;
				break;
			}
		}

		if (playerFrame == null) {
			System.out.println("Player iframe not found.");
			return;
		}

		// Step 6: Switch to iframe
		try {
			driver.switchTo().frame(playerFrame);
			System.out.println("Switched to iframe: " + playerFrame.getAttribute("src"));
		} catch (Exception e) {
			System.out.println("Unable to switch to iframe. Possibly cross-origin.");
			return;
		}

		// Step 7: Wait for video or play control
		try {
			wait.until(driver1 -> ((JavascriptExecutor) driver1).executeScript(
					"return document.querySelector('video') != null || document.querySelector('.vjs-play-control') != null"));
			System.out.println("Video or play control found in iframe.");
		} catch (TimeoutException e) {
			System.out.println("No video or play control found inside iframe.");
			driver.switchTo().defaultContent();
			return;
		}

		// Step 8: Attempt to play the video via JS
		try {
			((JavascriptExecutor) driver).executeScript("var video = document.querySelector('video');" + "if (video) {"
					+ "  video.muted = true;" + "  video.play();" + "  console.log('HTML5 video playback started');"
					+ "} else {" + "  var playBtn = document.querySelector('.vjs-play-control, [aria-label=\"Play\"]');"
					+ "  if (playBtn) playBtn.click();" + "  console.log('Clicked custom play button');" + "}");
			System.out.println("Playback script executed.");
		} catch (Exception e) {
			System.out.println("Playback script failed: " + e.getMessage());
		}

		// Step 9: Switch back to main content
		driver.switchTo().defaultContent();
		System.out.println("Switched back to main content.");
	}

	// Test Case 9: Click footer links
	@Test
	public void footerTermsAndConditionsTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		// Login Flow
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mobile Number']")))
				.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='otp']"))).sendKeys("1234");

		WebElement clickOnSendOtp = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Verify OTP']")));
		clickOnSendOtp.click();

		// Wait until page is loaded
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("footer")));

		// Scroll to bottom
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		// Wait for terms link to be visible and clickable
		WebElement termsLink = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Terms and Conditions']")));
		js.executeScript("arguments[0].scrollIntoView(true);", termsLink);
		termsLink.click();

		// Wait a bit to ensure iframe or new tab is loaded
		Thread.sleep(2000); // Optional: use explicit wait if there's a proper condition

		// ==== DEBUGGING: Print all iframe IDs ====
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Total iframes: " + iframes.size());
		for (WebElement iframe : iframes) {
			System.out.println("iframe ID: " + iframe.getAttribute("id"));
			System.out.println("iframe SRC: " + iframe.getAttribute("src"));
		}

		// Try switching to first available iframe as fallback
		if (!iframes.isEmpty()) {
			driver.switchTo().frame(iframes.get(0)); // You can refine this by matching ID/src if needed

			// Now interact inside the frame
			WebElement termsHeader = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='container']")));
			if (termsHeader.isDisplayed()) {
				System.out.println("Element is visible");
			} else {
				System.out.println("Data Not Displayed");
			}

			// Switch back to default content after checking
			driver.switchTo().defaultContent();
		} else {
			System.out.println("❌ No iframe found. Terms and Conditions might be in a new tab.");

			// Optional: handle new window/tab
			String originalWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for (String window : allWindows) {
				if (!window.equals(originalWindow)) {
					driver.switchTo().window(window);
					break;
				}
			}

			// Now try to find the element in new tab
			WebElement termsHeader = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='container']")));
			if (termsHeader.isDisplayed()) {
				System.out.println("✅ Element visible in new tab.");
			} else {
				System.out.println("❌ Data Not Displayed in new tab.");
			}

			// Optional: close the new tab and return to original
			driver.close();
			driver.switchTo().window(originalWindow);
		}
	}

	// Test Case 10: Without login detailed page navigation
	@Test
	public void detailedPageWithoutLoginTest() {

		WebElement devotionalTab = driver.findElement(By.xpath("//a[@href='/Devotional/16']"));
		devotionalTab.click();
		WebElement videoElement = driver
				.findElement(By.xpath("//div[@class='scroll-container']//div[@id='custom-div-2']"));
		videoElement.click();

		String videoTitle = driver.findElement(By.xpath("//h3[@class='detailHeading']")).getText();
		Assert.assertEquals(videoTitle, "Ahiya Maiya Pujwa Ke Beriya", "Video title mismatch");
	}

	// Test Case 11: Login with mail
	@Test
	public void loginWithMailTest() throws InterruptedException {

		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[normalize-space(.)=\"Login with Email\"]")).click();

		driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
		driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
		driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
		driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();
		// Assert here on the result of OTP validation
	}

	// Test Case 12: Logout
	@Test
	public void logoutTest() throws InterruptedException {
		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
		Thread.sleep(4000);
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();

		// driver.findElement(By.xpath("(//img[@class='Movieslogo'])[8]")).click();
		WebElement Clickonlogout = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[8]")));
		Clickonlogout.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='removebtn']"))).click();

	}

	// Test Case 13: Buy or rent video
	@Test
	public void buyRentVideoTest() {

		driver.findElement(By.xpath("//div[@class='dropdown'][2]")).click();
		driver.findElement(By.xpath("//div[@class='dropdown-menu show']//a[1]")).click();
		driver.findElement(By.xpath("//div[@class='card movie-card']")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");

		WebElement watchNow = driver.findElement(By.xpath("//button[contains(@class,'goYXOV')]"));
		watchNow.click();
		// Add assertion if needed
	}

	// Test Case 14: View all button
	@Test
	public void viewAllTest() throws InterruptedException {
		driver.get("https://chull.tv/"); // Always good to ensure you’re on the right page

		// Click login
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		Thread.sleep(2000);

		// Enter mobile number
		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
		enterphoneNumber.sendKeys("8920689888");

		// Click Send OTP
		driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();

		// Enter OTP
		driver.findElement(By.xpath("//input[@name='otp']")).sendKeys("1234");
		Thread.sleep(4000);

		// Click Verify OTP
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();

		// Wait for navigation or home screen to load fully
		Thread.sleep(3000); // Adjust depending on speed or replace with proper wait

		// Wait until the "Top 10 in India" link is present and clickable
		try {
			WebElement top10Link = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'free-episode')]")));
			top10Link.click();
		} catch (TimeoutException e) {
			System.out.println("Top 10 link not clickable: " + e.getMessage());
			
		}

		// Now check if the view-list div is visible
		WebElement checkDataVisible = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'view-list')]")));
		boolean check = checkDataVisible.isDisplayed();

		System.out.println("Is Top 10 content visible: " + check);
		Thread.sleep(2000);
	}

	// Test Case 15: Play Store button
	@Test
	public void playStoreButtonTest() throws InterruptedException {

		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
		Thread.sleep(4000);
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		WebElement playStoreLink = driver.findElement(By.xpath("//div[@class='icon']//a[1]"));
		playStoreLink.click();

		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();

		for (String win : allWindows) {
			if (!win.equals(mainWindow)) {
				driver.switchTo().window(win);
				break;
			}
		}

		String newUrl = driver.getCurrentUrl();
		Assert.assertTrue(newUrl.contains("google.com"), "Not redirected to Play Store");

		driver.close();
		driver.switchTo().window(mainWindow);
	}

	// Test Case 16: Filter with values
	@Test
	public void filterValuesTest() throws InterruptedException {

		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
		Thread.sleep(4000);
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();

		// WebElement dropdown =
		// driver.findElement(By.xpath("//div[@class='dropdown'][1]"));
		// dropdown.click();
		Thread.sleep(4000);
		WebElement ClickonfilterButton = driver.findElement(By.xpath("(//img[@class='Movieslogo'])[4]"));
		ClickonfilterButton.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class=\"card\"])[4]"))).click();

		Thread.sleep(6000);

		String url = driver.getCurrentUrl();
		System.out.println(url);

	}

	@Test
	public void watchlist() throws InterruptedException {

		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
		Thread.sleep(4000);
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();

		driver.findElement(By.xpath("//a[@href='/search']")).click();
		Thread.sleep(3000);
		WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));
		searchBox.sendKeys("Hello pooja", Keys.ENTER);
		Thread.sleep(3000);

		WebElement clickonVideo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Hello Pooja']")));
		clickonVideo.click();

		WebElement checkvideoIsalreadyinwatchlist = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root watchlist-container']")));
		if (checkvideoIsalreadyinwatchlist.isDisplayed()) {
			System.out.println("Video is already Added in the watchlist");
			WebElement clickonwatchlist = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='WatchList']")));
			clickonwatchlist.click();
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Watchlist']"))).click();

		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Watchlist']"))).click();

	}

	@Test
	public void checkRelatedvideo() throws InterruptedException {

		driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
		Thread.sleep(2000);

		WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
		enterphoneNumber.sendKeys("8920689888");

		driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
		Thread.sleep(4000);
		WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
		Thread.sleep(2000);
		clickOnSendOtp.click();

		driver.findElement(By.xpath("//a[@href='/search']")).click();
		Thread.sleep(3000);
		WebElement searchBox = driver.findElement(By.xpath("//input[@type='text']"));
		searchBox.sendKeys("Hello pooja");
		WebElement ClickOnvideo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Hello Pooja']")));
		ClickOnvideo.click();

		Thread.sleep(10000);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)");
		Thread.sleep(3000);

		WebElement clickonvideo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class=\"img-top\"]")));
		clickonvideo.click();

		WebElement watchVideo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'sc-fUnMCh')]")));
		watchVideo.click();
		Thread.sleep(5000);

		// Step 4: Close popup if exists
		try {
			WebElement popup = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='cancelbtn']")));
			popup.click();
			System.out.println("Popup closed.");
		} catch (TimeoutException e) {
			System.out.println("No popup found.");
		}

		// Step 5: Detect player iframe
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Total iframes found: " + iframes.size());

		WebElement playerFrame = null;
		for (WebElement iframe : iframes) {
			String src = iframe.getAttribute("src");
			System.out.println("Iframe src: " + src);
			if (src != null && src.contains("chull.tv/player.html")) {
				playerFrame = iframe;
				break;
			}
		}

		if (playerFrame == null) {
			System.out.println("Player iframe not found.");
			return;
		}

		// Step 6: Switch to iframe
		try {
			driver.switchTo().frame(playerFrame);
			System.out.println("Switched to iframe: " + playerFrame.getAttribute("src"));
		} catch (Exception e) {
			System.out.println("Unable to switch to iframe. Possibly cross-origin.");
			return;
		}

		// Step 7: Wait for video or play control
		try {
			wait.until(driver1 -> ((JavascriptExecutor) driver1).executeScript(
					"return document.querySelector('video') != null || document.querySelector('.vjs-play-control') != null"));
			System.out.println("Video or play control found in iframe.");
		} catch (TimeoutException e) {
			System.out.println("No video or play control found inside iframe.");
			driver.switchTo().defaultContent();
			return;
		}

		// Step 8: Attempt to play the video via JS
		try {
			((JavascriptExecutor) driver).executeScript("var video = document.querySelector('video');" + "if (video) {"
					+ "  video.muted = true;" + "  video.play();" + "  console.log('HTML5 video playback started');"
					+ "} else {" + "  var playBtn = document.querySelector('.vjs-play-control, [aria-label=\"Play\"]');"
					+ "  if (playBtn) playBtn.click();" + "  console.log('Clicked custom play button');" + "}");
			System.out.println("Playback script executed.");
		} catch (Exception e) {
			System.out.println("Playback script failed: " + e.getMessage());
		}

		// Step 9: Switch back to main content
		driver.switchTo().defaultContent();
		System.out.println("Switched back to main content.");
	}

	@Test
	public void Banner_is_visible() throws InterruptedException {

		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
			enterphoneNumber.sendKeys("8920689888");

			driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

			driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
			Thread.sleep(4000);
			WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
			Thread.sleep(2000);
			clickOnSendOtp.click();

			WebElement bannerIsvisible = driver
					.findElement(By.xpath("//img[@class=\"d-block w-100 mscreen image-gradient\"]"));
			if (bannerIsvisible.isDisplayed()) {
				System.out.println("Banner is visible");
			} else {
				System.out.println("Banner is not visible");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}
	
	@Test
	public void subscription() {
		
		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
			enterphoneNumber.sendKeys("8920689888");

			driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

			driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
			Thread.sleep(4000);
			WebElement clickOnSendOtp = driver.findElement(By.xpath("//button[normalize-space(.)='Verify OTP']"));
			Thread.sleep(2000);
			clickOnSendOtp.click();
			
			WebElement subscriptionButtoncheck = driver.findElement(By.xpath("(//img[@class='Movieslogo'])[5]"));
			if(subscriptionButtoncheck.isDisplayed()) {
				System.out.println("Subscription button is visible");
			}else {
				System.out.println("Subscription Button is not visible");
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void withoutLoginSubscriptionButtonisVisible() {
		try {
		WebElement withoutlogincheckSubscriptionButtonIsvisible = driver.findElement(By.xpath("(//img[@class='Movieslogo'])[5]"));
		if(withoutlogincheckSubscriptionButtonIsvisible.isDisplayed()) {
			System.out.println("Visible");
		}else {
			System.out.println("Not visible");
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void check_SubscriptionButtonIsclickable() {
		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			WebElement enterphoneNumber = driver.findElement(By.xpath("//input[@placeholder=\"Mobile Number\"]"));
			enterphoneNumber.sendKeys("8920689888");

			driver.findElement(By.xpath("//button[normalize-space(.)='Send OTP']")).click();

			driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("1234");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(.)='Verify OTP']"))).click();
			Thread.sleep(2000);
			
			
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class=\\\"Movieslogo\\\"])[5]"))).click();
			Thread.sleep(4000);
			
			List<WebElement> buttons = driver.findElements(By.className("plan_button"));

			for (int i = 0; i < buttons.size(); i++) {
			    WebElement button = buttons.get(i);
			    System.out.println("Button " + i + " text: " + button.getText());
			    
			}
			
		}catch(Exception e) {
			System.out.println((e.getMessage()));
		}
		
	}
	
	
	
	
}
