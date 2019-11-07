package ar.com.javacuriosities.jdbc;

import static ar.com.javacuriosities.jdbc.util.Constants.PASSWORD;
import static ar.com.javacuriosities.jdbc.util.Constants.URL;
import static ar.com.javacuriosities.jdbc.util.Constants.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/*
 * El ResultSet es la estructura de records que consumimos desde la DB, cada record tiene la misma
 * cantidad de columnas, aunque puede suceder que no todas las columnas tengan valor.
 * Este cuenta con algunos atributos importantes
 * 
 * - Type
 * - Concurrency
 * - Holdability
 * 
 * Dependiendo el motor de base de datos y el JDBC Driver puede ser que se soportan distintos valores
 * para estos atributos esto lo podemos verificar por medio de distintos métodos
 * 
 * 	- Type: Podemos verificar los valores soportados por medio de "DatabaseMetaData.supportsResultSetType(int type)"
 * 	- Concurrency: Podemos verificar los valores soportados por medio de "DatabaseMetaData.supportsResultSetConcurrency(int concurrency)"
 * 	- Holdability: Podemos verificar los valores soportados por medio de "DatabaseMetaData.supportsResultSetHoldability(int holdability)"
 * 
 * ResultSet Type:
 * 
 * 	- ResultSet.TYPE_FORWARD_ONLY: Es el valor por defecto y solo nos permite movernos para adelante en el ResultSet
 * 	- ResultSet.TYPE_SCROLL_INSENSITIVE: Podemos navegar por el ResultSet pero si los datos son modificados por otro thread en la DB no los veremos desde nuestro ResultSet
 * 	- ResultSet.TYPE_SCROLL_SENSITIVE: Podemos navegar por el ResultSet y si los datos son modificados por otro thread en la DB también serán actualizados en nuestro ResultSet
 * 
 * ResultSet Concurrency:
 * 	
 * 	- ResultSet.CONCUR_READ_ONLY: El ResultSet solo puede ser usado para lectura
 * 	- ResultSet.CONCUR_UPDATABLE: El ResultSet puede ser leído y actualizado (Para la actualización podemos usar los métodos "update" del ResultSet)
 * 
 * ResultSet Holdability:
 * 
 * 	- ResultSet.CLOSE_CURSORS_OVER_COMMIT: Esto indica que las conexiones son cerradas cuando ejecuto el commit
 * 	- ResultSet.HOLD_CURSORS_OVER_COMMIT:Esto indica que la conexion queda abierta luego de ejecutar el commit 
 * 
 */
public class Lesson03ResultSet {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
				String sql = "SELECT * FROM alumnos";
				
				// Utilizamos un Statement para ejecutar nuestra consulta
				Statement statement = connection.createStatement();
				
				try (ResultSet rs = statement.executeQuery(sql)) {
					// Podemos obtener metadata sobre el ResultSet
					ResultSetMetaData resultSetMetaData = rs.getMetaData();
					
					System.out.println("Number of columns: " + resultSetMetaData.getColumnCount());
					
					/* 
					 * Cada vez que se ejecuta el método next() sobre el ResultSet este verifica si tiene
					 * datos y si no tiene, intenta recuperar mas datos desde la DB, los datos son van
					 * recuperando en conjunto, el tamaño de este conjunto esta delimitado por el método
					 * "setFetchSize()", usualmente este valor ya viene configurado en el valor óptimo, pero
					 * en algunos casos puede ser útil cambiarlo
					 */
					while (rs.next()) {
						// Podemos acceder por nombre o por índice, empezando desde 1
						String name = rs.getString("nombre");
						double average = rs.getDouble("promedio");

						System.out.println("Name: " + name);
						System.out.println("Average: " + average);
					}
				}
			}
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
