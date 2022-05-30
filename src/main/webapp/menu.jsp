<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
  <div id="app">

    <div class="header">
      <h1 class="site_logo"><a href="menu.jsp">商品管理システム</a></h1>
      <div class="user">
        <p class="user_name">${user.getLoginId()}</p>
        <form class="logout_form" action="logout.jsp" method="get">
          <button class="logout_btn" type="submit">
            <img src="images/ドアアイコン.png">ログアウト</button>
        </form>
      </div>
    </div>

    <hr>
	<c:if test="${authority}">
    <div class="btn"><a class="basic_btn regist" href="insert.jsp">新規登録</a></div>
    </c:if>
    <p>成功メッセージ</p>
    <form method="get" action="product">
      <div class="search_container">
	      <input type="text" size="25" name="seach" placeholder="キーワード検索">
	      <input type="submit" value="&#xf002">
      </div>
    
	    <table>
	        <div class="caption"><p>検索結果：${productList.size()}件</p></div>
	        <div class="order">
	          <select class="base-text" name="select">
	            <option value="product_id">並び替え</option>
	            <option value="product_id">商品ID</option>
	            <option value="category_id">カテゴリ</option>
	            <option value="price ASC">単価：安い順</option>
	            <option value="price DESC">単価：高い順</option>
	            <option value="updated_at ASC">登録日：古い順</option>
	            <option value="updated_at DESC">登録日：新しい順</option>
	          </select>
	        </div>
	      <thead>
	        <tr>
	          <th>商品ID</th>
	          <th>商品名</th>
	          <th>単価</th>
	          <th>カテゴリ</th>
	          <th>詳細</th>
	        </tr>
	      </thead>
	      <tbody>
	        	<c:forEach var="product" items="${productList}">
	          		<tr>
			            <td>${product.productId}</td>
			            <td>${product.productName}</td>
			            <td>${product.price}</td>
			            <td>${product.categoryName}</td>
			            <td><a class="detail_btn" href="./Detail?productId=${product.productId}">詳細</a></td>
	          		</tr>
	          	</c:forEach>
	      </tbody>
	    </table>
    </form>
  </div>
  <footer></footer>

</body>
</html>