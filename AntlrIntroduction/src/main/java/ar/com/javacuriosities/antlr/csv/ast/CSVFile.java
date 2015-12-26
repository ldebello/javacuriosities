package ar.com.javacuriosities.antlr.csv.ast;

import java.util.ArrayList;
import java.util.List;

public class CSVFile {

	private List<Record> records = new ArrayList<>();

	public CSVFile() {
	}

	public void add(Record record) {
		records.add(record);
	}

	public List<Record> getRecords() {
		return records;
	}
}