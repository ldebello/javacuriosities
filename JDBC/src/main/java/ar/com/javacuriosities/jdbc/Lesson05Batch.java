package ar.com.javacuriosities.jdbc;

import static ar.com.javacuriosities.jdbc.util.Constants.PASSWORD;
import static ar.com.javacuriosities.jdbc.util.Constants.URL;
import static ar.com.javacuriosities.jdbc.util.Constants.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*
 * Podemos usar el modo batch cuando queremos preparar varias consultas y enviarlas todas juntas
 * para mejorar la performance de nuestra aplicacion
 */
public class Lesson05Batch {
	
	private static final int BATCH_SIZE = 10000;
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
				String SQL = "INSERT INTO bulk_table (data) " + "VALUES(?)";

				PreparedStatement preparedStatement = connection.prepareStatement(SQL);

				// Desactivamos el Auto-commit
				connection.setAutoCommit(false);
				
				for (int count = 1; count <= 100_000; count++) {
					// Asignamos las variables
					preparedStatement.setString(1, "Random Data " + count);

					// Agregamos el PreparedStatement al batch
					preparedStatement.addBatch();
					
					if(count % BATCH_SIZE == 0){
						// Devuelve un array con el update de cada query
						preparedStatement.executeBatch();
	                }
				}

				// Ejecutamos el commit
				connection.commit();
			}
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
}
