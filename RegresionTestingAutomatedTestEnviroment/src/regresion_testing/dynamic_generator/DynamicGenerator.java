package regresion_testing.dynamic_generator;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicGenerator {
	private static WebDriver driver = null;

	@BeforeClass
	public static void setup() {
		// Webdriver Initialization
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	public DynamicGenerator() {
		// Here I have to add parameters and set them into global variables.
	}

	@Parameterized.Parameters
	public static Collection data() {
		return null;
	}

	@Test
	public void dynamicTest() {

	}

	@AfterClass
	public static void end() {
		// Closing browser windows related to this WebDriver.
		driver.quit();
	}
}
