package regresion_testing.generic_tests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import regresion_testing.util.TestResult;

public class SearchTest implements SeleniumTests {

	@Override
	/**
	 * This test is used to check if the search function works in a webpage.
	 * 
	 * @param driver
	 * @param url
	 * @param field
	 * @param value
	 * @return
	 */
	public TestResult execute (WebDriver driver, String url, String field,
			String value) {
		TestResult result = new TestResult(url, "Search", Map.of(field, value));

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

}
