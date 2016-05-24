package ar.com.javacuriosities.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;

/*
 * LocalDate representa Year-Month-Day en el calendario ISO
 * YearMonth representa el mes en un año especifico
 * MonthDay representa un día en un mes especifico
 * Year representa un año especifico
 */
public class Lesson03DateClasses {
	public static void main(String[] args) throws Exception {
		// LocalDate
		LocalDate date = LocalDate.of(2016, Month.JANUARY, 1);
		DayOfWeek dow = date.getDayOfWeek();
		
		System.out.println("Local Date: " + date);
		System.out.println("Day Of Week: " + dow);
		
		// YearMonth
		YearMonth currentYearMonth = YearMonth.now();
		System.out.printf("Current Year -> %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());

		YearMonth yearMonthNonLeapYear = YearMonth.of(2010, Month.FEBRUARY);
		System.out.printf("Non Leap Year -> %s: %d%n", yearMonthNonLeapYear, yearMonthNonLeapYear.lengthOfMonth());

		YearMonth yearMonthLeapYear = YearMonth.of(2012, Month.FEBRUARY);
		System.out.printf("Leap Year -> %s: %d%n", yearMonthLeapYear, yearMonthLeapYear.lengthOfMonth());
		
		// MonthDay
		MonthDay dayOfFebruary = MonthDay.of(Month.FEBRUARY, 29);
		
		System.out.println("2010 is leap year: " + dayOfFebruary.isValidYear(2010));
		System.out.println("2012 is leap year: " + dayOfFebruary.isValidYear(2012));
		System.out.println("MonthDay in 2010: " + dayOfFebruary.atYear(2010));
		System.out.println("MonthDay in 2012: " + dayOfFebruary.atYear(2012));
		
		// Year
		Year initialYear = Year.of(2012);
		Year finalYear = Year.of(2010);
		
		System.out.println("Is leap year: " + Year.isLeap(2010));
		System.out.println("2012 > 2010: " + initialYear.isAfter(finalYear));
		
	}
}
