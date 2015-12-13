package ar.com.javacuriosities.antlr.tests;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.Token;

import ar.com.javacuriosities.antlr.csv.CSVLexer;
import junit.framework.TestCase;

public class TestCSVLexer extends TestCase {
	public void testTokens() {
		
		String source = "";
		
		CSVLexer lexer = new CSVLexer(new ANTLRStringStream(source));

		Token token = lexer.nextToken();
		
		while (Token.EOF != token.getType()) {
			System.out.println(token);
			token = lexer.nextToken();
		}	
	}
}
