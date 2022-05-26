package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Category;

public class CategoryDao {

    private static final String SQL_SELECT_ALL = "SELECT id, c_name FROM categories ORDER BY id";

    private Connection connection;

    public CategoryDao(Connection connection) {
        this.connection = connection;
    }

    public List<Category> findAll() {
        List<Category> list = new ArrayList<Category>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category u = new Category(rs.getInt("id"), rs.getString("c_name"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

}