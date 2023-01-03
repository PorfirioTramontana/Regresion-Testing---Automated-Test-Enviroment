package regresion_testing.generic_tests;

public class TestParamsProcessed {

	public enum TestType {
		SEARCH, CHECK, COMBOBOX
	};

	private String testUrl;
	private String testField;
	private String testValue;
	private TestType testType;

	public TestParamsProcessed(String testUrl, String testField,
			String testValue, TestType testType) {
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

	public TestType getTestType() {
		return testType;
	}

}
