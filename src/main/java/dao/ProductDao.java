package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {

    private static final String SQL_SELECT_ALL = "SELECT product_id, name, price, category_id FROM products ORDER BY product_id";
    private static final String SQL_SELECT_WHERE_CATEGORY_ID = "SELECT product_id, name, price, category_id FROM products WHERE category_id = ?";
    private static final String SQL_SELECT_WHERE_PRODUCT_NAME = "SELECT product_id, name, price, category_id FROM products WHERE name LIKE ? ";
//    private static final String SQL_INSERT = "INSERT INTO users (id, name, mail, pass) VALUES (?, ?, ?, ?)";
//    private static final String SQL_UPDATE = "UPDATE users SET name = ?, mail = ?, pass = ? WHERE id = ?";
//    private static final String SQL_DELETE = "DELETE FROM users WHERE id = ?";

    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList<Product>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	Product u = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getInt("category_id"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public List<Product> findByCategoryId(Integer categoryId) {
    	List<Product> findList = new ArrayList<Product>();
    	
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_CATEGORY_ID)) {
            stmt.setInt(1, categoryId);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	Product u = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getInt("category_id"));
                findList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findList;
    }
    
    public List<Product> findByName(String name) {
    	List<Product> findNameList = new ArrayList<Product>();
    	
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_PRODUCT_NAME)) {
            stmt.setString(1, "%" + name + "%");
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	Product u = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getInt("category_id"));
                findNameList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findNameList;
    }

//    public int insert(Product user) {
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
//            stmt.setInt(1, user.getUserId());
//            stmt.setString(2, user.getUserName());
//            stmt.setString(3, user.getMail());
//            stmt.setString(4, user.getPass());
//
//            return stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public int update(Product user) {
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
//            stmt.setString(1, user.getUserName());
//            stmt.setString(2, user.getMail());
//            stmt.setString(3, user.getPass());
//            stmt.setInt(4, user.getUserId());
//
//            return stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public int delete(int userId) {
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
//            stmt.setInt(1, userId);
//
//            return stmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
