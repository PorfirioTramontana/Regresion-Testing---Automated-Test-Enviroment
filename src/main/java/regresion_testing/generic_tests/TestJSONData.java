package regresion_testing.generic_tests;

import java.util.List;
import java.util.Map;

public class TestJSONData {
	private String[] url;
	private Map<String, List<String>> searchFields;
	private Map<String, List<String>> somethingFields;

	public String[] getUrl() {
		return url;
	}

	public void setUrl(String[] url) {
		this.url = url;
	}

	public Map<String, List<String>> getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(Map<String, List<String>> searchFields) {
		this.searchFields = searchFields;
	}

	public Map<String, List<String>> getSomethingFields() {
		return somethingFields;
	}

	public void setSomethingFields(Map<String, List<String>> somethingFields) {
		this.somethingFields = somethingFields;
	}

}
