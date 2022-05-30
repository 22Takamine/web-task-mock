package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Category;
import entity.Product;
import service.CategoryService;
import service.ProductService;
import util.ParamUtil;

@WebServlet("/Detail")
public class DetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	// 文字化け対策
        request.setCharacterEncoding("UTF-8");
        
        String productId = request.getParameter("productId");
        int id = 0;
        
        if(ParamUtil.isNullOrEmpty(productId)) {
        	System.out.println("detailエラー");
        }else {
        	id = Integer.valueOf(productId);
        }
        
        
        ProductService productService = new ProductService();
        Product product = productService.findById(id);
        CategoryService categoryService = new CategoryService();
        List<Category> category = categoryService.findAll();
        
        if(product == null) {
        	request.setAttribute("msg", "削除に失敗しました");
        }else {
        	session.setAttribute("categoryList", category);
        	session.setAttribute("detailDate", product);
        
        }
        request.getRequestDispatcher("detail.jsp").forward(request, response);
        
        
    }

}