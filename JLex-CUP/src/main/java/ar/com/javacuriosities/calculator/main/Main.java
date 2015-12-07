package ar.com.javacuriosities.calculator.main;

import java.io.File;
import java.io.FileReader;
import java.net.URL;

import ar.com.javacuriosities.calculator.lexer.ArithmeticLexer;
import ar.com.javacuriosities.calculator.model.Document;
import ar.com.javacuriosities.calculator.parser.ArithmeticParser;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;

public class Main {
	public static void main(String[] args) {
		try {
			URL resource = Thread.currentThread().getContextClassLoader().getResource("examples.data");
			ArithmeticLexer lexer = new ArithmeticLexer(new FileReader(new File(resource.getFile())));
			SymbolFactory symbolFactory = new DefaultSymbolFactory();
			ArithmeticParser parser = new ArithmeticParser(lexer, symbolFactory);
			// Se puede usar el método debug_parse para obtener información útil como los token procesados y reglas aplicadas
			Symbol root = parser.parse();
			Document document = (Document) root.value;
			document.evaluate();
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
