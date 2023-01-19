package regresion_testing.util;

import regresion_testing.generic_tests.SeleniumTests;

public class TestParamsProcessed {
	private String testUrl;
	private String testField;
	private String testValue;
	private SeleniumTests testType;

	public TestParamsProcessed(String testUrl, String testField,
			String testValue, SeleniumTests testType) {
		super();
		this.testUrl = testUrl;
		this.testField = testField;
		this.testValue = testValue;
		this.testType = testType;
	}

	public String getTestUrl() {
		return testUrl;
	}

	public String getTestField() {
		return testField;
	}

	public String getTestValue() {
		return testValue;
	}

	public SeleniumTests getTestType() {
		return testType;
	}

}
