package ar.com.javacuriosities.threads;

/*
 * Interrumpir un Thread es un trabajo en conjunto, dado que el interesado debe ejecutar el método interrupt(), pero
 * el Thread debe manejar esa señal de forma apropiada.
 * 
 * Java nos provee de la exception InterruptedException la cual puede ser un poco confusa al principio.
 * Cada Thread tiene un flag el cual indica su estado de interrupción, este puede ser manipulado por los siguientes métodos
 * 
 * Thread#interrupt(): Marca el Thread como interrumpido
 * Thread#interrupted(): Informa el estado del Thread y hace un reset del flag
 * Thread#isInterrupted(): Verifica el estado de interrupción
 * 
 * Si queremos interrumpir el Thread el método interrupt(), puede comportarse distinto en base al estado del Thread
 * 
 * Thread Status:
 * 
 * RUNNABLE: El Thread esta ejecutando o es elegible para ser ejecutado
 * BLOCKED, WAITING, TIMED_WAITING: En este estado el Thread no esta haciendo progreso, pero si alguien ejecuta el interrupt(), se arroja la exception InterruptedException y el estado de interrupción es reiniciado
 * 
 * Dado que en el caso de InterruptedException el estado de interrupción es reiniciado esta exception puede ser maneja de distinta maneras
 * 
 * Rethrow: Clases que implementan operaciones bloqueantes como BlockingQueue, hacen retrow de la exception
 * Catch, restore and terminate: Las clases que implementan una interfaz existente como Runnable no pueden hacer el rethrow de la exception, por lo cual necesitan comunicar la interrupción de otra manera, se debe hacer el catch InterruptedException, restaurar el status de interrupción y terminar tan pronto como sea posible
 * Catch and terminate: Esta es una variación de la estrategia anterior. Subclases de Thread no necesitan hacer el restore del estado de interrupción porque están en la parte superior del Stack
 */
public class Lesson12InterruptMethod {

	public static void main(String[] args) {
		Runnable heavyTask = new Task();
		Thread thread = new Thread(heavyTask);
		thread.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// Log and Handle exception
			e.printStackTrace();
		}
		System.out.println("Main Thread interrupting another Thread");
		thread.interrupt();
		System.out.println("Finishing Main");
	}

	private static final class Task implements Runnable {
		@Override
		public void run() {
			try {
				System.out.println("Before running - work()");
				work();
				System.out.println("After running -  work()");
			} catch (InterruptedException e) {
				// Log and Handle exception
				System.out.println("work() Method has been interrupted");
				// Restore interruption status of the corresponding thread
		        Thread.currentThread().interrupt();
			}
			System.out.println("Finishing run()");
		}

		public void work() throws InterruptedException {
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println("Working...");
				Thread.sleep(500);
			}
		}
	}
}