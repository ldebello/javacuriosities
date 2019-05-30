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
 * Nuestras PC pueden ejecutar de forma paralela hasta la cantidad máxima de núcleos que tengamos
 * ya sean físicos o virtuales.
 * 
 * Podemos obtener la cantidad de procesadores disponible por medio de 
 * Runtime.getRuntime().availableProcessors()
 * 
 * En general las computadoras modernas y comunes suelen venir con un microprocesador el cual
 * tiene uno o mas núcleo, luego en base a si el chip provee tecnología de virtualización esos núcleos se pueden
 * duplicar creando virtuales.
 * 
 * O sea cada núcleo puede ejecutar un hilo a la vez, dependiendo del tipo de tarea que estemos ejecutando convendrá
 * usar diferente cantidad de hilos, en general si son tareas largas CPU Intensive intentaremos mantener el numero de hilos
 * bajos, y si son tareas con mucho I/O podremos crear mas hilos ya que se pueden seguir procesando cosas mientras se espera por el I/O.
 * Si tuviéramos que hacer performance de nuestra aplicación habrá que hacer mediciones y ajustar la configuración acorde al caso, vale aclarar que no existe a rule of thumb
 * 
 * Durante la ejecución de un thread se le asigna un quantum o time slice para que el thread se ejecute, luego de ese tiempo o si el thread es bloqueado o dormido, se cambiara
 * a otro thread o al mismo, esto es manejado por el scheduler del sistema operativo
 * 
 * Una pregunta típica que suele surgir es How many threads?
 * 
 * Esto no es fácil de responder y depende del tipo de tarea que estemos haciendo, o sea CPU Intensive o IO Intensive. 
 * 
 * CPU Bound: Significa que una tarea es limitada por la velocidad del CPU. Una tarea que hace cálculos sobre un grupo pequeño de numeros, por ejemplo una multiplicación de matrices, puede ser limitada por el CPU.
 * 
 * I/O Bound: significa que una tarea es limitada por la velocidad de I/O. Una tarea que necesita leer datos del disco, por ejemplo contar las líneas de un archivo, puede ser limitada por I/O.
 * 
 * Cuando empezamos a investigar suele ser común encontrar información sobre Little’s Law, el cual enuncia
 * 
 * L = λ W
 * 
 * L = Numero promedio de tareas en curso
 * λ = Tiempo promedio de arribo de cada tarea
 * W = Tiempo promedio de cada tarea
 * 
 * Si tenemos 10 tareas por segundo y cada una toma 1 segundo, necesitaríamos de 10 Threads (L = 10 * 1 = 10)
 * Si tenemos 10 tareas por segundo y cada una toma 2 segundos, necesitaríamos de 20 Threads (L = 10 * 2 = 20)
 *
 * Otra posible formula para calcular el numero de threads es:
 *
 * Nthreads = Ncpu * Ucpu * (1 + W/C)
 *
 * Ncpu = number of CPU cores
 * Ucpu = desired CPU utilization, 0 < Ucpu <= 1
 * W/C = ratio of wait time to compute time
 *
 * Tareas CPU Bound tendrían un numero cercano a 0 en la relación W/C (1 + 0)
 * Tareas I/O Bound tendrían un numero mayor en la relación W/C (1 + 75/25)
 *
 * Es importante recordar que estas son cosas generales y para nuestro sistema la mejor herramienta es medir y poder analizar que numero de Threads se ajusta mejor a nuestra solución.
 *
 * La JVM tiene alguno hilos por default que veremos a la hora de hacer un Thread Dump:
 *
 * - DestroyJavaVM: Es el thread encargado de descargar nuestra JVM cuando el programa termine.
 * - Signal Dispatcher: Es el thread que manejas las signals nativas enviadas por el OS a la JVM
 * - Finalizer: Thread que hace pull de los objetos desde la finalization queue y ejecuta su método finalize.
 * - Reference Handler: Es un thread de alta prioridad que se encarga de ir encolando las referencias pendientes.
 *
 *
 * Sockets vs Chips vs Core
 *
 * - Socket: Es el conector físico en el mother que acepta un chip físico. Muchos motherboards tienen soporte para multiples chips.
 * - CPU chip: Se refiere al circuito integrado físico. Este contiene el System Bus que comunica a los distintos núcleos. Un CPU chip puede contener multiples núcleos.
 * - Core: Pueden ejecutar threads de forma independiente, cada core físico tiene una ALU (Arithmetic Logic Unit).
 * - LCPU:
 * 	- Un core físico puede tener 1 o 2 LCPU (Logical CPU).
 * 	- Cada LCPU puede ejecutar 1 thread.
 * 	- Si tenemos mas de 1 LCPU por core esto es gracias al Hyper-threading.
 * 	- Cada LCPU tiene sus propios registros.
 * 	- Las memorias cache L1, L2, L3 pueden ser propias de cada LCPU o alguna puede estar compartida.
 *
 * UMA & NUMA Memory Architecture:
 *
 * - Uniform Memory Access: Cada logical core tiene acceso uniforme a la memoria (Symmetric Multiprocessor - SMP). Multiples core interactúan con la memoria por medio de un bus.
 * - Non-uniform Memory Access: El tiempo de acceso a la memoria depende de la location de los datos, el acceso local es mas rápido y es mas fácil de escalar que SMP. Un conjunto de core comparte un bus para acceder a la memoria y otro conjunto de cores comparte otro bus.
 */
public class Lesson01CreateThread {

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