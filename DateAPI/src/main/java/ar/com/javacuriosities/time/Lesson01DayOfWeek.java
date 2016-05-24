package ar.com.javacuriosities.time;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

/*
 * DayOfWeek es un Enum que contiene los siete días de la semana, el rango va de 1-7 y provee métodos auxiliares.
 * 
 * Podemos utilizar el método getDisplayName para utilizar el Locale que deseemos. Este método recibe un TextStyle
 * 
 * - TextStyle.FULL: Nombre completo
 * - TextStyle.NARROW: Típicamente una sola letra
 * - TextStyle.SHORT: Abreviación
 * 
 * Las versiones STANDALONE, es utilizada en algunos lenguajes donde el output puede variar dependiendo si la fecha esta sola
 * o es usada como parte de algo
 */
public class Lesson01DayOfWeek {
	public static void main(String[] args) throws Exception {
		System.out.println("Monday:" + DayOfWeek.MONDAY);
		System.out.println("Monday + 3:" + DayOfWeek.MONDAY.plus(3));
		
		DayOfWeek dow = DayOfWeek.MONDAY;
		Locale locale = Locale.getDefault();
		System.out.println(dow.getDisplayName(TextStyle.FULL, locale));
		System.out.println(dow.getDisplayName(TextStyle.NARROW, locale));
		System.out.println(dow.getDisplayName(TextStyle.SHORT, locale));
	}
}
