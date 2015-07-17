package ar.com.javacuriosities.threads;

public class Lesson06SleepMethod {

	public static void main(String[] args) {
		/*
		 * Creamos dos threads lo cuales podemos indicar por medio
		 * de un boolean en su constructor si esta cansado o no, si
		 * esta cansado el thread se dormirá por 5000 ms o sea 5 segundos
		 */
		Thread energeticMan = new Worker("Worker-01", true);
		Thread tiredMan = new Worker("Worker-02", false);

		/*
		 * Iniciamos los dos threads, dado que el energetic man empieza
		 * primero usualmente será el que termine primero, pero si indicamos
		 * que esta cansado ese thread se dormirá por 5 segundos y eso afecta directamente 
		 * cuando termina
		 */
		energeticMan.start();
		tiredMan.start();
	}

	private static class Worker extends Thread {

		private boolean isTired;

		public Worker(String name, boolean isTired) {
			super(name);
			this.isTired = isTired;
		}

		@Override
		public void run() {
			System.out.println("I am " + getName());
			if (isTired) {
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
