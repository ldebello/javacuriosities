package ar.com.javacuriosities.signal;

import java.util.concurrent.TimeUnit;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/*
 * La JVM por defecto maneja algunas señales de forma automatica o utiliza las mismas
 * para realizar ciertas optimizaciones.
 *
 * Ejemplos:
 *  1- Podemos obtener un Thread dump ejecutando kill -3 (SIGQUIT)
 *  2- La JVM utiliza signal handling para implementar una optimizacion llamada Null Check Elimination
 *
 * Existe la posibilidad de implementar nuestro propio signal handler aunque para poder hacerlo debemos utilizar clases
 * del paquete "sun.misc" las cuales son API internas que limitan la portabilidad de nuestro codigo.
 *
 * Un posible uso de un custom signal handler es poder tener una forma simple de obtener informacion de nuestro proceso
 * y mostrarla cuando la signal es invocada.
 *
 */
public class SignalHandling {

	public static void main(String[] args) {
		try {
			CustomSignalHandler customSignalHandler = new CustomSignalHandler();

			// Handling kill -5
			Signal.handle(new Signal("TRAP"), customSignalHandler);

			Thread.sleep(TimeUnit.MINUTES.toMillis(1));
		} catch (InterruptedException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static class CustomSignalHandler implements SignalHandler {
		public CustomSignalHandler() {
		}

		@Override
		public void handle(Signal signal) {
			System.out.println("Custom Signal Handling");
		}
	}
}