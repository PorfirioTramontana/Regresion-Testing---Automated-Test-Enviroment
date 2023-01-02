package regresion_testing.dynamic_generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.google.gson.Gson;

import regresion_testing.configuration.ANSIConstants;
import regresion_testing.configuration.Configuration;
import regresion_testing.configuration.CsvExport;
import regresion_testing.generic_tests.SeleniumTests;
import regresion_testing.generic_tests.TestJSONData;
import regresion_testing.generic_tests.TestParamsProcessed;
import regresion_testing.generic_tests.TestParamsProcessed.TestType;
import regresion_testing.generic_tests.TestResult;

@RunWith(Parameterized.class)
public class DynamicGenerator {
	private static WebDriver driver = null;
	private String testUrl;
	private String field;
	private String value;
	private TestType testType;
	private static String jsonRoute = "config.json";
	private static Configuration config;
	private static List<TestParamsProcessed> testsProcessed;
	private static CsvExport csvExport;

	@BeforeClass
	public static void setup() {
		// Selenium warnings silence
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
				"GeckoDriverLog.txt");
		// Cookies Initialization. Have to move the path into the config.json
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("-profile", "resources\\jw3wcbv0.default-release");
		// Webdriver Initialization
		driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
		// Initialization export of csv
		csvExport = new CsvExport();
		String[] header = { "STATUS", "NAME", "URL", "INPUT", "XPATH",
				"ERROR MESSAGE" };
		csvExport.addCsvExport(header);
		// Start of the execution
		System.out.println(ANSIConstants.ANSI_BOLD
				+ "REGRESION TESTING - AUTOMATED TEST ENVIROMENT"
				+ ANSIConstants.ANSI_RESET);
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
	public static List<Object[]> data() throws IOException {
		// Obtain JSON data
		Path inputFile = FileSystems.getDefault().getPath(jsonRoute);
		String dataString = readFile(inputFile);

		// Here we process the main JSON called config.json
		config = new Gson().fromJson(dataString, Configuration.class);

		// List of all tests processed
		testsProcessed = new ArrayList<TestParamsProcessed>();

		// Something to process all tests and store them in a list.
		for (String jsonTestsPath : config.getJsonTests()) {
			inputFile = FileSystems.getDefault().getPath(jsonTestsPath);
			dataString = readFile(inputFile);

			TestJSONData dataList = new Gson().fromJson(dataString,
					TestJSONData.class);

			// If wanted to add more tests, add them below with the
			// testProcessor method.

			testProcessor(dataList.getSearchFields(), dataList.getUrl(),
					TestType.TEST1);

			testProcessor(dataList.getSomethingFields(), dataList.getUrl(),
					TestType.TEST2);
		}

		// Process all the data and return it.
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < testsProcessed.size(); i++) {
			Object[] args = new Object[4];
			TestParamsProcessed tpp = testsProcessed.get(i);
			args[0] = tpp.getTestUrl();
			args[1] = tpp.getTestField();
			args[2] = tpp.getTestValue();
			args[3] = tpp.getTestType();
			list.add(args);
		}

		return list;
	}

	private static void testProcessor(Map<String, List<String>> map,
			String[] urls, TestType testType) {
		if (map != null) {
			List<String> keySet = new ArrayList<String>(map.keySet());
			// Run through the Map of Fields
			for (int i = 0; i < map.size(); i++) {
				// Update the URL each time the XPath changes
				String actualUrl = urls[i];
				// Read each Value inside the Key
				for (String valueInside : map.get(keySet.get(i))) {
					testsProcessed.add(new TestParamsProcessed(actualUrl,
							keySet.get(i), valueInside, testType));
				}
			}
		}
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
			res = new SeleniumTests().test1(driver, testUrl, field, value);
		} else if (testType.equals(TestType.TEST2)) {
			// Need to add parameters to each test with webdriver, url...
			res = new SeleniumTests().test2(driver, testUrl, field, value);
		} else if (testType.equals(TestType.TEST3)) {
			// Need to add parameters to each test with webdriver, url...
			res = new SeleniumTests().test3();
		}

		String status;

		if (res.isSuccess()) {
			status = "PASSED";
			System.out.print(ANSIConstants.ANSI_GREEN + "PASSED"
					+ ANSIConstants.ANSI_RESET);
		} else {
			status = "FAILED";
			System.out.print(ANSIConstants.ANSI_RED + "FAILED"
					+ ANSIConstants.ANSI_RESET);
		}

		System.out.println(res);

		String[] body = { status, res.getTestName(), res.getUrl(),
				res.getArgValue(), res.getArgKey(), res.getErrorMessage() };
		csvExport.addCsvExport(body);
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
		csvExport.export();
	}
}
