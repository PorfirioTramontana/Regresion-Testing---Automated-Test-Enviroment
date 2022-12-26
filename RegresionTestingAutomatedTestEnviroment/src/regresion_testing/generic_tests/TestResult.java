package regresion_testing.generic_tests;

import java.util.Map;
import java.util.Objects;

public class TestResult {
	private String url;
	private boolean success;
	private String errorMessage;
	private String testName;
	private Map<String, String> args;

	public TestResult(String url, boolean success, String errorMessage,
			String testName, Map<String, String> args) {
		super();
		this.url = url;
		this.success = success;
		this.errorMessage = errorMessage;
		this.testName = testName;
		this.args = args;
	}

	public boolean isSuccess() {
		return success;
	}

	@Override
	public int hashCode() {
		return Objects.hash(args, errorMessage, success, testName, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestResult other = (TestResult) obj;
		return Objects.equals(args, other.args)
				&& Objects.equals(errorMessage, other.errorMessage)
				&& success == other.success
				&& Objects.equals(testName, other.testName)
				&& Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "TestResult [url=" + url + ", success=" + success
				+ ", errorMessage=" + errorMessage + ", testName=" + testName
				+ ", args=" + args + "]";
	}
}
