package ar.com.javacuriosities.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/*
 * La clase java.util.Date contiene información sobre Date, Time y Time zone entonces a la hora
 * de hacer la conversion a la nueva API debemos ver que información queremos usar.
 * 
 *  La clase Date fue extendida con un método "toInstant" para realizar estas conversiones, además
 *  se le agrego el método from para las conversiones en sentido inverso
 */
public class Lesson11Conversion {
	public static void main(String[] args) {
		Date oldDate = new Date();

		System.out.println("***java.util.Date to java.time.LocalDateTime***");
		LocalDateTime localDateTime = LocalDateTime.ofInstant(oldDate.toInstant(), ZoneId.systemDefault());

		System.out.println("Old Date: " + oldDate);
		System.out.println("New Local Date Time: " + localDateTime);

		System.out.println("***java.util.Date to java.time.LocalDate***");
		LocalDate localDate = LocalDateTime.ofInstant(oldDate.toInstant(), ZoneId.systemDefault()).toLocalDate();

		System.out.println("Old Date: " + oldDate);
		System.out.println("New Local Date: " + localDate);
		
		System.out.println("***java.time.LocalDateTime to java.util.Date***");
		LocalDateTime localDateTimeToConvert = LocalDateTime.now();
		oldDate = Date.from(localDateTimeToConvert.atZone(ZoneId.systemDefault()).toInstant());

		System.out.println("Old Date: " + oldDate);
		System.out.println("New Local Date Time: " + localDateTimeToConvert);
		
		System.out.println("***java.time.LocalDate to java.util.Date***");
		LocalDate localDateToConvert = LocalDate.now();
		oldDate = Date.from(localDateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		System.out.println("Old Date: " + oldDate);
		System.out.println("New Local Date: " + localDateToConvert);
	}
}
