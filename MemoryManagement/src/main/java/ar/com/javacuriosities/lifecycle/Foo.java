package ar.com.javacuriosities.lifecycle;

public class Foo extends Bar {

	static {
		System.out.println("Bloque estático de Foo");
	}

	{
		System.out.println("Bloque común a todos los constructores de Foo");
	}

	public Foo() {
		System.out.println("Constructor de Foo");
	}

	public static void metodoEstatico() {
		System.out.println("Bloque método estático de Foo");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Bloque finalize de Foo(" + hashCode() + ")");
	}
}
