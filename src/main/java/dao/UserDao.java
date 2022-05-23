package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class UserDao {

    private static final String SQL_SELECT_ALL = "SELECT id, login_id, password, name, role FROM users ORDER BY id";
    private static final String SQL_SELECT_WHERE_USER_ID_AND_PASS = "SELECT id, login_id, password, name, role FROM users WHERE login_id = ? AND password = ?";
    //private static final String SQL_INSERT = "INSERT INTO users (id, name, mail, pass) VALUES (?, ?, ?, ?)";
    //private static final String SQL_UPDATE = "UPDATE users SET name = ?, mail = ?, pass = ? WHERE id = ?";
    //private static final String SQL_DELETE = "DELETE FROM users WHERE id = ?";

    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<User>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User u = new User(rs.getInt("id"), rs.getString("loginid"), rs.getString("password"), rs.getString("name"), rs.getInt("role"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public User findByLoginIdAndPass(String loginId, String password) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_USER_ID_AND_PASS)) {
            stmt.setString(1, loginId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("login_id"), rs.getString("password"), rs.getString("name"), rs.getInt("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

//    public int insert(User user) {
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
//    public int update(User user) {
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
