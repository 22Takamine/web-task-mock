package service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {
	
	public List<Product> findAll() {
		String select = "product_id";
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findAll(select);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public List<Product> find(String select) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findAll(select);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
    public Product findById(Integer productId) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findById(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public List<Product> findSerch(String name, String select) {
        try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.findByName(name, select);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
    
    public int insert(Product product) {
    	try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.insert(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    	
    }
    
    public int update(Product product) {
    	try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.update(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    	
    }
    
    public int delete(int productId) {
    	try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            return productDao.delete(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    	
    }

}
