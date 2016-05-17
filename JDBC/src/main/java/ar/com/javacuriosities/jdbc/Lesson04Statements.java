package ar.com.javacuriosities.jdbc;

import static ar.com.javacuriosities.jdbc.util.Constants.PASSWORD;
import static ar.com.javacuriosities.jdbc.util.Constants.URL;
import static ar.com.javacuriosities.jdbc.util.Constants.USER;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Tenemos 3 tipos de Statements
 * 
 * 	- Statement: Son usados para ejecutar las consultas contra la base
 * 	- PreparedStatement: También los utilizamos para ejecutar consultas contra la base, pero podemos reutilizarlos
 * 	- CallableStatement: Nos permite ejecutar Stored Procedures contra la base de datos
 */
public class Lesson04Statements {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			statement();
			preparedStatement();
			callableStatement();
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}
	
	private static void statement() throws SQLException {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM alumnos";
			
			// Utilizamos un Statement para ejecutar nuestra consulta
			Statement statement = connection.createStatement();
			
			try (ResultSet rs = statement.executeQuery(sql)) {
				while (rs.next()) {
					// Podemos acceder por nombre o por índice, empezando desde 1
					int id = rs.getInt("id_alumno");
					String name = rs.getString("nombre");
					double average = rs.getDouble("promedio");

					System.out.println("Id: " + id);
					System.out.println("Name: " + name);
					System.out.println("Average: " + average);
				}
			}
		}
	}
	
	private static void preparedStatement() throws SQLException {
		System.out.println("PreparedStatement");
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM alumnos WHERE id_alumno = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, 2);
			
			executeAndIterate(preparedStatement);
			
			// Aca estamos reutilizando el mismo PreparedStatement, esto nos ayuda a mejorar la performance de nuestra consulta
			preparedStatement.setInt(1, 4);
			
			executeAndIterate(preparedStatement);
		}
	}
	
	private static void callableStatement() throws SQLException {
		System.out.println("CallableStatement");
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			CallableStatement callableStatement = connection.prepareCall("{CALL GET_ALUMNO(?)}");
			callableStatement.setInt(1, 2);
			
			try (ResultSet rs = callableStatement.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id_alumno");
					String name = rs.getString("nombre");
					double average = rs.getDouble("promedio");

					System.out.println("Id: " + id);
					System.out.println("Name: " + name);
					System.out.println("Average: " + average);
				}
			}
		}
	}

	private static void executeAndIterate(PreparedStatement preparedStatement) throws SQLException {
		try (ResultSet rs = preparedStatement.executeQuery()) {
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
