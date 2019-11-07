package ar.com.javacuriosities.jdbc;

import static ar.com.javacuriosities.jdbc.util.Constants.PASSWORD;
import static ar.com.javacuriosities.jdbc.util.Constants.URL;
import static ar.com.javacuriosities.jdbc.util.Constants.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Vamos a usar un CRUD (Create-Retrieval-Update-Delete) para ver las distintas consultas que podemos ejecutar
 * 
 */
public class Lesson02CRUD {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			insert();
			select();
			update();
			delete();
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static void select() throws SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM alumnos";
			
			// Utilizamos un Statement para ejecutar nuestra consulta
			Statement statement = connection.createStatement();
			
			try (ResultSet rs = statement.executeQuery(sql)) {
				while (rs.next()) {
					// Podemos acceder por nombre o por índice, empezando desde 1
					String name = rs.getString("nombre");
					double average = rs.getDouble("promedio");

					System.out.println("Name: " + name);
					System.out.println("Average: " + average);
				}
			}
		}
	}
	
	private static void insert() throws SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			Statement statement = connection.createStatement();

			String sql = "INSERT INTO alumnos (nombre, apellido, dni, fecha_nacimiento) VALUES ('" + "Name" + "', '" + "LastName" + "', '" + "12345678" + "', '1985-06-05')";

			/*
			 * Cuando ejecutamos el query podemos pasar el segundo parámetro como "Statement.RETURN_GENERATED_KEYS" 
			 * para obtener el ID auto-generado, siempre y cuando el motor soporte esto, podemos verificar esto
			 * por medio de DatabaseMetaData, si no pasamos el segundo parámetro no podemos obtener
			 * el ID auto-generado
			 */
			int rowsAffected = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			System.out.println(rowsAffected + " Row Inserted");
			while (generatedKeys.next()) {
				System.out.println("Last Id: " + generatedKeys.getInt(1));
			}
		}
	}
	
	private static void update() throws SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			Statement statement = connection.createStatement();

			int maxId = getMaxId(statement);
			
			String sql = "UPDATE alumnos SET nombre = '" + "NewName" + "', apellido = '" + "NewLastName" + "' WHERE id_alumno = " + maxId;
			
			int rowsAffected = statement.executeUpdate(sql);
			System.out.println(rowsAffected + " Row Updated");
		}
	}

	private static void delete() throws SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			Statement statement = connection.createStatement();

			int maxId = getMaxId(statement);
			
			String sql = "DELETE FROM alumnos WHERE id_alumno = " + maxId;
			
			int rowsAffected = statement.executeUpdate(sql);
			System.out.println(rowsAffected + " Row Deleted");
		}
	}
	
	private static int getMaxId(Statement statement) throws SQLException {
		int maxId = -1;
		ResultSet rs = statement.executeQuery("SELECT MAX(id_alumno) AS max_id FROM alumnos");
		while(rs.next()) {
			maxId = rs.getInt("max_id");
		}
		return maxId;
	}
}