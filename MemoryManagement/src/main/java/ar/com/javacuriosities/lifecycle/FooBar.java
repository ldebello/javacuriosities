package ar.com.javacuriosities.lifecycle;

public class FooBar {

	static {
		System.out.println("Bloque estático de FooBar");
	}

	{
		System.out.println("Bloque común a todos los constructores de FooBar");
	}

	public FooBar() {
		System.out.println("Constructor de FooBar");
	}

	public static void metodoEstatico() {
		System.out.println("Bloque método estático de FooBar");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Bloque finalize de FooBar(" + hashCode() + ")");
	}
}
