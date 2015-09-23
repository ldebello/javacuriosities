package ar.com.javacuriosities.lexer;

public final class CustomToken {

	// Definimos los atributos del token
	public int numToken;
	public String text;
	public String type;
	public int lineBegin;
	public int charBegin;

	public CustomToken(int numToken, String text, String type, int lineBegin, int charBegin) {
		super();
		this.numToken = numToken;
		this.text = text;
		this.type = type;
		this.lineBegin = lineBegin;
		this.charBegin = charBegin;
	}

	@Override
	public String toString() {
		return "Yytoken [numToken=" + numToken + ", text=" + text + ", type=" + type + ", lineBegin=" + lineBegin + ", charBegin=" + charBegin + "]";
	}
}