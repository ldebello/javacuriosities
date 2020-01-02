package ar.com.javacuriosities.lifecycle;

public class Bar {

	private int code;

	static {
		System.out.println("Bloque estático de Bar");
	}

	{
		System.out.println("Bloque común a todos los constructores de Bar");
	}

	public Bar() {
		System.out.println("Constructor de Bar");
	}

	public static void metodoEstatico() {
		System.out.println("Bloque método estático de Bar");
	}

	public int getCode() {
		return code;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Bloque finalize de Bar(" + hashCode() + ")");
	}
}
