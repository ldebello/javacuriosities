package ar.com.javacuriosities.antlr.tests;

import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import ar.com.javacuriosities.antlr.csv.CSVLexer;
import ar.com.javacuriosities.antlr.csv.CSVParser;
import ar.com.javacuriosities.antlr.csv.ast.CSVFile;
import ar.com.javacuriosities.antlr.csv.ast.Record;
import junit.framework.TestCase;

public class TestCSVParser extends TestCase {
	public void testTokens() {
		try (Scanner scanner = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("Data.csv"))) {
			String source = scanner.useDelimiter("\\A").next();
			CSVLexer lexer = new CSVLexer(new ANTLRStringStream(source));
			
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			
			CSVParser parser = new CSVParser(tokens);
			
			CSVFile file = parser.file();
			
			List<Record> records = file.getRecords();
			
			for (Record record : records) {
				List<String> values = record.getValues();
				StringJoiner joiner = new StringJoiner(",");
				for (String value : values) {
					joiner.add(value);
				}
				System.out.println(joiner.toString());
			}
		} catch (RecognitionException e) {
			// Log and Handle exception
			e.printStackTrace();
		}	
	}
}