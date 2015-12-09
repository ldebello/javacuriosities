package ar.com.javacuriosities.time;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/*
 * La clase Clock nos permite obtener momento precisos en una línea de tiempo (Instant). Esta clase tiene en cuenta el timezone, se puede
 * usar como reemplazo para "System.currentTimeMillis()", además los Instants pueden ser usados para crear las clases legacy del manejo de fechas.
 * 
 * La clase Instant representa un momento preciso en una línea de tiempo, se define un punto de origen, también llamado epoch, el cual es Jan. 1st 1970 - 00:00 - Greenwhich mean time (GMT)
 */
public class Lesson01Clock {
	public static void main(String[] args) throws Exception {
		Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		System.out.println("Time in millis: " + millis);
		
		Instant instant = clock.instant();
		
		System.out.println("Instant from clock: " + instant);
		System.out.println("Instant from factory method: " + Instant.now());
		
		// Podemos utilizar este método para obtener clases basadas en momento precisos
		Date legacyDate = Date.from(instant);
		System.out.println("Legacy Date: " + legacyDate);
	}
}
