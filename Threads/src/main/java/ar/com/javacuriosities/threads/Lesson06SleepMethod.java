package ar.com.javacuriosities.threads;

public class Lesson06SleepMethod {

	public static void main(String[] args) {
		/*
		 * Creamos dos threads lo cuales podemos indicar por medio
		 * de un boolean en su constructor si esta cansado o no, si
		 * esta cansado el thread se dormirá por 5000 ms o sea 5 segundos
		 */
		Thread worker01 = new Worker("Worker-01", true);
		Thread worker02 = new Worker("Worker-02", false);

		/*
		 * Iniciamos los dos threads, dado que el energetic man empieza
		 * primero usualmente será el que termine primero, pero si indicamos
		 * que esta cansado ese thread se dormirá por 5 segundos y eso afecta directamente 
		 * cuando termina
		 */
		worker01.start();
		worker02.start();
	}

	private static final class Worker extends Thread {

		private boolean needSleep;

		public Worker(String name, boolean needSleep) {
			super(name);
			this.needSleep = needSleep;
		}

		@Override
		public void run() {
			System.out.println("Thread Name: " + getName());
			if (needSleep) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					/*
					 * Puede ser que nuestro thread este dormido y alguien
					 * ejecute el método interrupt() lo cual va a generar
					 * esta exception la cual es chequeada para que dejemos 
					 * los datos de forma consistente
					 */
					// Log and Handle exception
					e.printStackTrace();
				}
			}
			System.out.println(getName() + " is done");
		}
	}
}