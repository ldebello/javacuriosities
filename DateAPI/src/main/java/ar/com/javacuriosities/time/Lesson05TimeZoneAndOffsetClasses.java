package ar.com.javacuriosities.time;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/*
 * Time zone es una region del planeta donde el mismo standard time es usado
 * Cada time zone esta descrito por un identificador y usualmente el formato es region/ciudad (Asia/Tokyo) y un offset desde Greenwich/UTC, el offset de Tokyo es +09:00
 * 
 * El API ofrece dos clases básicas para el manejo de time zone
 * 	- ZoneId
 * 	- ZoneOffset
 * 
 * Tenemos tres clases especificas para el manejo de fechas con time zone
 * 	- ZonedDateTime: Combina LocalDateTime con ZoneId, es usada para representar una fecha completa con time zone incluido
 * 	- OffsetDateTime: Combina LocalDateTime con ZoneOffset, es usada para representar una fecha completa con un offset desde el UTC (Coordinated Universal Time)
 * 	- OffsetTime: Combina LocalTime con ZoneOffset, es usada para representar un tiempo especifico (Hora, Minutos, Segundos, Nanosegundos) con un offset desde el UTC
 */
public class Lesson05TimeZoneAndOffsetClasses {
	public static void main(String[] args) throws Exception {
		LocalDateTime currentLocalDateTime = LocalDateTime.now();
		
		Set<String> allZones = ZoneId.getAvailableZoneIds();
		List<String> zoneList = new ArrayList<String>(allZones);
		Collections.sort(zoneList);

		System.out.println("Original: " + currentLocalDateTime);
		for (String zone : zoneList) {
		    ZoneId zoneId = ZoneId.of(zone);
		    ZonedDateTime zonedDateTime = currentLocalDateTime.atZone(zoneId);
		    ZoneOffset offset = zonedDateTime.getOffset();
		    
		    if (offset.getTotalSeconds() != 0) {
		    	System.out.println(offset + " -> " + zonedDateTime);
		    }
		}
		
		// ZonedDateTime
		LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
		ZoneId leavingZone = ZoneId.of("America/Los_Angeles"); 
		ZonedDateTime departureFlight = ZonedDateTime.of(leaving, leavingZone);
		
		// El vuelo dura 10 horas y 50 minutos, el total es 650 minutos
		ZoneId arrivingZone = ZoneId.of("Asia/Tokyo"); 
		ZonedDateTime arrivalFlight = departureFlight.withZoneSameInstant(arrivingZone).plusMinutes(650);
		
		System.out.println("Departure at America/Los_Angeles: " + departureFlight);
		System.out.println("Current Time at Asia/Tokyo: " + departureFlight.withZoneSameInstant(arrivingZone));
		System.out.println("Arrival: " + arrivalFlight);
		
		// OffsetDateTime
		LocalDateTime localDate = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
		ZoneOffset offset = ZoneOffset.of("-08:00");

		OffsetDateTime offsetDate = OffsetDateTime.of(localDate, offset);
		System.out.println("Date based on offset: "  + offsetDate);
	}
}