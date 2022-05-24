package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Categories;

public class CategoriesDao {

    private static final String SQL_SELECT_ALL = "SELECT id, name FROM categories ORDER BY id";
    private static final String SQL_SELECT_WHERE_ID = "SELECT id, name FROM categories WHERE id = ?";
    private static final String SQL_SELECT_WHERE_NAME = "SELECT id, name FROM categories WHERE name LIKE ?";
    //private static final String SQL_INSERT = "INSERT INTO users (id, name, mail, pass) VALUES (?, ?, ?, ?)";
    //private static final String SQL_UPDATE = "UPDATE users SET name = ?, mail = ?, pass = ? WHERE id = ?";
    //private static final String SQL_DELETE = "DELETE FROM users WHERE id = ?";

    private Connection connection;

    public CategoriesDao(Connection connection) {
        this.connection = connection;
    }

    public List<Categories> findAll() {
        List<Categories> list = new ArrayList<Categories>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categories u = new Categories(rs.getInt("id"), rs.getString("name"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public Categories findById(String name) {
    	try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_NAME)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Categories(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    public Categories findName(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            
            if (rs.next()) {
                return new Categories(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return null;
        
    }
    
    public List<Categories> findId(String name) {
    	List<Categories> list = new ArrayList<Categories>();
    	
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_NAME)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categories u = new Categories(rs.getInt("id"), rs.getString("name"));
                list.add(u);
            }
//            if (rs.next()) {
//                return new Categories(rs.getInt("id"), rs.getString("name"));
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return list;
        
    }
    




}

