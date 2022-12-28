package regresion_testing.generic_tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumTests {

	/**
	 * 
	 * @param driver
	 * @param url
	 * @param field
	 * @param value
	 * @return
	 */
	public TestResult test1(WebDriver driver, String url, String field,
			String value) {
		try {
			driver.get(url);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		WebElement element = driver.findElement(By.id(field));
		element.sendKeys(value);
		element.submit();

		TestResult result = new TestResult(url, "Test 1", Map.of(field, value));

		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
		} catch (Exception e) {
			result.setErrorMessage(e.getMessage());
		}

		if (connection != null) {
			try {
				result.setSuccess(String.valueOf(connection.getResponseCode())
						.startsWith("2"));
			} catch (IOException e) {
			}
		}

		return result;
	}

	public TestResult test2() {
		return null;
	}

	public TestResult test3() {
		return null;
	}
}
