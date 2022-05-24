package service;

import java.sql.Connection;
import java.util.List;

import dao.CategoriesDao;
import entity.Categories;
import util.DbUtil;

public class CategoriesService {

    public Categories authentication(String name) {
        try (Connection conn = DbUtil.getConnection()) {
            CategoriesDao categoriesDao = new CategoriesDao(conn);
            Categories categories = categoriesDao.findById(name);

            return categories;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public Categories findName(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            CategoriesDao categoriesDao = new CategoriesDao(conn);
            Categories categories = categoriesDao.findName(id);
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<Categories> findId(String name) {
        try (Connection conn = DbUtil.getConnection()) {
            CategoriesDao categoriesDao = new CategoriesDao(conn);
            List<Categories> categories = categoriesDao.findId(name);
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}