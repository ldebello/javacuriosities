package ar.com.javacuriosities.calculator.generators;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerateLexer {
	
	private static final String LEXER_PACKAGE = "ar.com.javacuriosities.calculator.lexer";
	
	public static void main(String[] args) {
		try {
			URL resource = Thread.currentThread().getContextClassLoader().getResource("lexer.jlex");
			JLex.Main.main(new String[] { resource.getPath() });
			File currentDirectory = new File(".");
			FileOutputStream fileOutputStream = new FileOutputStream(new File(currentDirectory.getCanonicalPath() + "/src/main/java/" + LEXER_PACKAGE.replaceAll("\\.", "/") + "/ArithmeticLexer.java"));
			Path path = Paths.get(resource.getPath() + ".java");
			Files.copy(path, fileOutputStream);
			Files.delete(path);
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}