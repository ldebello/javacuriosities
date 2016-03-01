// $ANTLR 3.4 ar/com/javacuriosities/tiny/grammar/Tiny.g 2016-01-24 21:44:56

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
    // ar/com/javacuriosities/tiny/grammar/Tiny.g:11:1: parse : (currentToken= . )* EOF ;
    public final void parse() throws RecognitionException {
        Token currentToken=null;

        try {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:12:2: ( (currentToken= . )* EOF )
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:12:4: (currentToken= . )* EOF
            {
            // ar/com/javacuriosities/tiny/grammar/Tiny.g:12:4: (currentToken= . )*
            loop1:
            do {
                int alt1=2;
                switch ( input.LA(1) ) {
                case Add:
                case And:
                case Assert:
                case Assign:
                case Bool:
                case CBrace:
                case CBracket:
                case CParen:
                case Colon:
                case Comma:
                case Comment:
                case Def:
                case Digit:
                case Divide:
                case Do:
                case Else:
                case End:
                case Equals:
                case Excl:
                case For:
                case GT:
                case GTEquals:
                case HexChar:
                case HexDigit:
                case Identifier:
                case If:
                case In:
                case Int:
                case LT:
                case LTEquals:
                case Modulus:
                case Multiply:
                case NEquals:
                case Null:
                case Number:
                case OBrace:
                case OBracket:
                case OParen:
                case Or:
                case Pow:
                case Print:
                case Println:
                case QMark:
                case Return:
                case SColon:
                case Size:
                case Space:
                case String:
                case Subtract:
                case To:
                case While:
                    {
                    alt1=1;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // ar/com/javacuriosities/tiny/grammar/Tiny.g:12:5: currentToken= .
            	    {
            	    currentToken=(Token)input.LT(1);

            	    matchAny(input); 


            	    			System.out.printf("Text: %-7s Type: %s \n",(currentToken!=null?currentToken.getText():null), tokenNames[(currentToken!=null?currentToken.getType():0)]);
            	    		

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_parse41); 

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

    // Delegated rules


 

    public static final BitSet FOLLOW_EOF_in_parse41 = new BitSet(new long[]{0x0000000000000002L});

}