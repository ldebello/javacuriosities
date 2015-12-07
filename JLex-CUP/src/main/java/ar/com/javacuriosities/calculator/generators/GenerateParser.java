package ar.com.javacuriosities.calculator.generators;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.Main;

public class GenerateParser {
	private static final String SYMBOLS_CLASS_NAME = "ArithmeticSymbols";
	private static final String PARSER_CLASS_NAME = "ArithmeticParser";
	private static final String PARSER_PACKAGE = "ar.com.javacuriosities.calculator.parser";

	public static void main(String[] args) {
		try {
			URL resource = Thread.currentThread().getContextClassLoader().getResource("parser.cup");
			Main.main(
					new String[] { "-parser", PARSER_CLASS_NAME, "-symbols", SYMBOLS_CLASS_NAME, "-package" , PARSER_PACKAGE, resource.getPath() });
			File currentDirectory = new File(".");
			
			Path parserPath = Paths.get(currentDirectory.getCanonicalPath() + "/" + PARSER_CLASS_NAME + ".java");
			
			FileOutputStream parserOutputStream = new FileOutputStream(new File(currentDirectory.getCanonicalPath() + "/src/main/java/" + PARSER_PACKAGE.replaceAll("\\.", "/") + "/" + PARSER_CLASS_NAME + ".java"));
			Files.copy(parserPath, parserOutputStream);
			Files.delete(parserPath);
			
			Path symbolPath = Paths.get(currentDirectory.getCanonicalPath() + "/" + SYMBOLS_CLASS_NAME + ".java");
			
			FileOutputStream symbolOutputStream = new FileOutputStream(new File(currentDirectory.getCanonicalPath() + "/src/main/java/" + PARSER_PACKAGE.replaceAll("\\.", "/") + "/" + SYMBOLS_CLASS_NAME + ".java"));
			Files.copy(symbolPath, symbolOutputStream);
			Files.delete(symbolPath);
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}