package ar.com.javacuriosities.antlr.csv.ast;

import java.util.ArrayList;
import java.util.List;

public class Record {

	private List<String> values = new ArrayList<>();

	public Record() {
	}

	public void add(String value) {
		values.add(value);
	}

	public List<String> getValues() {
		return values;
	}
}