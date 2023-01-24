package regresion_testing.generic_tests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import regresion_testing.util.TestResult;

public class ComboboxTest implements SeleniumTests {

	
	/**
	 * This test is used to edit a part in a webpage and check that it has been
	 * changed.
	 * 
	 * @param driver
	 * @param url
	 * @param field
	 * @param value
	 * @return
	 */
	@Override
	public TestResult execute (WebDriver driver, String url, String field,
			String value) {
		TestResult result = new TestResult(url, "Combobox",
				Map.of(field, value));

		try {
			HttpURLConnection connection = null;
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();

			if (connection != null) {
				if (!String.valueOf(connection.getResponseCode())
						.startsWith("2")) {
					result.setErrorMessage(
							"There was a problem with the Website, response code: "
									+ String.valueOf(
											connection.getResponseCode()));
				}
			}

			driver.get(url);
			Select selectBox = new Select(driver.findElement(By.xpath(field)));
			selectBox.selectByValue(value);
			result.setSuccess(true);

		} catch (Exception e) {
			// The split is done because it returns an overided method that adds
			// way much information.
			result.setErrorMessage(e.getMessage().split("For")[0].strip());
		}

		return result;
	}

}
