package service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {

    public Product authentication() {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            Product product = productDao.findAll();

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
    
    public List<Product> findSerch(String id, int categoryId) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findByNameOrCategoryId(id, categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

}
