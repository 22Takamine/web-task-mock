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

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	// 文字化け対策
        request.setCharacterEncoding("UTF-8");

        // ログイン
        String loginId = request.getParameter("loginId");
        String userName = request.getParameter("userName");
        String tel = request.getParameter("tel");
        String roleId = request.getParameter("roleId");
        String description = request.getParameter("description");
        String file = request.getParameter("file");
        int productId = 0;
        Integer cheakId = ParamUtil.checkAndParseInt(loginId);
        int price = 0;
        Integer categoryId = 0;
        int count = 0;

        // 入力値のチェック
        if (ParamUtil.isNullOrEmpty(loginId)) {
        	request.setAttribute("msgId", "商品IDは必須です");
        	count = 1;
        }else if(cheakId == null) {
        	request.setAttribute("msgId", "商品IDは数値です");
        	count = 1;
        }else {
        	productId = Integer.valueOf(loginId);
        }
        
        if (ParamUtil.isNullOrEmpty(userName)) {
        	request.setAttribute("msgName", "商品名は必須です");
        	count = 1;
        }
        
        if (ParamUtil.isNullOrEmpty(tel)) {
        	request.setAttribute("msgTel", "単価は必須です");
        	count = 1;
        }else {
        	price = Integer.valueOf(tel);
        }
        
        if (ParamUtil.isNullOrEmpty(roleId)) {
        	categoryId = null;
        	
        }else {
        	categoryId = Integer.valueOf(roleId);
        }
        
        if (ParamUtil.isNullOrEmpty(file)) {
        	request.setAttribute("msgFile", "画像は必須です");
        	//count = 1;
        }
        
        if(count == 1) {
        	request.getRequestDispatcher("insert.jsp").forward(request, response);
            return;
        	
        }
        
        Product product = new Product(productId, userName, price, categoryId,  file, description);
        
        ProductService productService = new ProductService();
        int insert = productService.insert(product);
        
        if(insert == 1) {
        	request.setAttribute("msgProductId", "成功しました");
        }else {
        	request.setAttribute("msgProductId", "商品IDが重複しています");
        }
        
        List<Product> productAll = productService.findAll();
        session.setAttribute("productList", productAll);
        request.getRequestDispatcher("insert.jsp").forward(request, response);
        
        

       
    }
  

}
