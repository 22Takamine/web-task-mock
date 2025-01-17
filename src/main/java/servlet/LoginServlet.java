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
import entity.User;
import service.ProductService;
import service.UserService;
import util.ParamUtil;

@WebServlet("/loginUser")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	// 文字化け対策
        request.setCharacterEncoding("UTF-8");

        // ログイン
        String loginId = request.getParameter("loginId");
        String pass = request.getParameter("pass");
        boolean authority = false;
        ProductService productService = new ProductService();
        List<Product> product = productService.findAll();

        // 入力値のチェック
        if (ParamUtil.isNullOrEmpty(loginId) || ParamUtil.isNullOrEmpty(pass)) {
            // メッセージ設定
        	if(ParamUtil.isNullOrEmpty(loginId)) {
        		request.setAttribute("msgId", "IDは必須です");
        	}
            
        	if(ParamUtil.isNullOrEmpty(pass)) {
        		request.setAttribute("msgPass", "PASSは必須です");
        	}

            // 次画面指定
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
            
        }

        // ログインチェック
        UserService userService = new UserService();
        User user = userService.authentication(loginId,pass);

        // 表示メッセージの受け渡し
        if (user != null) {
        	if(user.getRole() == 1) {
        		authority = true;
        	}
        	
            // 次画面指定
        	session.setAttribute("productList", product);
        	session.setAttribute("user", user);
        	session.setAttribute("authority",authority);
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else {
            // メッセージ設定
            request.setAttribute("msg", "IDまたはパスワードが不正です");

            // 次画面指定
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
