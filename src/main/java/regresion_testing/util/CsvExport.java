package regresion_testing.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class CsvExport {

	private List<String[]> csvExport = null;

	/**
	 * Used to add lines to the csv file.
	 * @param csvExport
	 */
	public void addCsvExport(String[] csvExport) {
		this.csvExport.add(csvExport);
	}

	public CsvExport() {
		csvExport = new ArrayList<String[]>();
	}

	/**
	 * Used to export the information in csvExport to the output file.
	 */
	public void export() {
		try (CSVWriter writer = new CSVWriter(new FileWriter("output.csv"));) {
			writer.writeAll(csvExport);
		} catch (IOException e) {
			System.err.println("Error while exporting to .csv");
		}
	}
}
