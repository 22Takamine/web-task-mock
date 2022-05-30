package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import service.ProductService;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	// 文字化け対策
    	request.setCharacterEncoding("UTF-8");
        
    	Product product = (Product)session.getAttribute("detailDate");
    	int id = product.getProductId();
        
        ProductService productService = new ProductService();
        productService.delete(id);
        
        List<Product> productAll = productService.findAll();
        session.setAttribute("productList", productAll);
        
        request.getRequestDispatcher("menu.jsp").forward(request, response);
        
        
    }
  

}