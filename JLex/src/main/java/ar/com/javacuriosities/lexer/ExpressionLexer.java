/*
 * Las especificación JLex están organizadas en tres secciones, separadas por la directiva "%%",
 * como se muestra a continuación:
 * 
 * Código Java propio 
 * %%
 * Directivas JLex
 * %%
 * Reglas de expresiones regulares
 *
 * La directiva "%%" distingue las diferentes secciones y debe ser ubicada en al comienzo de la línea, 
 * y esa line debe permanecer en blanco.
 *
 *
 * Código Java propio
 * Esta sección nos permite agregar nuestro propio código Java, el código se agregara al comienzo del archivo
 * de salida, podemos incluir cosas como el paquete de la clase de salida, clases que importaremos, implementaciones
 * de clases auxiliares o creación de tipos de datos personalizados que usaremos.
 */
package ar.com.javacuriosities.lexer;
import java.lang.System;
/*
 * Definimos la clase Yytoken, esta es casi siempre necesaria porque el método yylex() 
 * retorna un objeto de este tipo, podemos agregar tantos parámetros y datos como quisiéramos.
 * Si queremos evitar esto podemos usar la directiva "%integer" la cual hace que el método yylex()
 * retorne un int o usar "%intwrap" para un Integer.
 * Dado que queremos que nuestro token sea visible desde otros paquete usaremos la directiva %type
 * para customizar el tipo de token retornado, mas adelante veremos que esta es la forma de integrar cup
 * con JLex
 */
class Yytoken {
	// Definimos los atributos del token
	public int numToken;
	public String text;
	public String type;
	public int lineBegin;
	public int charBegin;
	public Yytoken(int numToken, String text, String type, int lineBegin, int charBegin) {
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


public class ExpressionLexer {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

	// La directiva "%{ ... %}" nos permite agregar código Java, en nuestro ejemplo la usamos para agregar comentarios sobre otras directivas y definir un atributo
	private int counter;

	// Esta directiva permite que la clase generada sea publica

	// Esta directiva nos permite cambiar el nombre de la clase del analizador léxico, por defecto su nombre es Yylex

	// Esta directiva nos permite cambiar el nombre del método que retorna los tokens, por defecto su nombre es yylex

	// Esta directiva activa el contador de líneas, se utiliza la variable yyline para almacenar el índice de la primera línea del token reconocido, esto es zero-based

	// Esta directiva activa el contador de caracteres, que por defecto se encuentra desactivado, se utiliza la variable yychar para almacenar el índice del primer carácter del token reconocido, esto es zero-based

	// Esta directiva permite especificar el tipo de dato del token

	// Podemos definir macros, las cuales se componen de un nombre y una REGEX (Regular Expression)

	/* 
	 * Usando la directiva %state, podemos definir distintos estados, por defecto todas las macro 
	 * aplican al estado YYINITIAL.
	 * 
	 * Ejemplos
	 * %state STATE0, STATE1
	 *
	 * Es igual que:
	 * %state STATE0
	 * %state STATE1
	 */	 

	/*
	 * Algunas directivas adicionales que no usaremos pero existen son:	
	 * La directiva "%full" nos permite usar un alfabeto de 8-bits por defecto usa de 7-bits.
	 * La directiva "%unicode" puede ser usada para incluir un alfabeto completo de 16-bits
	 * La directiva "%ignorecase" puede ser usada para generar un analizador del tipo case-insensitive
	 * La directiva "%implements" nos permite hacer que nuestro lexer implemente una interfaz
	 */

	/*
	 * La siguiente sección es la tercera parte donde defineiremós las reglas para partir el Input Stream en
	 * tokens, cada regla puede tener asociada una acción la cual ejecuta un bloque de código Java.
	 * Podemos definir tres partes en una regla
	 * [<STATE>] <EXPRESSION> { <ACTION> }
	 * Ahora defineiremós cada parte
	 * Si tenemos una parte del input que hace matching con mas de una regla, siempre se tomara la mas larga, y si las dos tuvieran 
	 * el mismo largo se toma la que se encuentra definida primero, o sea la posición nos brinda la prioridad de esa regla
	 * El bloque de input siempre debe coincidir con al menos una regla sino se arroja un error por eso siempre agregaremos la siguiente regla
	 * al final de todo.
	 *
	 * . { java.lang.System.out.println("Unmatched input: " + yytext()); }
	 */
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public ExpressionLexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public ExpressionLexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private ExpressionLexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

	// La directiva "%init ... %init" nos permite definir código Java que será agregado en el constructor de nuestra clase
	counter = 0;
	}

	private boolean yy_eof_done = false;
	private void yy_do_eof () {
		if (false == yy_eof_done) {

	// La directiva "%eof ... %eof" nos permite definir código Java que será ejecutado al alcanzar el fin de archivo
	System.out.println("No more data to read");
		}
		yy_eof_done = true;
	}
	private final int YYINITIAL = 0;
	private final int COMMENTS = 1;
	private final int yy_state_dtrans[] = {
		0,
		14
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"10:9,12,11,10:2,11,10:18,12,10:7,7,8,5,3,10,4,10,6,2,1:9,10,9,10:64,12,10:3" +
",0:2")[0];

	private int yy_rmap[] = unpackFromString(1,26,
"0,1,2,1:4,3,1:6,4,1,5,6:3,7,6:5")[0];

	private int yy_nxt[][] = unpackFromString(8,13,
"1,2,3,4,5,6,7,8,9,10,3,11,12,-1:14,2:2,-1:16,13,-1:6,1,16,25,17,18,19,20,21" +
",22,23,25,15,25,-1,16:2,25:8,-1,25,-1,25:10,-1,25,-1,25:5,24,25:4,-1,25");

	public CustomToken nextToken ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				yy_do_eof();

	// Aquí podemos generar un token personalizado para el EOF
	return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
	counter++;
	// El método yytext() retorna el texto matcheado por esa 
	return new CustomToken(counter, yytext(),"Number", yyline, yychar); 
}
					case -3:
						break;
					case 3:
						{ 
	System.out.println("Unmatched input: " + yytext()); 
}
					case -4:
						break;
					case 4:
						{
	// Si la acción no retorna un token el lexer continua buscando el siguiente match
	System.out.println("Addition");
}
					case -5:
						break;
					case 5:
						{
	System.out.println("Subtraction");
}
					case -6:
						break;
					case 6:
						{
	System.out.println("Multiplication");
}
					case -7:
						break;
					case 7:
						{
	System.out.println("Division");
}
					case -8:
						break;
					case 8:
						{
	System.out.println("Open Parenthesis");
}
					case -9:
						break;
					case 9:
						{
	System.out.println("Close Parenthesis");
}
					case -10:
						break;
					case 10:
						{
	System.out.println("Semicolon");
}
					case -11:
						break;
					case 11:
						{
}
					case -12:
						break;
					case 12:
						{
	System.out.println("Whitespace");
}
					case -13:
						break;
					case 13:
						{
	// Cuando encontramos el token "//" pasamos al estado COMMENTS
	System.out.println("Starting comment");
	yybegin(COMMENTS);
}
					case -14:
						break;
					case 14:
						{
	return new CustomToken(counter, yytext(),"Comment", yyline, yychar);
}
					case -15:
						break;
					case 15:
						{
	System.out.println("Ending comment");
	yybegin(YYINITIAL);
}
					case -16:
						break;
					case 16:
						{
	counter++;
	// El método yytext() retorna el texto matcheado por esa 
	return new CustomToken(counter, yytext(),"Number", yyline, yychar); 
}
					case -17:
						break;
					case 17:
						{
	// Si la acción no retorna un token el lexer continua buscando el siguiente match
	System.out.println("Addition");
}
					case -18:
						break;
					case 18:
						{
	System.out.println("Subtraction");
}
					case -19:
						break;
					case 19:
						{
	System.out.println("Multiplication");
}
					case -20:
						break;
					case 20:
						{
	System.out.println("Division");
}
					case -21:
						break;
					case 21:
						{
	System.out.println("Open Parenthesis");
}
					case -22:
						break;
					case 22:
						{
	System.out.println("Close Parenthesis");
}
					case -23:
						break;
					case 23:
						{
	System.out.println("Semicolon");
}
					case -24:
						break;
					case 24:
						{
	// Cuando encontramos el token "//" pasamos al estado COMMENTS
	System.out.println("Starting comment");
	yybegin(COMMENTS);
}
					case -25:
						break;
					case 25:
						{
	return new CustomToken(counter, yytext(),"Comment", yyline, yychar);
}
					case -26:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
