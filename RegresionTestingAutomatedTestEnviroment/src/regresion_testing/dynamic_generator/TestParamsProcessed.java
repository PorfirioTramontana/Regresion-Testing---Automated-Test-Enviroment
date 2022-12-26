package regresion_testing.dynamic_generator;

public class TestParamsProcessed {

	public enum TestType {
		TEST1, TEST2, TEST3
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

	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl;
	}

	public String getTestField() {
		return testField;
	}

	public void setTestField(String testField) {
		this.testField = testField;
	}

	public String getTestValue() {
		return testValue;
	}

	public void setTestValue(String testValue) {
		this.testValue = testValue;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

}
