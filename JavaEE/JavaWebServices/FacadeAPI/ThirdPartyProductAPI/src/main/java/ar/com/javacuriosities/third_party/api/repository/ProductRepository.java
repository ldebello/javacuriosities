package ar.com.javacuriosities.third_party.api.repository;

import ar.com.javacuriosities.third_party.api.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static final String URL = "jdbc:postgresql://" + System.getenv("DB_HOST") + "/postgres?useSSL=false&requireSSL=false\";";
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Product p = new Product();

                p.setName(rs.getString("name"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getDouble("price"));

                products.add(p);
            }
        } catch (SQLException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
        return products;
    }
}
