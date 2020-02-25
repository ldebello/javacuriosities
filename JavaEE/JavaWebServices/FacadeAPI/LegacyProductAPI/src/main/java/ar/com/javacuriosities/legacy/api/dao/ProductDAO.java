package ar.com.javacuriosities.legacy.api.dao;

import ar.com.javacuriosities.legacy.api.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String URL = "jdbc:mysql://" + System.getenv("DB_HOST") + "/legacy?useSSL=false";
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Log and Handle exception
            e.printStackTrace();
        }
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Product p = new Product();

                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
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
