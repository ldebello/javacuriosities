package ar.com.javacuriosities.time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/*
 * Clock es utilizada para asegurarnos que un date/time es creado con el correcto time zone, además
 * esta pensada con facilidades para el testing como el método "fixed" que nos permite definir una fecha especifica
 */
public class Lesson08Clock {
	public static void main(String[] args) {
		Clock systemClock = Clock.systemDefaultZone();
		Clock utcClock = Clock.systemUTC();
		Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
		
		LocalDateTime systemLocalDateTime = LocalDateTime.now(systemClock);
		LocalDateTime utcLocalDateTime = LocalDateTime.now(utcClock);
		Instant instant = fixedClock.instant();
		
		System.out.println(systemLocalDateTime);
		System.out.println(utcLocalDateTime);
		System.out.println(instant);
	}
}
