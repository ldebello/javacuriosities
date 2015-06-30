package ar.com.javacuriosities.references.util;

public class Foo {
	private String data;

	public Foo() {
	}

	public Foo(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	/*
	 * El método finalize es invocado por el GC cuando determina que no hay mas referencias al objeto.
	 * 
	 * - El método finalize será solo invocado una vez
	 * - La JVM no asegura que hilo llamara al método 
	 * - Se puede usar este método para revivir el objeto
	 * - Las excepciones no capturadas son ignoradas
	 */
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Foo finalize() method");
		super.finalize();
	}

	@Override
	public String toString() {
		return "This is Foo Object";
	}
}