package service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {

    public List<Product> authentication(String name) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            List<Product> product = productDao.findByName(name);

            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> find() {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
    public List<Product> findSerch(Integer categoryId) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findByCategoryId(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

}
