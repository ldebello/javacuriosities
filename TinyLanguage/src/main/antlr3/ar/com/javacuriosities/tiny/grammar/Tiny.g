grammar Tiny;

@lexer::header {
	package ar.com.javacuriosities.tiny.grammar;
}

@header {
	package ar.com.javacuriosities.tiny.grammar;
}

/*
 * Podemos usar esta regla para ver los token que llegan al parser
 *
 * parse
 *	: (currentToken=.
 *		{
 *			System.out.printf("Text: \%-7s Type: \%s \n",$currentToken.text, tokenNames[$currentToken.type]);
 *		}
 *	)* 
 *	EOF
 *	;
 */

/*
 * Parser rules
 *
 */
 
parse
	: block EOF
;

block
	: (statement | functionDecl)* (Return expression ';')?
;

statement
	: assignment ';'
	| functionCall ';'
  	| ifStatement
  	| forStatement
  	| whileStatement
;

functionDecl
	: Def Identifier '(' idList? ')' block End
;
 
idList
	: Identifier (',' Identifier)*
;

assignment
	: Identifier indexes? '=' expression
;

indexes
	: ('[' expression ']')+
;

expression
	: condExpr
;

condExpr
	: orExpr ( '?' expression ':' expression | In expression)?
;

orExpr
	: andExpr ('||' andExpr)*
;

andExpr
	: equExpr ('&&' equExpr)*
;

equExpr
	: relExpr (('==' | '!=') relExpr)*
;

relExpr
	: addExpr (('>=' | '<=' | '>' | '<') addExpr)*
;

addExpr
	: mulExpr (('+' | '-') mulExpr)*
;

mulExpr
	: powExpr (('*' | '/' | '%') powExpr)*
;

powExpr
	: unaryExpr ('^' unaryExpr)*
;

unaryExpr
	: '-' atom
	| '!' atom
	| atom
;

atom
	: Null
  	| Number
  	| Bool
  	| lookup
;

lookup
	: functionCall indexes?
  	| '(' expression ')' indexes?
  	| list indexes?
  	| Identifier indexes?
  	| String indexes?
;

list
	: '[' exprList? ']'
;

exprList
	: expression (',' expression)*
;

functionCall
	: Identifier '(' exprList? ')'
  	| Println '(' expression? ')'
  	| Print '(' expression ')'
  	| Assert '(' expression ')'
  	| Size '(' expression ')'
;

ifStatement
	: ifStat elseIfStat* elseStat? End
;

ifStat
	: If expression Do block
;

elseIfStat
	: Else If expression Do block
;

elseStat
	: Else Do block
;

forStatement
	: For Identifier '=' expression To expression Do block End
;

whileStatement
	: While expression Do block End
;

/*
 * Lexer rules
 * 
 * Primero definimos los keywords para que tengan mas prioridad y no sean tratados como identificadores
 */

// Funciones
Println	 : 'println';
Print    : 'print';
Assert   : 'assert';
Size     : 'size';


Def      : 'def';
If       : 'if';
Else     : 'else';
Return   : 'return';
For      : 'for';
While    : 'while';
To       : 'to';
Do       : 'do';
End      : 'end';
In       : 'in';
Null     : 'null';

Or       : '||';
And      : '&&';
Equals   : '==';
NEquals  : '!=';
GTEquals : '>=';
LTEquals : '<=';
Pow      : '^';
Excl     : '!';
GT       : '>';
LT       : '<';
Add      : '+';
Subtract : '-';
Multiply : '*';
Divide   : '/';
Modulus  : '%';
OBrace   : '{';
CBrace   : '}';
OBracket : '[';
CBracket : ']';
OParen   : '(';
CParen   : ')';
SColon   : ';';
Assign   : '=';
Comma    : ',';
QMark    : '?';
Colon    : ':';

Bool
	: 'true' 
	| 'false'
;

Number
	: Int ('.' Digit*)?
;
  
Identifier
	: ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | Digit)*
;

String
	@after {
		setText(getText().substring(1, getText().length()-1).replaceAll("\\\\(.)", "$1"));
	}
	: '"'  (~('"' | '\\')  | '\\' .)* '"'
  	| '\'' (~('\'' | '\\') | '\\' .)* '\''
;

HexChar
	: '\\' 'u' HexDigit HexDigit HexDigit HexDigit
;

// Cuando invocamos al método skip() estamos diciendo que queremos evitar estos token en el parser
Comment
	: '//' ~('\r' | '\n')* {skip();}
	| '/*' .* '*/'         {skip();}
;

Space
	: (' ' | '\t' | '\r' | '\n' | '\u000C') {skip();}
;

/*
 * Usamos fragment cuando queremos definir reglas que no serán tokens por si mismos 
 * sino que serán reutilizados por otros
 *
 */
fragment Int
	: '1'..'9' Digit*
  	| '0'
;

fragment Digit
	: '0'..'9'
;

fragment HexDigit
	: '0'..'9' | 'a'..'f' | 'A'..'F'
;