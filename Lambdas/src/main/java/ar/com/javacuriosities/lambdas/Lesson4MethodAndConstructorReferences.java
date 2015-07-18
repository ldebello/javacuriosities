package ar.com.javacuriosities.lambdas;

import java.io.File;
import java.io.FileFilter;

/*
 * Las referencias a métodos nos permite reutilizar un método como una Lambda Expression
 */
public class Lesson4MethodAndConstructorReferences {
	public static void main(String[] args) {
		/* 
		 * Esto es un Lambda complementamente valido pero dado que solo estamos llamando 
		 * un método del parámetro podemos usar referencias a métodos
		 */
		FileFilter filter = (File f) -> f.canRead();
		
		// Hacemos lo mismo que antes pero con una referencia a método
		FileFilter enhancedFilter = File::canRead;
		
		/*
		 * Hay 3 tipos de referencias a métodos:
		 * - Métodos estáticos
		 * - Métodos de un tipo
		 * - Métodos de un objeto existente 
		 */		
	}
}