package ar.com.javacuriosities.concurrency.time_unit;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.TimeUnit;

/*
 * TimeUnit: Es una abstracción de las medidas de tiempo, nos permite usar sleep con 
 * una medida de tiempo o hacer conversiones entre las distintas unidades de tiempo
 */
public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Sleeping 2 seconds...");
            
            // Usando algún tipo del Enum TimeUnit ejecutamos un sleep
            SECONDS.sleep(2);
            
            System.out.println("Continue");
            
            // Obtenemos una unidad de tiempo
            TimeUnit timeUnit = TimeUnit.HOURS;
            
            // Calculamos la cantidad de horas incluidas en 5 días
            System.out.println("5 days are: " + timeUnit.convert(5, TimeUnit.DAYS) + " hours");
        } catch (InterruptedException e) {
        	// Log and Handle exception
        	e.printStackTrace();
        }
    }
}