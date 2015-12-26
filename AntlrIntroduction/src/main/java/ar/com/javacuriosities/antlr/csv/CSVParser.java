// $ANTLR 3.4 ar/com/javacuriosities/antlr/csv/CSVParser.g 2015-12-26 13:30:40

	package ar.com.javacuriosities.antlr.csv;
	
	import ar.com.javacuriosities.antlr.csv.ast.Record;
	import ar.com.javacuriosities.antlr.csv.ast.CSVFile;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class CSVParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "Comma", "LineBreak", "QuotedValue", "SimpleValue"
    };

    public static final int EOF=-1;
    public static final int Comma=4;
    public static final int LineBreak=5;
    public static final int QuotedValue=6;
    public static final int SimpleValue=7;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public CSVParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public CSVParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return CSVParser.tokenNames; }
    public String getGrammarFileName() { return "ar/com/javacuriosities/antlr/csv/CSVParser.g"; }



    // $ANTLR start "file"
    // ar/com/javacuriosities/antlr/csv/CSVParser.g:16:1: file returns [CSVFile file] : (record= row )+ EOF ;
    public final CSVFile file() throws RecognitionException {
        CSVFile file = null;


        Record record =null;



        		file = new CSVFile();
        	
        try {
            // ar/com/javacuriosities/antlr/csv/CSVParser.g:20:2: ( (record= row )+ EOF )
            // ar/com/javacuriosities/antlr/csv/CSVParser.g:20:4: (record= row )+ EOF
            {
            // ar/com/javacuriosities/antlr/csv/CSVParser.g:20:4: (record= row )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                switch ( input.LA(1) ) {
                case QuotedValue:
                case SimpleValue:
                    {
                    alt1=1;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // ar/com/javacuriosities/antlr/csv/CSVParser.g:20:5: record= row
            	    {
            	    pushFollow(FOLLOW_row_in_file34);
            	    record=row();

            	    state._fsp--;



            	    			file.add(record);
            	    		

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_file40); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return file;
    }
    // $ANTLR end "file"



    // $ANTLR start "row"
    // ar/com/javacuriosities/antlr/csv/CSVParser.g:28:1: row returns [Record record] : data= value ( Comma data= value )* ( LineBreak | EOF ) ;
    public final Record row() throws RecognitionException {
        Record record = null;


        String data =null;



        		// Este bloque es ejecutado antes de hacer el matching de cualquier rule
        		record = new Record();
        	
        try {
            // ar/com/javacuriosities/antlr/csv/CSVParser.g:33:4: (data= value ( Comma data= value )* ( LineBreak | EOF ) )
            // ar/com/javacuriosities/antlr/csv/CSVParser.g:33:6: data= value ( Comma data= value )* ( LineBreak | EOF )
            {
            pushFollow(FOLLOW_value_in_row66);
            data=value();

            state._fsp--;



            			record.add(data);
            		

            // ar/com/javacuriosities/antlr/csv/CSVParser.g:36:3: ( Comma data= value )*
            loop2:
            do {
                int alt2=2;
                switch ( input.LA(1) ) {
                case Comma:
                    {
                    alt2=1;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // ar/com/javacuriosities/antlr/csv/CSVParser.g:36:4: Comma data= value
            	    {
            	    match(input,Comma,FOLLOW_Comma_in_row74); 

            	    pushFollow(FOLLOW_value_in_row78);
            	    data=value();

            	    state._fsp--;



            	    			record.add(data);
            	    		

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            if ( input.LA(1)==EOF||input.LA(1)==LineBreak ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
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
        return record;
    }
    // $ANTLR end "row"



    // $ANTLR start "value"
    // ar/com/javacuriosities/antlr/csv/CSVParser.g:47:1: value returns [String value] : ( SimpleValue | QuotedValue );
    public final String value() throws RecognitionException {
        String value = null;


        Token SimpleValue1=null;
        Token QuotedValue2=null;

        try {
            // ar/com/javacuriosities/antlr/csv/CSVParser.g:48:2: ( SimpleValue | QuotedValue )
            int alt3=2;
            switch ( input.LA(1) ) {
            case SimpleValue:
                {
                alt3=1;
                }
                break;
            case QuotedValue:
                {
                alt3=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // ar/com/javacuriosities/antlr/csv/CSVParser.g:48:4: SimpleValue
                    {
                    SimpleValue1=(Token)match(input,SimpleValue,FOLLOW_SimpleValue_in_value111); 


                    		// Por medio del $[NombreDelToken] podemos acceder a los valores del mismo, el atributo "text" contiene el texto que genero ese token 
                    		value = (SimpleValue1!=null?SimpleValue1.getText():null);
                    	

                    }
                    break;
                case 2 :
                    // ar/com/javacuriosities/antlr/csv/CSVParser.g:53:4: QuotedValue
                    {
                    QuotedValue2=(Token)match(input,QuotedValue,FOLLOW_QuotedValue_in_value120); 


                           value = (QuotedValue2!=null?QuotedValue2.getText():null);
                           value = value.substring(1, value.length()-1); // Removemos las comillas del comienzo y final
                           value = value.replace("\"\"", "\""); // Reemplazamos todos los '""' por '"'
                    	

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
        return value;
    }
    // $ANTLR end "value"

    // Delegated rules


 

    public static final BitSet FOLLOW_row_in_file34 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_EOF_in_file40 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_row66 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_Comma_in_row74 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_value_in_row78 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_set_in_row87 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SimpleValue_in_value111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QuotedValue_in_value120 = new BitSet(new long[]{0x0000000000000002L});

}