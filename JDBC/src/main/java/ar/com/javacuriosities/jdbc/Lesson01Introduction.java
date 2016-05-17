package ar.com.javacuriosities.jdbc;

import static ar.com.javacuriosities.jdbc.util.Constants.PASSWORD;
import static ar.com.javacuriosities.jdbc.util.Constants.URL;
import static ar.com.javacuriosities.jdbc.util.Constants.USER;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * El API de JDBC (Java Database Connectivity), nos permite interactuar con bases de datos
 * relacionales.
 * 
 * El API consta de 4 componentes claves:
 * 	- JDBC Drivers: Son el conjunto de clases que nos permite establecer una conexión con la DB, son las implementaciones de las interfaces JDBC
 * 	- Connections: Es la forma en que vamos a establecer una conexión con la DB, y la comunicación sucede a través de esta conexión
 * 	- Statements: Es la sentencia que enviaremos al motor para ejecutar nuestras consultas y actualizaciones
 * 	- ResultSets: Es la estructura que manejaremos para obtener los datos de la DB
 * 
 * Driver Types:
 * Hay cuatro tipo de Drivers, en general ya casi todas las DB tienen implementaciones del driver tipo 4.
 * 
 * 	- Type 1: Tiene una parte en Java que se conecta con un ODBC Bridge que se conecta al ODBC Driver y ahí a la DB
 * 	- Type 2: Tiene una parte en Java que se conecta con un driver nativo y ahí a la DB
 * 	- Type 3: Tiene una parte en Java que se conecta a un Middleware que hace la traducción y ahí a la DB
 * 	- Type 4: Todo el driver esta implementado en Java
 * 
 * Register JDBC Driver:
 * A la hora de usar un driver JDBC, debemos registrarlo esto se puede hacer de dos formas
 * 
 * 	- Reflection: Podemos usar "Class.forName("com.mysql.jdbc.Driver");", esto debería funcionar siempre que el Driver sea standard, si no lo es podemos probar con "Class.forName("com.mysql.jdbc.Driver").newInstance();"
 * 	- Manualmente: Podemos registrar el driver de forma manual
 * 		Driver driver = new com.mysql.jdbc.Driver();
 *		DriverManager.registerDriver(driver);
 * 
 * JDBC URL:
 * La URL para la conexion varia segun el motor de base de datos, algunos ejemplos son
 * 
 * 	- MySQL: jdbc:mysql://hostname/ databaseName
 * 	- Oracle: jdbc:oracle:thin:@hostname:port Number:databaseName
 * 	- DB2: jdbc:db2:hostname:port Number/databaseName
 * 	- Sybase: jdbc:sybase:Tds:hostname: port Number/databaseName
 * 
 * Nota:
 * Para los ejemplos se va a usar MySQL, asi que el motor debe estar instalado y corriendo, por defecto se usara user="root" password="root" en caso de ser
 * distinta hay que cambiarlo, si el motor fuera otro habría que cambiar el connector y la URL para la conexión
 * 
 */
public class Lesson01Introduction {
	public static void main(String[] args) {
		try {
			// Usamos Reflection para cargar la clase del Driver la cual tiene un bloque estático que registra el Driver
			Class.forName("com.mysql.jdbc.Driver");

			// Nos conectamos con la base de datos
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
				System.out.println("Connection open");
				
				// Por medio de la conexion podemos obtener metadata de la base y ejecutar algunas consultas
				DatabaseMetaData databaseMetaData = connection.getMetaData();
				
				int majorVersion = databaseMetaData.getDatabaseMajorVersion();
				int minorVersion = databaseMetaData.getDatabaseMinorVersion();

				String productName = databaseMetaData.getDatabaseProductName();
				String productVersion = databaseMetaData.getDatabaseProductVersion();
				
				System.out.println("Major Version: " + majorVersion);
				System.out.println("Minor Version: " + minorVersion);
				System.out.println("Product Name: " + productName);
				System.out.println("Product Version: " + productVersion);
				
				listingTables(databaseMetaData);
				listingColumnFromTable(databaseMetaData);
				primaryKeyFortable(databaseMetaData);
				checkSupportedFeatures(databaseMetaData);
			}
		} catch (Exception e) {
			// Log and Handle exception
			e.printStackTrace();
		}
	}

	private static void listingTables(DatabaseMetaData databaseMetaData) throws SQLException {
		// Podemos pasarle algunos parámetros para filtrar esta consulta
		ResultSet result = databaseMetaData.getTables(null, null, null, null);

		while (result.next()) {
			String tableName = result.getString(3);
			System.out.println(tableName);
		}
	}
	
	private static void listingColumnFromTable(DatabaseMetaData databaseMetaData) throws SQLException {
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = "alumnos";
		String columnNamePattern = null;

		ResultSet result = databaseMetaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);

		while (result.next()) {
			String columnName = result.getString(4);
			int columnType = result.getInt(5);
			
			System.out.println("Column Name: " + columnName + " Column Type: " + columnType);
		}
	}
	
	private static void primaryKeyFortable(DatabaseMetaData databaseMetaData) throws SQLException {
		String catalog = null;
		String schema = null;
		String tableName = "alumnos";

		ResultSet result = databaseMetaData.getPrimaryKeys(catalog, schema, tableName);

		while (result.next()) {
			String columnName = result.getString(4);
			System.out.println("Primary Key: " + columnName);
		}
	}
	
	private static void checkSupportedFeatures(DatabaseMetaData databaseMetaData) throws SQLException {
		System.out.println("Supports Get Generated Keys: " + databaseMetaData.supportsGetGeneratedKeys());
		System.out.println("Supports Group By: " + databaseMetaData.supportsGroupBy());
		System.out.println("Supports Outer Joins: " + databaseMetaData.supportsOuterJoins());
	}
}