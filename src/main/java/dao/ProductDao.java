package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;

public class ProductDao {

	private static final String SQL_SELECT_ALL = "SELECT p.product_id, p.name, p.price, c.c_name, p.updated_at FROM products p INNER JOIN categories c ON p.category_id = c.id ORDER BY ";
	private static final String SQL_SELECT_ID = "SELECT * FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE product_id = ?";
    private static final String SQL_SELECT_WHERE_NAME = "SELECT p.product_id, p.name, p.price, c.c_name FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE name LIKE ? OR c_name LIKE ? ORDER BY ";
    private static final String SQL_INSERT = "INSERT INTO products (product_id, name, price, category_id, image_path, description, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, current_timestamp, current_timestamp)";
    private static final String SQL_UPDATE = "UPDATE products SET product_id = ?, name = ?, price = ?, category_id = ?, image_path = ?, description = ?, updated_at = current_timestamp  WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM products WHERE product_id = ?";

    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public List<Product> findAll(String select) {
        List<Product> list = new ArrayList<Product>();

        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL + select)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	Product u = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("c_name"));
                list.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    
    public Product findById(Integer productId) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID)) {
        	stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	return new Product(rs.getInt("id"), rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getInt("category_id"), rs.getString("c_name"), rs.getString("image_path"), rs.getString("description"));
            }else {
            	return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Product> findByName(String name, String select) {
    	List<Product> findList = new ArrayList<Product>();
    	
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_NAME + select)) {
            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + name + "%");
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	Product u = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("c_name"));
                findList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findList;
    }
    
    public int insert(Product product) {
      try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
          stmt.setInt(1, product.getProductId());
          stmt.setString(2, product.getProductName());
          stmt.setInt(3, product.getPrice());
          stmt.setInt(4, product.getCategoryId());
          stmt.setString(5, product.getImagePath());
          stmt.setString(6, product.getDescription());

          return stmt.executeUpdate();
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
    }
    
    public int update(Product product) {
      try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
    	  stmt.setInt(1, product.getProductId());
    	  stmt.setString(2, product.getProductName());
          stmt.setInt(3, product.getPrice());
          stmt.setInt(4, product.getCategoryId());
          stmt.setString(5, product.getImagePath());
          stmt.setString(6, product.getDescription());
          stmt.setInt(7, product.getId());
          

          return stmt.executeUpdate();
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
    }
      
    public int delete(int productId) {
    try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
        stmt.setInt(1, productId);

        return stmt.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

  }
    
//    public List<Product> findByName(String name) {
//    	List<Product> findNameList = new ArrayList<Product>();
//    	
//        try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_WHERE_PRODUCT_NAME)) {
//            stmt.setString(1, "%" + name + "%");
//            
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//            	Product u = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getInt("price"), rs.getString("c_name"));
//                findNameList.add(u);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return findNameList;
//    }

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
