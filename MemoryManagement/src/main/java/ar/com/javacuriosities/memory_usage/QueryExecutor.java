package ar.com.javacuriosities.memory_usage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryExecutor {

    // Constantes
    private final static String DB = "seminario_memoria";
    private final static String USER = "root";
    private final static String PASSWORD = "123456";
    private final static String HOST = "localhost";

    /*
     * En Java cuando deseamos conectar con una base de datos debemos crear una
     * URL de conexión cada URL va a ir variando entre las distintas base de
     * datos
     */
    private final static String URL = "jdbc:mysql://" + HOST + "/" + DB;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryExecutor.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public static ResultSet getData() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USER,
                    PASSWORD);

            Statement statement = conexion.createStatement();

            ResultsetKey key = new ResultsetKey(0, new String("Transactions"));

            ResultSet resultset = ResultsetHandler.getSingleInstance()
                    .getResulset(key);

            if (resultset == null) {
                resultset = statement
                        .executeQuery("SELECT client, symbol, currency, buy, volume, price, total FROM transactions");

                ResultsetHandler.getSingleInstance()
                        .addResulset(key, resultset);
            }

            return resultset;
        } catch (SQLException ex) {
            Logger.getLogger(QueryExecutor.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return null;
    }
}
