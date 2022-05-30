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
import util.ParamUtil;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	// 文字化け対策
        request.setCharacterEncoding("UTF-8");

        String seachText = request.getParameter("seach");
        String select = request.getParameter("select");
        
     // ログインチェック
        ProductService productService = new ProductService();
        List<Product> product = null;
        
        
         if (ParamUtil.isNullOrEmpty(seachText)) {
            // メッセージ設定
        	product = productService.find(select);
        	System.out.println("ALL検索");
        	session.setAttribute("productList",product);
            // 次画面指定
            request.getRequestDispatcher("menu.jsp").forward(request, response);
            return;
            
        }else {
        	product = productService.findSerch(seachText,select);
        }
        
         
        // 表示メッセージの受け渡し
        if (product != null) {
        	System.out.println("検索");
        	session.setAttribute("productList", product);
        	if(product.size() == 0) {
        		request.setAttribute("msg", "見つかりません");
        	}
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
