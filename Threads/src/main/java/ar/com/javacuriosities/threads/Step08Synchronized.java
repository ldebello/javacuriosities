package ar.com.javacuriosities.threads;

/*
 * Cuando usamos sincronización es para asegurarnos la exclusion de multiples
 * objetos trabajando sobre los mismos datos.
 * 
 * En nuestro ejemplo los datos serían la BankAccount y nos referimos al mismo dato
 * porque ambos Customer interactúan con la misma instancia.
 * Si ejecutamos este código y vemos los println veremos que la información no tiene mucho sentido
 * y es incorrecta y los resultados pueden ser distintos en base al orden de ejecución.
 * 
 * Esto se conoce como Race Condition lo cual ocurre cuando dos o mas procesos acceden a un recurso compartido sin control, 
 * de manera que el resultado combinado de este acceso depende del orden de llegada.
 * 
 * Cada objeto tiene lo que se conoce como un lock/monitor/candado el cual podemos usar para controlar el acceso a recursos
 * compartidos la forma mas básica de esto es cuando mencionamos la keyword synchronized en la firma del método
 * lo cual va a generar que solo un thread a la vez pueda interactuar con ese método
 * 
 * Existen varias formas de sincronización:
 * 
 * 1- Sincronizando el recurso que estamos usando, esto lo podemos hacer con synchronized en la firma del método o 
 * synchronized(this) lo cual es exactamente lo mismo, ponerla en la firma es simplemente azúcar sintáctico
 * 
 * 2- Sincronizando por medio de un mutex (Nombre típico de la variable que viene de Mutual Exclusion), que se refiere
 * a usar del candado de ese objeto haciendo synchronized(mutex) 
 * 
 * 3- También podemos hacer sincronizados métodos que sean estáticos aunque ahí la diferencia radica que no se usa el candado
 * de la instancia sino de la clase
 */
public class Step08Synchronized {

	public static void main(String[] args) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.deposit(80);

		Customer customer01 = new Customer(bankAccount);
		Customer customer02 = new Customer(bankAccount);

		Thread withdraw01 = new WithdrawTask(customer01, 50);
		Thread withdraw02 = new WithdrawTask(customer02, 40);

		withdraw01.start();
		withdraw02.start();
	}

	private static final class BankAccount {
		private double balance = 0;

		/*
		 * Si queremos que el ejemplo funcione de forma correcta solo cambiamos la firma de los métodos usando
		 * la keyword synchronized
		 */
		// public synchronized void deposit(double amount) {
		public void deposit(double amount) {
			if (amount > 0) {
				balance = balance + amount;
			}
		}

		// public synchronized double withdraw(double amount) {
		public double withdraw(double amount) {
			System.out.println(Thread.currentThread().getName() + " - Balance before condition: " + balance);
			if (amount > 0 && balance >= amount) {
				sleep();
				balance = balance - amount;
				System.out.println(Thread.currentThread().getName() + " - Balance after withdraw: " + balance);
				return balance;
			} else {
				throw new RuntimeException("Insufficient funds");
			}
		}

		private void sleep() {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static final class Customer {

		private BankAccount bankAccount;

		public Customer(BankAccount bankAccount) {
			this.bankAccount = bankAccount;
		}

		@SuppressWarnings("unused")
		public void deposit(double amount) {
			bankAccount.deposit(amount);
		}

		public double withdraw(double amount) {
			return bankAccount.withdraw(amount);
		}
	}

	private static final class WithdrawTask extends Thread {

		private Customer customer;
		private double amount;

		public WithdrawTask(Customer customer, double amount) {
			this.amount = amount;
			this.customer = customer;
		}

		@Override
		public void run() {
			customer.withdraw(amount);
		}
	}
}