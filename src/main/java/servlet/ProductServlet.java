package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Categories;
import entity.Product;
import service.CategoriesService;
import service.ProductService;
import util.ParamUtil;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	// 文字化け対策
        request.setCharacterEncoding("UTF-8");

        String seachText = request.getParameter("seach");
     // ログインチェック
        ProductService productService = new ProductService();
        CategoriesService categoriesService = new CategoriesService();
        Categories category = categoriesService.authentication(seachText);
        List<Product> product = new ArrayList<>();
        List<Categories> categories;
        
        if(category == null) {
        	product = productService.authentication(seachText);
        	
        }else {
        	categories = categoriesService.findId(seachText);
        	
        	for(int i = 0; i < categories.size(); i++) {
        		category = categories.get(i);
        		int categoryId = category.getId();
//        		product = productService.findSerch(categoryId);
        		product.addAll(productService.findSerch(categoryId));
        		
        		
        	}
//        	int categoryId = category.getId();
//        	product = productService.findSerch(seachText,categoryId);
        }
        

        
       
        if (ParamUtil.isNullOrEmpty(seachText)) {
            // メッセージ設定
        	product = productService.find();
        	System.out.println("ALL検索");
        	request.setAttribute("productList",product);
        	request.setAttribute("categoryList", category);
            // 次画面指定
            request.getRequestDispatcher("menu.jsp").forward(request, response);
            return;
            
        }

        
        

        // 表示メッセージの受け渡し
        if (product != null) {
        	System.out.println("検索");
        	session.setAttribute("productList", product);
        	request.setAttribute("categoryList", category);
            // 次画面指定
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else {
            // メッセージ設定
            request.setAttribute("msg", "IDまたはパスワードが不正です");

            // 次画面指定
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
