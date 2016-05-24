package ar.com.javacuriosities.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;

/*
 * LocalTime representa información sobre hora, minuto, segundos en "Human terms"
 * LocalDateTime representa información sobre la fecha (Month-Day-Year) así como del tiempo (Hour-Minute-Second-Nanosecond) 
 */
public class Lesson04DateAndTimeClasses {
	public static void main(String[] args) throws Exception {
		// LocalTime
		LocalTime localTime = LocalTime.now();

		System.out.println("Hour: " + localTime.getHour());
		System.out.println("Minutes: " + localTime.getMinute());
		System.out.println("Seconds: " + localTime.getSecond());
		System.out.println("Nanoseconds: " + localTime.getNano());
		
		// LocalDateTime
		System.out.printf("Now: %s%n", LocalDateTime.now());

		System.out.printf("Apr 15, 1994 @ 11:30am: %s%n", LocalDateTime.of(1994, Month.APRIL, 15, 11, 30));

		System.out.printf("Now (from Instant): %s%n", LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));

		System.out.printf("6 months from now: %s%n", LocalDateTime.now().plusMonths(6));

		System.out.printf("6 months ago: %s%n", LocalDateTime.now().minusMonths(6));
	}
}