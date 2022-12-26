package regresion_testing.dynamic_generator;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import regresion_testing.dynamic_generator.TestParamsProcessed.TestType;
import regresion_testing.generic_tests.SeleniumTests;
import regresion_testing.generic_tests.TestResult;

public class DynamicGenerator {
	private static WebDriver driver = null;
	private String testUrl;
	private String field;
	private String value;
	private TestType testType;
	private static String jsonRoute;

	@BeforeClass
	public static void setup() {
		// Webdriver Initialization
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	/**
	 * Each parameter should be placed as an argument here, every time runner
	 * triggers, it will pass the arguments from parameters we defined in data()
	 * method.
	 * 
	 * @param testUrl
	 * @param field
	 * @param value
	 * @param testType
	 */
	public DynamicGenerator(String testUrl, String field, String value,
			TestType testType) {
		this.testUrl = testUrl;
		this.field = field;
		this.value = value;
		this.testType = testType;
	}

	@Parameterized.Parameters
	public static Collection data() throws IOException {
		// Obtain JSON data
		Path inputFile = FileSystems.getDefault().getPath(jsonRoute);
		String dataString = readFile(inputFile);

		// Need to create something to process the JSON information with GSON
		// new GSON().fromJson(dataString, .class);

		// Something to process all tests and store them in a list.

		// Process all the data and return it.
		List<Object[]> list = new ArrayList<Object[]>();
//		for () {
//			Object[] args = new Object[4];
//			args[0] = ;
//			args[1] = ;
//			args[2] = ;
//			args[3] = ;
//			list.add(args);
//		}

		return list;
	}

	/**
	 * It is executed n times where n is the size of the Collection returned in
	 * the data() method and each time with different data is used ( the data is
	 * each object returned in the Collection ). Depending on the TestType it
	 * selects the Selenium test to be used and it gets processed in a
	 * TestResult to check in the command line feed.
	 */
	@Test
	public void dynamicTest() {
		TestResult res = null;

		if (testType.equals(TestType.TEST1)) {
			// Need to add parameters to each test with webdriver, url...
			res = new SeleniumTests().test1();
		} else if (testType.equals(TestType.TEST2)) {
			// Need to add parameters to each test with webdriver, url...
			res = new SeleniumTests().test2();
		} else if (testType.equals(TestType.TEST3)) {
			// Need to add parameters to each test with webdriver, url...
			res = new SeleniumTests().test3();
		}

		assertTrue(res.isSuccess());

		if (res.isSuccess())
			System.out.print("OK ");
		else
			System.out.print("ERR ");

		System.out.println(res);
	}

	/**
	 * Method that processes the file into a String to be used later in other
	 * method.
	 * 
	 * @param inputFile
	 * @return
	 * @throws IOException
	 */
	private static String readFile(Path inputFile) throws IOException {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(inputFile,
				StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		}
		return contentBuilder.toString();
	}

	@AfterClass
	public static void end() {
		// Closing browser windows related to this WebDriver.
		driver.quit();
	}
}
