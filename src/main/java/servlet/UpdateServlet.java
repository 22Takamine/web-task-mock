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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	// 文字化け対策
        request.setCharacterEncoding("UTF-8");
        Product oldProduct = (Product) session.getAttribute("detailDate");
        int id = oldProduct.getId();
        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String tel = request.getParameter("tel");
        String roleId = request.getParameter("roleId");
        String description = request.getParameter("description");
        String file = request.getParameter("file");
        int proId = 0;
        Integer cheakId = ParamUtil.checkAndParseInt(productId);
        int price = 0;
        Integer categoryId = 0;
        int count = 0;
        
        ProductService productService = new ProductService();
        proId = Integer.valueOf(productId);
    
     // 入力値のチェック
        if (ParamUtil.isNullOrEmpty(productId)) {
        	request.setAttribute("msgId", "商品IDは必須です");
        	count = 1;
        }else if(cheakId == null) {
        	request.setAttribute("msgId", "商品IDは数値です");
        	count = 1;
        }else if(productService.findById(cheakId) != null && proId != oldProduct.getProductId()) {
        	request.setAttribute("msgId", "商品IDが重複しています");
        	count = 1;
        }
        
        if (ParamUtil.isNullOrEmpty(productName)) {
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
        	request.getRequestDispatcher("updateInput.jsp").forward(request, response);
            return;
        	
        }
        

        Product product = new Product(id, proId, productName, price, categoryId,  file, description);
        
        
        int insert = productService.update(product);
        
        if(insert == 1) {
        	request.setAttribute("msgUpdate", "成功しました");
        }else {
        	request.setAttribute("msgUpdate", "失敗しました");
        }
        session.setAttribute("msg","更新処理が完了しました");
        request.getRequestDispatcher("updateInput.jsp").forward(request, response);
    }

}