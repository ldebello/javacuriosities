package ar.com.javacuriosities.time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/*
 * Duration: Mide una cantidad de tiempo usando time units (Hours, Minutes, Seconds, Nanoseconds)
 * Period: Mide una cantidad de tiempo usando date units (Year, Month, Day)
 *
 * Es importante notar que el Duration no esta conectado al timeline por lo cual si agregamos un Duration equivalente a un día
 * esto es agregar 24HS ya que no tiene en cuenta el daylight o otras diferencias
 *
 * ChronoUnit es un Enum el cual define las distintas unidades de tiempo y algunos métodos útiles
 */
public class Lesson07PeriodAndDuration {
	public static void main(String[] args) throws Exception {
		// Duration
		Instant start = Instant.now();
		Instant end = Instant.now();
		
		long nanoseconds = Duration.between(start, end).toNanos();
		
		System.out.println("Start: " + start);
		System.out.println("End: " + end);
		System.out.println("Diff: " + nanoseconds);
		
		Duration gap = Duration.ofSeconds(10);
		Instant later = start.plus(gap);
		System.out.println("Start + 10s : " + later);
		
		// Period
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(1985, Month.MAY, 6);

		Period period = Period.between(birthday, today);
		long chronoUnit = ChronoUnit.DAYS.between(birthday, today);
		System.out.println("You are " + period.getYears() + " years, " + period.getMonths() + " months, and " + period.getDays() + " days old. (" + chronoUnit + " days total)");
	}
}
