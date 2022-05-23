package service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import dao.UserDao;
import entity.User;
import util.DbUtil;

public class UserService {

    public User authentication(String loginId, String password) {
        try (Connection conn = DbUtil.getConnection()) {
            UserDao userDao = new UserDao(conn);
//            User user = userDao.findByLoginIdAndPass(loginId,password);
            User user = (User) userDao.findByLoginIdAndPass(loginId,password);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> find() {
        try (Connection conn = DbUtil.getConnection()) {
        	UserDao userDao = new UserDao(conn);
            return userDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
//    public List<User> findSerch(int id, int price) {
//        try (Connection conn = DbUtil.getConnection()) {
//            ProductDao productDao = new ProductDao(conn);
//            return productDao.findSerch(id,price);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return Collections.emptyList();
//    }

}