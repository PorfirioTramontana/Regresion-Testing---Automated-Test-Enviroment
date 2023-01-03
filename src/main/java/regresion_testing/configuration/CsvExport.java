package regresion_testing.configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class CsvExport {

	private List<String[]> csvExport = null;

	public void addCsvExport(String[] csvExport) {
		this.csvExport.add(csvExport);
	}

	public CsvExport() {
		csvExport = new ArrayList<String[]>();
	}

	public void export() throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter("output.csv"));
		writer.writeAll(csvExport);
	}
}
