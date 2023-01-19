package regresion_testing.generic_tests;

import org.openqa.selenium.WebDriver;

import regresion_testing.util.TestResult;

public interface SeleniumTests {
	public TestResult execute(WebDriver driver, String url, String field,
			String value);
}
