package ar.com.javacuriosities.time;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/*
 * Month es un Enum que contiene los 12 meses, el rango va de 1-12 y provee métodos auxiliares
 */
public class Lesson02Month {
	public static void main(String[] args) throws Exception {
		System.out.println("January:" + Month.JANUARY);
		System.out.println("January + 3:" + Month.JANUARY.plus(3));
		System.out.println("January - 3:" + Month.JANUARY.minus(3));
		
		Month month = Month.JANUARY;
		Locale locale = Locale.getDefault();
		System.out.println(month.getDisplayName(TextStyle.FULL, locale));
		System.out.println(month.getDisplayName(TextStyle.NARROW, locale));
		System.out.println(month.getDisplayName(TextStyle.SHORT, locale));
	}
}
