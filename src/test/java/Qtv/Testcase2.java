package Qtv;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class Testcase2 extends TestCase {

	@Test
	public void checkcouponprice() {
		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//button[normalize-space(.)=\"Login with Email\"]")).click();

			driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
			driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
			driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
			driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			WebElement clickonchooseplan = wait.until(ExpectedConditions.elementToBeClickable(
					(By.xpath("(//button[@class=\"subscription_button btn btn-lg btn-block planBtn\"])[1]"))));
			clickonchooseplan.click();

			Thread.sleep(4000);

			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Enter coupon code...\"]")))
					.sendKeys("4OefxM");

			WebElement applyOncouponcode = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"apply-button\"]")));
			applyOncouponcode.click();
			Thread.sleep(4000);

			WebElement click = driver.findElement(By.xpath("//div[@class=\"text\"]"));
			System.out.println(click.getText());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void couponPercentage() {
		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//button[normalize-space(.)=\"Login with Email\"]")).click();

			driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
			driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
			driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
			driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			WebElement clickonchooseplan = wait.until(ExpectedConditions.elementToBeClickable(
					(By.xpath("(//button[@class=\"subscription_button btn btn-lg btn-block planBtn\"])[1]"))));
			clickonchooseplan.click();

			Thread.sleep(4000);

			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"Enter coupon code...\"]")))
					.sendKeys("t7fnlx");

			WebElement applyOncouponcode = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class=\"apply-button\"]")));
			applyOncouponcode.click();
			Thread.sleep(4000);

			WebElement click = driver.findElement(By.xpath("//div[@class=\"text\"]"));
			System.out.println(click.getText());
			String checkcouponcodeapply = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"error-message\"]")))
					.getText();
			String validate = "Coupon applied";
			if (checkcouponcodeapply.equals(validate)) {
				System.out.println("Applied coupon code");
			} else {
				System.out.println("Something went wrong");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void checkrazorpayopen() throws InterruptedException {
		try {
			// Login steps
			driver.findElement(By.xpath("//a[@href='/login']")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//button[normalize-space(.)='Login with Email']")).click();
			driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
			driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
			driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
			driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();

			// Select a movie
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			// Choose subscription plan
			WebElement clickOnChoosePlan = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("(//button[@class='subscription_button btn btn-lg btn-block planBtn'])[1]")));
			clickOnChoosePlan.click();
			Thread.sleep(4000);

			// Apply coupon
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter coupon code...']")))
					.sendKeys("t7fnlx");

			WebElement applyOnCouponCode = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='apply-button']")));
			applyOnCouponCode.click();
			Thread.sleep(4000);

			// Confirm coupon application
			WebElement click = driver.findElement(By.xpath("//div[@class='text']"));
			System.out.println(click.getText());

			String checkCouponCodeApply = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='error-message']")))
					.getText();

			if ("Coupon applied".equals(checkCouponCodeApply)) {
				System.out.println("Applied coupon code");
			} else {
				System.out.println("Something went wrong");
			}

			// Click Razorpay payment button
			WebElement clickOnRazorpayButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='text']")));
			clickOnRazorpayButton.click();

			Thread.sleep(4000);

			// Switch to Razorpay iframe
			WebElement razorpayFrame = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe[src*='razorpay']")));
			driver.switchTo().frame(razorpayFrame);

			// Enter mobile number
			WebElement mobileInput = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Mobile number']")));
			mobileInput.clear();
			mobileInput.sendKeys("8920689888");
			Thread.sleep(5000);

			// Click continue (optional)
			WebElement continueBtn = driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
			continueBtn.click();

			// Switch back to main content
			driver.switchTo().defaultContent();

			// Optional: Confirm Razorpay container is visible
			boolean check = driver.findElement(By.id("razorpay-checkout-v2-container")).isDisplayed();
			System.out.println("Razorpay modal displayed: " + check);

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	@Test
	public void Validate_No_Data_message_is_visible() throws InterruptedException {

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
		searchBox.sendKeys("Hey only put for testing purpose", Keys.ENTER);
		Thread.sleep(3000);

		String message = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[normalize-space()='No data available']")))
				.getText();
		System.out.println(message);
	}

	@Test
	public void clickwatchnow_userredirect_into_login() throws InterruptedException {
		Thread.sleep(4000);

		WebElement clickonvideo = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class=\"img-top\"])[1]")));
		clickonvideo.click();

		driver.findElement(By.xpath("//button[@class=\"sc-fUnMCh hzdSCt\"]")).click();
		boolean check = driver.findElement(By.xpath("//div[@class=\"signin-form\"]")).isDisplayed();
		System.out.println(check);
	}

	@Test
	public void insidecouponpagebannerisvisible() {

		try {
			driver.findElement(By.xpath("//a[@href=\"/login\"]")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//button[normalize-space(.)=\"Login with Email\"]")).click();

			driver.findElement(By.xpath("//input[@placeholder='Enter Email']")).sendKeys("rajnish.kumar@unvii.com");
			driver.findElement(By.xpath("//button[normalize-space()='Send OTP']")).click();
			driver.findElement(By.xpath("//input[@class='otp-input form-control']")).sendKeys("1234");
			driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='Movieslogo'])[5]"))).click();
			Thread.sleep(4000);

			WebElement clickonchooseplan = wait.until(ExpectedConditions.elementToBeClickable(
					(By.xpath("(//button[@class=\"subscription_button btn btn-lg btn-block planBtn\"])[1]"))));
			clickonchooseplan.click();

			Thread.sleep(4000);
			List<WebElement> banners = driver.findElements(By.xpath("//img[@class='d-block w-100 mscreen']"));
			int totalBanners = banners.size();

			System.out.println("Total number of banners: " + totalBanners);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void checkNoofBannerpresendInHomepage() throws InterruptedException {
		try {
			Thread.sleep(4000);

			List<WebElement> banners = driver.findElements(By.xpath("//img[@class='d-block w-100 mscreen']"));
			int totalBanners = banners.size();
			System.out.println(totalBanners);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void checkAllanguageIsvisible() {
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class=\"Movieslogo\"])[4]"))).click();
	    List<WebElement> listOfLanguages = driver.findElements(By.xpath("//div[@class='card']"));
	    
	    System.out.println("Total languages found: " + listOfLanguages.size());

	    for (int i = 0; i < listOfLanguages.size(); i++) {
	        WebElement languageCard = listOfLanguages.get(i);
	        String languageName = languageCard.getText(); // or use .findElement(...) if text is inside a child
	        System.out.println("Language " + (i + 1) + ": " + languageName);
	    }
	}
}
