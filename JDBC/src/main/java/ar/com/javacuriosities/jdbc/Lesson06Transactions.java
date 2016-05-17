package ar.com.javacuriosities.jdbc;

import static ar.com.javacuriosities.jdbc.util.Constants.PASSWORD;
import static ar.com.javacuriosities.jdbc.util.Constants.URL;
import static ar.com.javacuriosities.jdbc.util.Constants.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Una transacción es un conjunto de acciones que deben ser tratadas como una acción única o atomica.
 * Las bases de datos garantizan esto cumpliendo con ACID (Atomicity, Consistency, Isolation, Durability)
 * 
 * Para el manejo de las transacciones tenemos tres métodos claves
 * 
 * setAutoCommit: Nos permite indicar que vamos a comenzar una transacción, utilizando el auto commit en "false"
 * rollback: En caso de error podemos usar esto para dejar la base de datos en un estado consistente
 * commit: Nos permite confirmar el set de acciones que ejecutamos y que sean aplicados en la base
 * 
 */
public class Lesson06Transactions {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

				connection.setAutoCommit(false);
				try (Statement statement = connection.createStatement()) {

					// Ejecutamos un query que falle con una SQLException
					statement.execute("DELETE FROM alumnos WHERE id_alumnos = 1");
					connection.commit();
				} catch (SQLException ex) {
					connection.rollback();
					throw ex;
				}
			}
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}