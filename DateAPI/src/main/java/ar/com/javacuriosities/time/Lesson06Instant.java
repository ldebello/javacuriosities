package ar.com.javacuriosities.time;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/*
 * Instant es una de las clases core del nuevo API, es util para generar un time stamp para representar "machine time"
 */
public class Lesson06Instant {
	public static void main(String[] args) {
		Instant timestamp = Instant.now();
		long secondsFromEpoch = Instant.ofEpochSecond(0L).until(Instant.now(), ChronoUnit.SECONDS);
		
		System.out.println(Instant.ofEpochSecond(0L));
		System.out.println(timestamp);
		System.out.println(secondsFromEpoch);
	}
}
