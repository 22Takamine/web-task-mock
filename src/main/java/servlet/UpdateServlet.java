package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import service.ProductService;
import util.ParamUtil;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	// 文字化け対策
        request.setCharacterEncoding("UTF-8");

        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String tel = request.getParameter("tel");
        String roleId = request.getParameter("roleId");
        String description = request.getParameter("description");
        String file = request.getParameter("file");
        
        int id = Integer.valueOf(productId);

        ProductService productService = new ProductService();
        Product product = productService.findById(id);
        
        
         if (ParamUtil.isNullOrEmpty(seachText)) {
            // メッセージ設定
        	product = productService.find();
        	System.out.println("ALL検索");
        	session.setAttribute("productList",product);
            // 次画面指定
            request.getRequestDispatcher("menu.jsp").forward(request, response);
            return;
            
        }else {
        	product = productService.findSerch(seachText);
        }

        
        

        // 表示メッセージの受け渡し
        if (product != null) {
        	System.out.println("検索");
        	session.setAttribute("productList", product);
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