// $ANTLR 3.4 ar/com/javacuriosities/tiny/grammar/Tiny.g 2016-05-14 20:29:20

	package ar.com.javacuriosities.tiny.grammar;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class TinyParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Add", "And", "Assert", "Assign", "Bool", "CBrace", "CBracket", "CParen", "Colon", "Comma", "Comment", "Def", "Digit", "Divide", "Do", "Else", "End", "Equals", "Excl", "For", "GT", "GTEquals", "HexChar", "HexDigit", "Identifier", "If", "In", "Int", "LT", "LTEquals", "Modulus", "Multiply", "NEquals", "Null", "Number", "OBrace", "OBracket", "OParen", "Or", "Pow", "Print", "Println", "QMark", "Return", "SColon", "Size", "Space", "String", "Subtract", "To", "While"
    };

    public static final int EOF=-1;
    public static final int Add=4;
    public static final int And=5;
    public static final int Assert=6;
    public static final int Assign=7;
    public static final int Bool=8;
    public static final int CBrace=9;
    public static final int CBracket=10;
    public static final int CParen=11;
    public static final int Colon=12;
    public static final int Comma=13;
    public static final int Comment=14;
    public static final int Def=15;
    public static final int Digit=16;
    public static final int Divide=17;
    public static final int Do=18;
    public static final int Else=19;
    public static final int End=20;
    public static final int Equals=21;
    public static final int Excl=22;
    public static final int For=23;
    public static final int GT=24;
    public static final int GTEquals=25;
    public static final int HexChar=26;
    public static final int HexDigit=27;
    public static final int Identifier=28;
    public static final int If=29;
    public static final int In=30;
    public static final int Int=31;
    public static final int LT=32;
    public static final int LTEquals=33;
    public static final int Modulus=34;
    public static final int Multiply=35;
    public static final int NEquals=36;
    public static final int Null=37;
    public static final int Number=38;
    public static final int OBrace=39;
    public static final int OBracket=40;
    public static final int OParen=41;
    public static final int Or=42;
    public static final int Pow=43;
    public static final int Print=44;
    public static final int Println=45;
    public static final int QMark=46;
    public static final int Return=47;
    public static final int SColon=48;
    public static final int Size=49;
    public static final int Space=50;
    public static final int String=51;
    public static final int Subtract=52;
    public static final int To=53;
    public static final int While=54;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public TinyParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public TinyParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return TinyParser.tokenNames; }
    public String getGrammarFileName() { return "ar/com/javacuriosities/tiny/grammar/Tiny.g"; }



    // $ANTLR start "parse"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:29:1: parse : block EOF ;
    public final void parse() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:30:2: ( block EOF )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:30:4: block EOF
            {
            pushFollow(FOLLOW_block_in_parse33);
            block();

            state._fsp--;


            match(input,EOF,FOLLOW_EOF_in_parse35); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "parse"



    // $ANTLR start "block"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:33:1: block : ( statement | functionDecl )* ( Return expression ';' )? ;
    public final void block() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:34:2: ( ( statement | functionDecl )* ( Return expression ';' )? )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:34:4: ( statement | functionDecl )* ( Return expression ';' )?
            {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:34:4: ( statement | functionDecl )*
            loop1:
            do {
                int alt1=3;
                switch ( input.LA(1) ) {
                case Assert:
                case For:
                case Identifier:
                case If:
                case Print:
                case Println:
                case Size:
                case While:
                    {
                    alt1=1;
                    }
                    break;
                case Def:
                    {
                    alt1=2;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:34:5: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block46);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;
            	case 2 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:34:17: functionDecl
            	    {
            	    pushFollow(FOLLOW_functionDecl_in_block50);
            	    functionDecl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:34:32: ( Return expression ';' )?
            int alt2=2;
            switch ( input.LA(1) ) {
                case Return:
                    {
                    alt2=1;
                    }
                    break;
            }

            switch (alt2) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:34:33: Return expression ';'
                    {
                    match(input,Return,FOLLOW_Return_in_block55); 

                    pushFollow(FOLLOW_expression_in_block57);
                    expression();

                    state._fsp--;


                    match(input,SColon,FOLLOW_SColon_in_block59); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "block"



    // $ANTLR start "statement"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:37:1: statement : ( assignment ';' | functionCall ';' | ifStatement | forStatement | whileStatement );
    public final void statement() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:38:2: ( assignment ';' | functionCall ';' | ifStatement | forStatement | whileStatement )
            int alt3=5;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                switch ( input.LA(2) ) {
                case OParen:
                    {
                    alt3=2;
                    }
                    break;
                case Assign:
                case OBracket:
                    {
                    alt3=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;

                }

                }
                break;
            case Assert:
            case Print:
            case Println:
            case Size:
                {
                alt3=2;
                }
                break;
            case If:
                {
                alt3=3;
                }
                break;
            case For:
                {
                alt3=4;
                }
                break;
            case While:
                {
                alt3=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:38:4: assignment ';'
                    {
                    pushFollow(FOLLOW_assignment_in_statement71);
                    assignment();

                    state._fsp--;


                    match(input,SColon,FOLLOW_SColon_in_statement73); 

                    }
                    break;
                case 2 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:39:4: functionCall ';'
                    {
                    pushFollow(FOLLOW_functionCall_in_statement78);
                    functionCall();

                    state._fsp--;


                    match(input,SColon,FOLLOW_SColon_in_statement80); 

                    }
                    break;
                case 3 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:40:6: ifStatement
                    {
                    pushFollow(FOLLOW_ifStatement_in_statement87);
                    ifStatement();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:41:6: forStatement
                    {
                    pushFollow(FOLLOW_forStatement_in_statement94);
                    forStatement();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:42:6: whileStatement
                    {
                    pushFollow(FOLLOW_whileStatement_in_statement101);
                    whileStatement();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "statement"



    // $ANTLR start "functionDecl"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:45:1: functionDecl : Def Identifier '(' ( idList )? ')' block End ;
    public final void functionDecl() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:46:2: ( Def Identifier '(' ( idList )? ')' block End )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:46:4: Def Identifier '(' ( idList )? ')' block End
            {
            match(input,Def,FOLLOW_Def_in_functionDecl111); 

            match(input,Identifier,FOLLOW_Identifier_in_functionDecl113); 

            match(input,OParen,FOLLOW_OParen_in_functionDecl115); 

            // ar/com/javacuriosities/tiny/grammar/Tiny.g:46:23: ( idList )?
            int alt4=2;
            switch ( input.LA(1) ) {
                case Identifier:
                    {
                    alt4=1;
                    }
                    break;
            }

            switch (alt4) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:46:23: idList
                    {
                    pushFollow(FOLLOW_idList_in_functionDecl117);
                    idList();

                    state._fsp--;


                    }
                    break;

            }


            match(input,CParen,FOLLOW_CParen_in_functionDecl120); 

            pushFollow(FOLLOW_block_in_functionDecl122);
            block();

            state._fsp--;


            match(input,End,FOLLOW_End_in_functionDecl124); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "functionDecl"



    // $ANTLR start "idList"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:49:1: idList : Identifier ( ',' Identifier )* ;
    public final void idList() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:50:2: ( Identifier ( ',' Identifier )* )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:50:4: Identifier ( ',' Identifier )*
            {
            match(input,Identifier,FOLLOW_Identifier_in_idList135); 

            // ar/com/javacuriosities/tiny/grammar/Tiny.g:50:15: ( ',' Identifier )*
            loop5:
            do {
                int alt5=2;
                switch ( input.LA(1) ) {
                case Comma:
                    {
                    alt5=1;
                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:50:16: ',' Identifier
            	    {
            	    match(input,Comma,FOLLOW_Comma_in_idList138); 

            	    match(input,Identifier,FOLLOW_Identifier_in_idList140); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "idList"



    // $ANTLR start "assignment"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:53:1: assignment : Identifier ( indexes )? '=' expression ;
    public final void assignment() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:54:2: ( Identifier ( indexes )? '=' expression )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:54:4: Identifier ( indexes )? '=' expression
            {
            match(input,Identifier,FOLLOW_Identifier_in_assignment152); 

            // ar/com/javacuriosities/tiny/grammar/Tiny.g:54:15: ( indexes )?
            int alt6=2;
            switch ( input.LA(1) ) {
                case OBracket:
                    {
                    alt6=1;
                    }
                    break;
            }

            switch (alt6) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:54:15: indexes
                    {
                    pushFollow(FOLLOW_indexes_in_assignment154);
                    indexes();

                    state._fsp--;


                    }
                    break;

            }


            match(input,Assign,FOLLOW_Assign_in_assignment157); 

            pushFollow(FOLLOW_expression_in_assignment159);
            expression();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "assignment"



    // $ANTLR start "indexes"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:57:1: indexes : ( '[' expression ']' )+ ;
    public final void indexes() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:58:2: ( ( '[' expression ']' )+ )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:58:4: ( '[' expression ']' )+
            {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:58:4: ( '[' expression ']' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                switch ( input.LA(1) ) {
                case OBracket:
                    {
                    alt7=1;
                    }
                    break;

                }

                switch (alt7) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:58:5: '[' expression ']'
            	    {
            	    match(input,OBracket,FOLLOW_OBracket_in_indexes170); 

            	    pushFollow(FOLLOW_expression_in_indexes172);
            	    expression();

            	    state._fsp--;


            	    match(input,CBracket,FOLLOW_CBracket_in_indexes174); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "indexes"



    // $ANTLR start "expression"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:61:1: expression : condExpr ;
    public final void expression() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:62:2: ( condExpr )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:62:4: condExpr
            {
            pushFollow(FOLLOW_condExpr_in_expression186);
            condExpr();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "expression"



    // $ANTLR start "condExpr"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:65:1: condExpr : orExpr ( '?' expression ':' expression | In expression )? ;
    public final void condExpr() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:66:2: ( orExpr ( '?' expression ':' expression | In expression )? )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:66:4: orExpr ( '?' expression ':' expression | In expression )?
            {
            pushFollow(FOLLOW_orExpr_in_condExpr196);
            orExpr();

            state._fsp--;


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:66:11: ( '?' expression ':' expression | In expression )?
            int alt8=3;
            switch ( input.LA(1) ) {
                case QMark:
                    {
                    alt8=1;
                    }
                    break;
                case In:
                    {
                    alt8=2;
                    }
                    break;
            }

            switch (alt8) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:66:13: '?' expression ':' expression
                    {
                    match(input,QMark,FOLLOW_QMark_in_condExpr200); 

                    pushFollow(FOLLOW_expression_in_condExpr202);
                    expression();

                    state._fsp--;


                    match(input,Colon,FOLLOW_Colon_in_condExpr204); 

                    pushFollow(FOLLOW_expression_in_condExpr206);
                    expression();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:66:45: In expression
                    {
                    match(input,In,FOLLOW_In_in_condExpr210); 

                    pushFollow(FOLLOW_expression_in_condExpr212);
                    expression();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "condExpr"



    // $ANTLR start "orExpr"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:69:1: orExpr : andExpr ( '||' andExpr )* ;
    public final void orExpr() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:70:2: ( andExpr ( '||' andExpr )* )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:70:4: andExpr ( '||' andExpr )*
            {
            pushFollow(FOLLOW_andExpr_in_orExpr224);
            andExpr();

            state._fsp--;


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:70:12: ( '||' andExpr )*
            loop9:
            do {
                int alt9=2;
                switch ( input.LA(1) ) {
                case Or:
                    {
                    alt9=1;
                    }
                    break;

                }

                switch (alt9) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:70:13: '||' andExpr
            	    {
            	    match(input,Or,FOLLOW_Or_in_orExpr227); 

            	    pushFollow(FOLLOW_andExpr_in_orExpr229);
            	    andExpr();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "orExpr"



    // $ANTLR start "andExpr"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:73:1: andExpr : equExpr ( '&&' equExpr )* ;
    public final void andExpr() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:74:2: ( equExpr ( '&&' equExpr )* )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:74:4: equExpr ( '&&' equExpr )*
            {
            pushFollow(FOLLOW_equExpr_in_andExpr241);
            equExpr();

            state._fsp--;


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:74:12: ( '&&' equExpr )*
            loop10:
            do {
                int alt10=2;
                switch ( input.LA(1) ) {
                case And:
                    {
                    alt10=1;
                    }
                    break;

                }

                switch (alt10) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:74:13: '&&' equExpr
            	    {
            	    match(input,And,FOLLOW_And_in_andExpr244); 

            	    pushFollow(FOLLOW_equExpr_in_andExpr246);
            	    equExpr();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "andExpr"



    // $ANTLR start "equExpr"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:77:1: equExpr : relExpr ( ( '==' | '!=' ) relExpr )* ;
    public final void equExpr() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:78:2: ( relExpr ( ( '==' | '!=' ) relExpr )* )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:78:4: relExpr ( ( '==' | '!=' ) relExpr )*
            {
            pushFollow(FOLLOW_relExpr_in_equExpr258);
            relExpr();

            state._fsp--;


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:78:12: ( ( '==' | '!=' ) relExpr )*
            loop11:
            do {
                int alt11=2;
                switch ( input.LA(1) ) {
                case Equals:
                case NEquals:
                    {
                    alt11=1;
                    }
                    break;

                }

                switch (alt11) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:78:13: ( '==' | '!=' ) relExpr
            	    {
            	    if ( input.LA(1)==Equals||input.LA(1)==NEquals ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_relExpr_in_equExpr269);
            	    relExpr();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "equExpr"



    // $ANTLR start "relExpr"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:81:1: relExpr : addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* ;
    public final void relExpr() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:82:2: ( addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )* )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:82:4: addExpr ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            {
            pushFollow(FOLLOW_addExpr_in_relExpr281);
            addExpr();

            state._fsp--;


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:82:12: ( ( '>=' | '<=' | '>' | '<' ) addExpr )*
            loop12:
            do {
                int alt12=2;
                switch ( input.LA(1) ) {
                case GT:
                case GTEquals:
                case LT:
                case LTEquals:
                    {
                    alt12=1;
                    }
                    break;

                }

                switch (alt12) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:82:13: ( '>=' | '<=' | '>' | '<' ) addExpr
            	    {
            	    if ( (input.LA(1) >= GT && input.LA(1) <= GTEquals)||(input.LA(1) >= LT && input.LA(1) <= LTEquals) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_addExpr_in_relExpr300);
            	    addExpr();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "relExpr"



    // $ANTLR start "addExpr"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:85:1: addExpr : mulExpr ( ( '+' | '-' ) mulExpr )* ;
    public final void addExpr() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:86:2: ( mulExpr ( ( '+' | '-' ) mulExpr )* )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:86:4: mulExpr ( ( '+' | '-' ) mulExpr )*
            {
            pushFollow(FOLLOW_mulExpr_in_addExpr312);
            mulExpr();

            state._fsp--;


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:86:12: ( ( '+' | '-' ) mulExpr )*
            loop13:
            do {
                int alt13=2;
                switch ( input.LA(1) ) {
                case Add:
                case Subtract:
                    {
                    alt13=1;
                    }
                    break;

                }

                switch (alt13) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:86:13: ( '+' | '-' ) mulExpr
            	    {
            	    if ( input.LA(1)==Add||input.LA(1)==Subtract ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_mulExpr_in_addExpr323);
            	    mulExpr();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "addExpr"



    // $ANTLR start "mulExpr"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:89:1: mulExpr : powExpr ( ( '*' | '/' | '%' ) powExpr )* ;
    public final void mulExpr() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:90:2: ( powExpr ( ( '*' | '/' | '%' ) powExpr )* )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:90:4: powExpr ( ( '*' | '/' | '%' ) powExpr )*
            {
            pushFollow(FOLLOW_powExpr_in_mulExpr335);
            powExpr();

            state._fsp--;


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:90:12: ( ( '*' | '/' | '%' ) powExpr )*
            loop14:
            do {
                int alt14=2;
                switch ( input.LA(1) ) {
                case Divide:
                case Modulus:
                case Multiply:
                    {
                    alt14=1;
                    }
                    break;

                }

                switch (alt14) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:90:13: ( '*' | '/' | '%' ) powExpr
            	    {
            	    if ( input.LA(1)==Divide||(input.LA(1) >= Modulus && input.LA(1) <= Multiply) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_powExpr_in_mulExpr350);
            	    powExpr();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "mulExpr"



    // $ANTLR start "powExpr"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:93:1: powExpr : unaryExpr ( '^' unaryExpr )* ;
    public final void powExpr() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:94:2: ( unaryExpr ( '^' unaryExpr )* )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:94:4: unaryExpr ( '^' unaryExpr )*
            {
            pushFollow(FOLLOW_unaryExpr_in_powExpr362);
            unaryExpr();

            state._fsp--;


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:94:14: ( '^' unaryExpr )*
            loop15:
            do {
                int alt15=2;
                switch ( input.LA(1) ) {
                case Pow:
                    {
                    alt15=1;
                    }
                    break;

                }

                switch (alt15) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:94:15: '^' unaryExpr
            	    {
            	    match(input,Pow,FOLLOW_Pow_in_powExpr365); 

            	    pushFollow(FOLLOW_unaryExpr_in_powExpr367);
            	    unaryExpr();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "powExpr"



    // $ANTLR start "unaryExpr"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:97:1: unaryExpr : ( '-' atom | '!' atom | atom );
    public final void unaryExpr() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:98:2: ( '-' atom | '!' atom | atom )
            int alt16=3;
            switch ( input.LA(1) ) {
            case Subtract:
                {
                alt16=1;
                }
                break;
            case Excl:
                {
                alt16=2;
                }
                break;
            case Assert:
            case Bool:
            case Identifier:
            case Null:
            case Number:
            case OBracket:
            case OParen:
            case Print:
            case Println:
            case Size:
            case String:
                {
                alt16=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }

            switch (alt16) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:98:4: '-' atom
                    {
                    match(input,Subtract,FOLLOW_Subtract_in_unaryExpr379); 

                    pushFollow(FOLLOW_atom_in_unaryExpr381);
                    atom();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:99:4: '!' atom
                    {
                    match(input,Excl,FOLLOW_Excl_in_unaryExpr386); 

                    pushFollow(FOLLOW_atom_in_unaryExpr388);
                    atom();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:100:4: atom
                    {
                    pushFollow(FOLLOW_atom_in_unaryExpr393);
                    atom();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "unaryExpr"



    // $ANTLR start "atom"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:103:1: atom : ( Null | Number | Bool | lookup );
    public final void atom() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:104:2: ( Null | Number | Bool | lookup )
            int alt17=4;
            switch ( input.LA(1) ) {
            case Null:
                {
                alt17=1;
                }
                break;
            case Number:
                {
                alt17=2;
                }
                break;
            case Bool:
                {
                alt17=3;
                }
                break;
            case Assert:
            case Identifier:
            case OBracket:
            case OParen:
            case Print:
            case Println:
            case Size:
            case String:
                {
                alt17=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }

            switch (alt17) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:104:4: Null
                    {
                    match(input,Null,FOLLOW_Null_in_atom403); 

                    }
                    break;
                case 2 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:105:6: Number
                    {
                    match(input,Number,FOLLOW_Number_in_atom410); 

                    }
                    break;
                case 3 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:106:6: Bool
                    {
                    match(input,Bool,FOLLOW_Bool_in_atom417); 

                    }
                    break;
                case 4 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:107:6: lookup
                    {
                    pushFollow(FOLLOW_lookup_in_atom424);
                    lookup();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "atom"



    // $ANTLR start "lookup"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:110:1: lookup : ( functionCall ( indexes )? | '(' expression ')' ( indexes )? | list ( indexes )? | Identifier ( indexes )? | String ( indexes )? );
    public final void lookup() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:111:2: ( functionCall ( indexes )? | '(' expression ')' ( indexes )? | list ( indexes )? | Identifier ( indexes )? | String ( indexes )? )
            int alt23=5;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                switch ( input.LA(2) ) {
                case OParen:
                    {
                    alt23=1;
                    }
                    break;
                case Add:
                case And:
                case CBracket:
                case CParen:
                case Colon:
                case Comma:
                case Divide:
                case Do:
                case Equals:
                case GT:
                case GTEquals:
                case In:
                case LT:
                case LTEquals:
                case Modulus:
                case Multiply:
                case NEquals:
                case OBracket:
                case Or:
                case Pow:
                case QMark:
                case SColon:
                case Subtract:
                case To:
                    {
                    alt23=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;

                }

                }
                break;
            case Assert:
            case Print:
            case Println:
            case Size:
                {
                alt23=1;
                }
                break;
            case OParen:
                {
                alt23=2;
                }
                break;
            case OBracket:
                {
                alt23=3;
                }
                break;
            case String:
                {
                alt23=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }

            switch (alt23) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:111:4: functionCall ( indexes )?
                    {
                    pushFollow(FOLLOW_functionCall_in_lookup434);
                    functionCall();

                    state._fsp--;


                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:111:17: ( indexes )?
                    int alt18=2;
                    switch ( input.LA(1) ) {
                        case OBracket:
                            {
                            alt18=1;
                            }
                            break;
                    }

                    switch (alt18) {
                        case 1 :
                            // ar/com/javacuriosities/tiny/grammar/Tiny.g:111:17: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup436);
                            indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:112:6: '(' expression ')' ( indexes )?
                    {
                    match(input,OParen,FOLLOW_OParen_in_lookup444); 

                    pushFollow(FOLLOW_expression_in_lookup446);
                    expression();

                    state._fsp--;


                    match(input,CParen,FOLLOW_CParen_in_lookup448); 

                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:112:25: ( indexes )?
                    int alt19=2;
                    switch ( input.LA(1) ) {
                        case OBracket:
                            {
                            alt19=1;
                            }
                            break;
                    }

                    switch (alt19) {
                        case 1 :
                            // ar/com/javacuriosities/tiny/grammar/Tiny.g:112:25: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup450);
                            indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:113:6: list ( indexes )?
                    {
                    pushFollow(FOLLOW_list_in_lookup458);
                    list();

                    state._fsp--;


                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:113:11: ( indexes )?
                    int alt20=2;
                    switch ( input.LA(1) ) {
                        case OBracket:
                            {
                            alt20=1;
                            }
                            break;
                    }

                    switch (alt20) {
                        case 1 :
                            // ar/com/javacuriosities/tiny/grammar/Tiny.g:113:11: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup460);
                            indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:114:6: Identifier ( indexes )?
                    {
                    match(input,Identifier,FOLLOW_Identifier_in_lookup468); 

                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:114:17: ( indexes )?
                    int alt21=2;
                    switch ( input.LA(1) ) {
                        case OBracket:
                            {
                            alt21=1;
                            }
                            break;
                    }

                    switch (alt21) {
                        case 1 :
                            // ar/com/javacuriosities/tiny/grammar/Tiny.g:114:17: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup470);
                            indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;
                case 5 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:115:6: String ( indexes )?
                    {
                    match(input,String,FOLLOW_String_in_lookup478); 

                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:115:13: ( indexes )?
                    int alt22=2;
                    switch ( input.LA(1) ) {
                        case OBracket:
                            {
                            alt22=1;
                            }
                            break;
                    }

                    switch (alt22) {
                        case 1 :
                            // ar/com/javacuriosities/tiny/grammar/Tiny.g:115:13: indexes
                            {
                            pushFollow(FOLLOW_indexes_in_lookup480);
                            indexes();

                            state._fsp--;


                            }
                            break;

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "lookup"



    // $ANTLR start "list"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:118:1: list : '[' ( exprList )? ']' ;
    public final void list() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:119:2: ( '[' ( exprList )? ']' )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:119:4: '[' ( exprList )? ']'
            {
            match(input,OBracket,FOLLOW_OBracket_in_list491); 

            // ar/com/javacuriosities/tiny/grammar/Tiny.g:119:8: ( exprList )?
            int alt24=2;
            switch ( input.LA(1) ) {
                case Assert:
                case Bool:
                case Excl:
                case Identifier:
                case Null:
                case Number:
                case OBracket:
                case OParen:
                case Print:
                case Println:
                case Size:
                case String:
                case Subtract:
                    {
                    alt24=1;
                    }
                    break;
            }

            switch (alt24) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:119:8: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_list493);
                    exprList();

                    state._fsp--;


                    }
                    break;

            }


            match(input,CBracket,FOLLOW_CBracket_in_list496); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "list"



    // $ANTLR start "exprList"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:122:1: exprList : expression ( ',' expression )* ;
    public final void exprList() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:123:2: ( expression ( ',' expression )* )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:123:4: expression ( ',' expression )*
            {
            pushFollow(FOLLOW_expression_in_exprList506);
            expression();

            state._fsp--;


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:123:15: ( ',' expression )*
            loop25:
            do {
                int alt25=2;
                switch ( input.LA(1) ) {
                case Comma:
                    {
                    alt25=1;
                    }
                    break;

                }

                switch (alt25) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:123:16: ',' expression
            	    {
            	    match(input,Comma,FOLLOW_Comma_in_exprList509); 

            	    pushFollow(FOLLOW_expression_in_exprList511);
            	    expression();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "exprList"



    // $ANTLR start "functionCall"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:126:1: functionCall : ( Identifier '(' ( exprList )? ')' | Println '(' ( expression )? ')' | Print '(' expression ')' | Assert '(' expression ')' | Size '(' expression ')' );
    public final void functionCall() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:127:2: ( Identifier '(' ( exprList )? ')' | Println '(' ( expression )? ')' | Print '(' expression ')' | Assert '(' expression ')' | Size '(' expression ')' )
            int alt28=5;
            switch ( input.LA(1) ) {
            case Identifier:
                {
                alt28=1;
                }
                break;
            case Println:
                {
                alt28=2;
                }
                break;
            case Print:
                {
                alt28=3;
                }
                break;
            case Assert:
                {
                alt28=4;
                }
                break;
            case Size:
                {
                alt28=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;

            }

            switch (alt28) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:127:4: Identifier '(' ( exprList )? ')'
                    {
                    match(input,Identifier,FOLLOW_Identifier_in_functionCall523); 

                    match(input,OParen,FOLLOW_OParen_in_functionCall525); 

                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:127:19: ( exprList )?
                    int alt26=2;
                    switch ( input.LA(1) ) {
                        case Assert:
                        case Bool:
                        case Excl:
                        case Identifier:
                        case Null:
                        case Number:
                        case OBracket:
                        case OParen:
                        case Print:
                        case Println:
                        case Size:
                        case String:
                        case Subtract:
                            {
                            alt26=1;
                            }
                            break;
                    }

                    switch (alt26) {
                        case 1 :
                            // ar/com/javacuriosities/tiny/grammar/Tiny.g:127:19: exprList
                            {
                            pushFollow(FOLLOW_exprList_in_functionCall527);
                            exprList();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,CParen,FOLLOW_CParen_in_functionCall530); 

                    }
                    break;
                case 2 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:128:6: Println '(' ( expression )? ')'
                    {
                    match(input,Println,FOLLOW_Println_in_functionCall537); 

                    match(input,OParen,FOLLOW_OParen_in_functionCall539); 

                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:128:18: ( expression )?
                    int alt27=2;
                    switch ( input.LA(1) ) {
                        case Assert:
                        case Bool:
                        case Excl:
                        case Identifier:
                        case Null:
                        case Number:
                        case OBracket:
                        case OParen:
                        case Print:
                        case Println:
                        case Size:
                        case String:
                        case Subtract:
                            {
                            alt27=1;
                            }
                            break;
                    }

                    switch (alt27) {
                        case 1 :
                            // ar/com/javacuriosities/tiny/grammar/Tiny.g:128:18: expression
                            {
                            pushFollow(FOLLOW_expression_in_functionCall541);
                            expression();

                            state._fsp--;


                            }
                            break;

                    }


                    match(input,CParen,FOLLOW_CParen_in_functionCall544); 

                    }
                    break;
                case 3 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:129:6: Print '(' expression ')'
                    {
                    match(input,Print,FOLLOW_Print_in_functionCall551); 

                    match(input,OParen,FOLLOW_OParen_in_functionCall553); 

                    pushFollow(FOLLOW_expression_in_functionCall555);
                    expression();

                    state._fsp--;


                    match(input,CParen,FOLLOW_CParen_in_functionCall557); 

                    }
                    break;
                case 4 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:130:6: Assert '(' expression ')'
                    {
                    match(input,Assert,FOLLOW_Assert_in_functionCall564); 

                    match(input,OParen,FOLLOW_OParen_in_functionCall566); 

                    pushFollow(FOLLOW_expression_in_functionCall568);
                    expression();

                    state._fsp--;


                    match(input,CParen,FOLLOW_CParen_in_functionCall570); 

                    }
                    break;
                case 5 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:131:6: Size '(' expression ')'
                    {
                    match(input,Size,FOLLOW_Size_in_functionCall577); 

                    match(input,OParen,FOLLOW_OParen_in_functionCall579); 

                    pushFollow(FOLLOW_expression_in_functionCall581);
                    expression();

                    state._fsp--;


                    match(input,CParen,FOLLOW_CParen_in_functionCall583); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "functionCall"



    // $ANTLR start "ifStatement"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:134:1: ifStatement : ifStat ( elseIfStat )* ( elseStat )? End ;
    public final void ifStatement() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:135:2: ( ifStat ( elseIfStat )* ( elseStat )? End )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:135:4: ifStat ( elseIfStat )* ( elseStat )? End
            {
            pushFollow(FOLLOW_ifStat_in_ifStatement593);
            ifStat();

            state._fsp--;


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:135:11: ( elseIfStat )*
            loop29:
            do {
                int alt29=2;
                switch ( input.LA(1) ) {
                case Else:
                    {
                    switch ( input.LA(2) ) {
                    case If:
                        {
                        alt29=1;
                        }
                        break;

                    }

                    }
                    break;

                }

                switch (alt29) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:135:11: elseIfStat
            	    {
            	    pushFollow(FOLLOW_elseIfStat_in_ifStatement595);
            	    elseIfStat();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);


            // ar/com/javacuriosities/tiny/grammar/Tiny.g:135:23: ( elseStat )?
            int alt30=2;
            switch ( input.LA(1) ) {
                case Else:
                    {
                    alt30=1;
                    }
                    break;
            }

            switch (alt30) {
                case 1 :
                    // ar/com/javacuriosities/tiny/grammar/Tiny.g:135:23: elseStat
                    {
                    pushFollow(FOLLOW_elseStat_in_ifStatement598);
                    elseStat();

                    state._fsp--;


                    }
                    break;

            }


            match(input,End,FOLLOW_End_in_ifStatement601); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "ifStatement"



    // $ANTLR start "ifStat"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:138:1: ifStat : If expression Do block ;
    public final void ifStat() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:139:2: ( If expression Do block )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:139:4: If expression Do block
            {
            match(input,If,FOLLOW_If_in_ifStat611); 

            pushFollow(FOLLOW_expression_in_ifStat613);
            expression();

            state._fsp--;


            match(input,Do,FOLLOW_Do_in_ifStat615); 

            pushFollow(FOLLOW_block_in_ifStat617);
            block();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "ifStat"



    // $ANTLR start "elseIfStat"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:142:1: elseIfStat : Else If expression Do block ;
    public final void elseIfStat() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:143:2: ( Else If expression Do block )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:143:4: Else If expression Do block
            {
            match(input,Else,FOLLOW_Else_in_elseIfStat627); 

            match(input,If,FOLLOW_If_in_elseIfStat629); 

            pushFollow(FOLLOW_expression_in_elseIfStat631);
            expression();

            state._fsp--;


            match(input,Do,FOLLOW_Do_in_elseIfStat633); 

            pushFollow(FOLLOW_block_in_elseIfStat635);
            block();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "elseIfStat"



    // $ANTLR start "elseStat"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:146:1: elseStat : Else Do block ;
    public final void elseStat() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:147:2: ( Else Do block )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:147:4: Else Do block
            {
            match(input,Else,FOLLOW_Else_in_elseStat645); 

            match(input,Do,FOLLOW_Do_in_elseStat647); 

            pushFollow(FOLLOW_block_in_elseStat649);
            block();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "elseStat"



    // $ANTLR start "forStatement"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:150:1: forStatement : For Identifier '=' expression To expression Do block End ;
    public final void forStatement() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:151:2: ( For Identifier '=' expression To expression Do block End )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:151:4: For Identifier '=' expression To expression Do block End
            {
            match(input,For,FOLLOW_For_in_forStatement659); 

            match(input,Identifier,FOLLOW_Identifier_in_forStatement661); 

            match(input,Assign,FOLLOW_Assign_in_forStatement663); 

            pushFollow(FOLLOW_expression_in_forStatement665);
            expression();

            state._fsp--;


            match(input,To,FOLLOW_To_in_forStatement667); 

            pushFollow(FOLLOW_expression_in_forStatement669);
            expression();

            state._fsp--;


            match(input,Do,FOLLOW_Do_in_forStatement671); 

            pushFollow(FOLLOW_block_in_forStatement673);
            block();

            state._fsp--;


            match(input,End,FOLLOW_End_in_forStatement675); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "forStatement"



    // $ANTLR start "whileStatement"
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:154:1: whileStatement : While expression Do block End ;
    public final void whileStatement() throws RecognitionException {
        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:155:2: ( While expression Do block End )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:155:4: While expression Do block End
            {
            match(input,While,FOLLOW_While_in_whileStatement685); 

            pushFollow(FOLLOW_expression_in_whileStatement687);
            expression();

            state._fsp--;


            match(input,Do,FOLLOW_Do_in_whileStatement689); 

            pushFollow(FOLLOW_block_in_whileStatement691);
            block();

            state._fsp--;


            match(input,End,FOLLOW_End_in_whileStatement693); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "whileStatement"

    // Delegated rules


 

    public static final BitSet FOLLOW_block_in_parse33 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse35 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_block46 = new BitSet(new long[]{0x0042B00030808042L});
    public static final BitSet FOLLOW_functionDecl_in_block50 = new BitSet(new long[]{0x0042B00030808042L});
    public static final BitSet FOLLOW_Return_in_block55 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_block57 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_SColon_in_block59 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignment_in_statement71 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_SColon_in_statement73 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_statement78 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_SColon_in_statement80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStatement_in_statement87 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStatement_in_statement94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStatement_in_statement101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Def_in_functionDecl111 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_functionDecl113 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_OParen_in_functionDecl115 = new BitSet(new long[]{0x0000000010000800L});
    public static final BitSet FOLLOW_idList_in_functionDecl117 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_CParen_in_functionDecl120 = new BitSet(new long[]{0x0042B00030908040L});
    public static final BitSet FOLLOW_block_in_functionDecl122 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_End_in_functionDecl124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_idList135 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_Comma_in_idList138 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_idList140 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_Identifier_in_assignment152 = new BitSet(new long[]{0x0000010000000080L});
    public static final BitSet FOLLOW_indexes_in_assignment154 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_Assign_in_assignment157 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_assignment159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBracket_in_indexes170 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_indexes172 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_CBracket_in_indexes174 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_condExpr_in_expression186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpr_in_condExpr196 = new BitSet(new long[]{0x0000400040000002L});
    public static final BitSet FOLLOW_QMark_in_condExpr200 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_condExpr202 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Colon_in_condExpr204 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_condExpr206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_In_in_condExpr210 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_condExpr212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpr_in_orExpr224 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_Or_in_orExpr227 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_andExpr_in_orExpr229 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_equExpr_in_andExpr241 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_And_in_andExpr244 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_equExpr_in_andExpr246 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_relExpr_in_equExpr258 = new BitSet(new long[]{0x0000001000200002L});
    public static final BitSet FOLLOW_set_in_equExpr261 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_relExpr_in_equExpr269 = new BitSet(new long[]{0x0000001000200002L});
    public static final BitSet FOLLOW_addExpr_in_relExpr281 = new BitSet(new long[]{0x0000000303000002L});
    public static final BitSet FOLLOW_set_in_relExpr284 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_addExpr_in_relExpr300 = new BitSet(new long[]{0x0000000303000002L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr312 = new BitSet(new long[]{0x0010000000000012L});
    public static final BitSet FOLLOW_set_in_addExpr315 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr323 = new BitSet(new long[]{0x0010000000000012L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr335 = new BitSet(new long[]{0x0000000C00020002L});
    public static final BitSet FOLLOW_set_in_mulExpr338 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_powExpr_in_mulExpr350 = new BitSet(new long[]{0x0000000C00020002L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr362 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_Pow_in_powExpr365 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_unaryExpr_in_powExpr367 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_Subtract_in_unaryExpr379 = new BitSet(new long[]{0x000A336010000140L});
    public static final BitSet FOLLOW_atom_in_unaryExpr381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Excl_in_unaryExpr386 = new BitSet(new long[]{0x000A336010000140L});
    public static final BitSet FOLLOW_atom_in_unaryExpr388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_unaryExpr393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Null_in_atom403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Number_in_atom410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Bool_in_atom417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_lookup_in_atom424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionCall_in_lookup434 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_indexes_in_lookup436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OParen_in_lookup444 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_lookup446 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_CParen_in_lookup448 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_indexes_in_lookup450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_lookup458 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_indexes_in_lookup460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_lookup468 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_indexes_in_lookup470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_lookup478 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_indexes_in_lookup480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OBracket_in_list491 = new BitSet(new long[]{0x001A336010400540L});
    public static final BitSet FOLLOW_exprList_in_list493 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_CBracket_in_list496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_exprList506 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_Comma_in_exprList509 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_exprList511 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_Identifier_in_functionCall523 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall525 = new BitSet(new long[]{0x001A336010400940L});
    public static final BitSet FOLLOW_exprList_in_functionCall527 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_CParen_in_functionCall530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Println_in_functionCall537 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall539 = new BitSet(new long[]{0x001A336010400940L});
    public static final BitSet FOLLOW_expression_in_functionCall541 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_CParen_in_functionCall544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Print_in_functionCall551 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall553 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_functionCall555 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_CParen_in_functionCall557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Assert_in_functionCall564 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall566 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_functionCall568 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_CParen_in_functionCall570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Size_in_functionCall577 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_OParen_in_functionCall579 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_functionCall581 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_CParen_in_functionCall583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStat_in_ifStatement593 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_elseIfStat_in_ifStatement595 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_elseStat_in_ifStatement598 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_End_in_ifStatement601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_If_in_ifStat611 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_ifStat613 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_Do_in_ifStat615 = new BitSet(new long[]{0x0042B00030808040L});
    public static final BitSet FOLLOW_block_in_ifStat617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseIfStat627 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_If_in_elseIfStat629 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_elseIfStat631 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_Do_in_elseIfStat633 = new BitSet(new long[]{0x0042B00030808040L});
    public static final BitSet FOLLOW_block_in_elseIfStat635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Else_in_elseStat645 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_Do_in_elseStat647 = new BitSet(new long[]{0x0042B00030808040L});
    public static final BitSet FOLLOW_block_in_elseStat649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_For_in_forStatement659 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_forStatement661 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_Assign_in_forStatement663 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_forStatement665 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_To_in_forStatement667 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_forStatement669 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_Do_in_forStatement671 = new BitSet(new long[]{0x0042B00030908040L});
    public static final BitSet FOLLOW_block_in_forStatement673 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_End_in_forStatement675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_While_in_whileStatement685 = new BitSet(new long[]{0x001A336010400140L});
    public static final BitSet FOLLOW_expression_in_whileStatement687 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_Do_in_whileStatement689 = new BitSet(new long[]{0x0042B00030908040L});
    public static final BitSet FOLLOW_block_in_whileStatement691 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_End_in_whileStatement693 = new BitSet(new long[]{0x0000000000000002L});

}