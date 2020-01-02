package ar.com.javacuriosities.lifecycle;

public class Baz {

	static {
		System.out.println("Bloque estático de Baz");
	}

	{
		System.out.println("Bloque común a todos los constructores de Baz");
	}

	public Baz() {
		System.out.println("Constructor de Baz");
	}

	public static void metodoEstatico() {
		System.out.println("Bloque método estático de Baz");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Bloque finalize de Baz(" + hashCode() + ")");
	}
}
