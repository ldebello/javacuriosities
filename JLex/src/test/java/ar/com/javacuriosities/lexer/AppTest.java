package ar.com.javacuriosities.lexer;

import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

public class AppTest extends TestCase {

	public void testPlusOperation() {
		try (InputStreamReader isr = new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("plusOperationTest.calc"))) {
			ExpressionLexer lexer = new ExpressionLexer(isr);
			CustomToken token = null;
			do {
				token = lexer.nextToken();
				if (token != null) {
					System.out.println(token);
				}
			} while (token != null);
			assertTrue(true);
		} catch (IOException e) {
			// Log and handle exception
			e.printStackTrace();
		}
	}
}