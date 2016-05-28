package ar.com.javacuriosities.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/*
 * La clase DateTimeFormatter nos permite configurar formatos validos para el parseo o formateo de una fecha, además
 * contiene varios formatos predefinidos.
 * Esta clase es immutable y thread safe
 * 
 * Parse: Operación para convertir un String en una Fecha
 * Format: Operación para convertir un Fecha en una String
 */
public class Lesson09ParseAndFormat {
	public static void main(String[] args) {
		String input = "20100102";

		// Parseamos un String a un Fecha con el formato básico yyyyMMdd
		LocalDate basicIsoDate = LocalDate.parse(input, DateTimeFormatter.BASIC_ISO_DATE);

		System.out.println(basicIsoDate);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
		
		input = "Jan 5 2014";
		LocalDate date = LocalDate.parse(input, formatter);
		System.out.println(date);

		LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
		ZoneId leavingZone = ZoneId.of("America/Los_Angeles"); 
		ZonedDateTime departureFlight = ZonedDateTime.of(leaving, leavingZone);
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
	    String departureTime = departureFlight.format(format);
	    System.out.println("Leaving at: " + departureTime + " (" + leavingZone + ")");
	}
}
