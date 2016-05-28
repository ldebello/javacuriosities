package ar.com.javacuriosities.time;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;

/*
 * El paquete "java.time.temporal" provee un conjunto de interfaces para realizar cálculos sobre fechas, así
 * como obtener datos especificos de cada fecha
 * 
 * La interfaz "Temporal" y "TemporalAccessor" están definidas en función de propiedades definidas en "TemporalField", las
 * unidades para estas propiedades son "TemporalUnit", para realizar operaciones aritméticas sobre "Temporal" utilizamos
 * "TemporalAmount"
 * 
 * ChronoField implements TemporalField: Conjunto standard de propiedades (DAY_OF_MONTH, DAY_OF_YEAR, etc), estas propiedades están pensadas para multiples calendarios
 * ChronoUnit implements TemporalUnit: Conjunto standard de unidades (DAYS, MONTHS, YEARS)
 * 
 * IsoFields: Contiene propiedades y unidades especificas para ISO-8601
 * 
 * Otras dos clases que tienen propiedades especificas son WeekFields, JulianFields
 * 
 * Temporal Adjuster:
 * 
 * - La interfaz "TemporalAdjuster" define un método el cual recibe un "Temporal" sobre el cual realizar un ajuste y retorna 
 * el resultado de la operación
 * - La clase "TemporalAdjusters" contiene varios adjusters ya implementados
 * 
 * Temporal Query:
 * 
 * - La interfaz "TemporalQuery" define un método que recibe un "TemporalAccessor" al cual podemos pedirle información y devolver el
 * resultado de forma genérica
 * - La clase "TemporalQueries" contiene varios queries ya implementados
 */
public class Lesson10TemporalPackage {
	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		
		boolean isSupported = date.isSupported(ChronoField.HOUR_OF_DAY);
		System.out.println("LocalDate supports Hour of Day? " + isSupported);
		
		System.out.println("Date of month? " + date.get(ChronoField.DAY_OF_MONTH));
		System.out.println("Quarter of year? " + date.get(IsoFields.QUARTER_OF_YEAR));
		
		System.out.println("***Temporal Adjusters***");

		System.out.println("First day of Month: " + date.with(TemporalAdjusters.firstDayOfMonth()));
		System.out.println("First Monday of Month: " + date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
		System.out.println("Last day of Month: " + date.with(TemporalAdjusters.lastDayOfMonth()));
		System.out.println("First day of next Month: " + date.with(TemporalAdjusters.firstDayOfNextMonth()));
		System.out.println("First day of next Year: " + date.with(TemporalAdjusters.firstDayOfNextYear()));
		System.out.println("First day of Year: " + date.with(TemporalAdjusters.firstDayOfYear()));
		

		System.out.println("***Custom Adjusters***");
		// Dado que "TemporalAdjuster" es una Functional Interface podes usar lambdas para definir nuestros propios adjusters
		System.out.println("Seven day of Month: " + date.with(t -> t.with(ChronoField.DAY_OF_MONTH, 7)));
		
		System.out.println("***Temporal Queries***");
		// Esta query retorna el "ChronoUnit" mas chico posible para el tipo solicitado
		TemporalQuery<TemporalUnit> query = TemporalQueries.precision();
		
		System.out.println("LocalDate precision is: " + LocalDate.now().query(query));
		System.out.println("LocalDateTime precision is: " + LocalDateTime.now().query(query));
		System.out.println("Year precision is: " + Year.now().query(query));
		System.out.println("YearMonth precision is: " + YearMonth.now().query(query));
		System.out.println("Instant precision is: " + Instant.now().query(query));
		
		System.out.println("Is Monday: " + LocalDate.now().query(t -> t.get(ChronoField.DAY_OF_WEEK) == DayOfWeek.MONDAY.getValue()));
	}
}
