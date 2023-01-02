package regresion_testing.generic_tests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumTests {

	/**
	 * This test is used to check if the search function works in a webpage.
	 * 
	 * @param driver
	 * @param url
	 * @param field
	 * @param value
	 * @return
	 */
	public TestResult test1(WebDriver driver, String url, String field,
			String value) {
		TestResult result = new TestResult(url, "Test 1", Map.of(field, value));

		try {
			driver.get(url);
			WebElement element = driver.findElement(By.xpath(field));
			element.sendKeys(value);
			element.submit();

			HttpURLConnection connection = null;
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();

			if (connection != null) {
				result.setSuccess(String.valueOf(connection.getResponseCode())
						.startsWith("2"));

			}
		} catch (Exception e) {
			// The split is done because it returns an overided method that adds
			// way much information.
			result.setErrorMessage(e.getMessage().split("For")[0].strip());
		}

		return result;
	}

	/**
	 * This test is used to check if a certain word is appearing.
	 * 
	 * @param driver
	 * @param url
	 * @param field
	 * @param value
	 * @return
	 */
	public TestResult test2(WebDriver driver, String url, String field,
			String value) {
		TestResult result = new TestResult(url, "Test 2", Map.of(field, value));

		try {
			driver.get(url);
			WebElement element = driver.findElement(By.xpath(field));
			HttpURLConnection connection = null;
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();

			if (connection != null) {
				if (element.getText().equals(value)) {
					result.setSuccess(true);
				} else {
					if (String.valueOf(connection.getResponseCode())
							.startsWith("2")) {
						result.setErrorMessage("The expected value was: "
								+ value + ", and it was: " + element.getText());
					} else {
						result.setErrorMessage(
								"There was a problem with the Website, response code: "
										+ String.valueOf(
												connection.getResponseCode()));
					}
				}
			}
		} catch (Exception e) {
			// The split is done because it returns an overided method that adds
			// way much information.
			result.setErrorMessage(e.getMessage().split("For")[0].strip());
		}

		return result;
	}

	public TestResult test3() {
		return null;
	}
}
