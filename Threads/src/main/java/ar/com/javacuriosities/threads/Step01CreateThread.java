package ar.com.javacuriosities.threads;

/*
 * Utilizaremos Threads cuando deseamos ejecutar tareas de forma concurrente y/o paralela, 
 * para eso primero debemos definir estos términos.
 * 
 * Concurrencia:
 * 		Hay concurrencia cuando varias tareas existen al mismo tiempo.
 * 
 * Paralelismo:
 * 		Hay paralelismo cuando varias tareas se ejecutan al mismo tiempo, o sea la concurrencia es necesaria para el paralelismo.
 * 
 * Nuestras PC pueden ejecutar de forma paralela hasta la cantidad maxima de nucleos que tengamos
 * ya sean físicos o virtuales.
 * 
 * Podemos obtener la cantidad de procesadores disponible por medio de 
 * Runtime.getRuntime().availableProcessors()
 * 
 * En general las computadoras modernas y comunes suelen venir con un microprocesador el cual
 * tiene uno o mas núcleo, luego en base a si el chip provee tecnología de virtualización esos núcleos se pueden
 * duplicar creando virtuales.
 * 
 * O sea cada nucleo puede ejecutar un hilo a la vez, dependiendo del tipo de tarea que estemos ejecutando convendrá
 * usar diferente cantidad de hilos, en general si son tareas largas CPU Intensive intentaremos mantener el numero de hilos
 * bajos, y si son tareas con mucho I/O podremos crear mas hilos ya que se pueden seguir procesando cosas mientras se espera por el I/O.
 * Si tuviéramos que hacer performance de nuestra aplicación habrá que hacer mediciones y ajustar la configuración acorde al caso, vale aclarar que no existe a rule of thumb
 * 
 * Durante la ejecución de un thread se le asigna un quantum o time slice para que el thread se ejecute, luego de ese tiempo o si el thread es bloqueado o dormido, se cambiara
 * a otro thread o al mismo, esto es manejado por el scheduler del sistema operativo
 */
public class Step01CreateThread {

	public static void main(String[] args) {
		// Creamos un thread usando una clase la cual extiende Thread
		Thread thread01 = new MyThread();

		// Creamos un thread proporcionándole la implementación de la tarea a ejecutar
		Thread thread02 = new Thread(new MyRunnable());

		/*
		 * Ejecutamos el método start() de cada thread, lo cual va a preparar todo la infraestructura 
		 * para que este thread cree su contexto e inicie su ejecución, estos threads pasan a ser planificados y 
		 * ejecutados por la JVM.
		 */
		thread01.start();
		thread02.start();
	}
	
	/*
	 * Podemos crear un thread extendiendo de la clase Thread
	 * podemos usar esta forma cuando necesitamos manejar el ciclo de vida del Thread
	 * así como chequear el estado del mismo
	 */
	private static class MyThread extends Thread {
		
		/*
		 * Para personalizar el código cuando heredamos de Thread debemos
		 * sobreescribir el método run()
		 */
		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				System.out.println("Executing logic from Thread");
			}
		}
	}
	
	/*
	 * Podemos implementar la interfaz Runnable la cual contiene el método run() donde
	 * definimos la lógica de la tarea a ser ejecutada por el Thread.
	 * Esta opción nos ofrece la posibilidad de desacoplar la lógica de la tarea de si
	 * esto se ejecuta de forma concurrente o no
	 */
	private static class MyRunnable implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				System.out.println("Executing logic from Runnable");
			}
		}
		
	}
}