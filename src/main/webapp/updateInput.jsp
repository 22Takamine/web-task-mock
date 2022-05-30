<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <div class="header">
    <h1 class="site_logo"><a href="menu.jsp">商品管理システム</a></h1>
    <div class="user">
      <p class="user_name">${user.getLoginId()}、こんにちは</p>
      <form class="logout_form" action="logout.jsp" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png">ログアウト</button>
      </form>
    </div>
  </div>

  <hr>

  <div class="insert">
    <div class="form_body">
      <p class="error">${msgUpdate}</p>

      <form action="update" method="post">
        <fieldset class="label-130">
          <div>
            <label>商品ID</label>
            <input type="number" name="productId" value="${detailDate.productId}" class="base-text">
            <span class="error">${msgId}</span>
          </div>
          <div>
            <label>商品名</label>
            <input type="text" name="productName" value="${detailDate.productName}" class="base-text">
            <span class="error">${msgName}</span>
          </div>
          <div>
            <label>単価</label>
            <input type="text" name="tel" value="${detailDate.price}" class="base-text">
            <span class="error">${msgTel}</span>
          </div>
          <div>
            <label>カテゴリ</label> <select name="roleId" class="base-text">
              <c:forEach var="category" items="${categoryList}">
                <c:if test="${detailDate.categoryId == category.id}" var="flg" />
                
                <c:if test="${flg}">
             	  <option value="${category.id}" selected>${category.name}</option>
                </c:if>
                
                <c:if test="${!flg}">
             	  <option value="${category.id}" >${category.name}</option>
                </c:if>
	               
              </c:forEach>
            </select>
          </div>
          <div>
            <label>商品説明</label>
            <textarea name="description" class="base-text">${detailDate.description}
            </textarea>
          </div>
          <div>
            <label>画像</label>
            <input type="file" name="file">
            <span class="error">${msgFile}</span>
          </div>
        </fieldset>
          <div class="btns">
            <button type="button" onclick="openModal()" class="basic_btn">更新</button>
            <input type="button" onclick="location.href='./menu.jsp'" value="メニューに戻る" class="cancel_btn">
          </div>
          <div id="modal">
            <p class="modal_message">更新しますか？</p>
            <div class="btns">
              <button type="submit" class="basic_btn">更新</button>
              <button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
            </div>
          </div>
      </form>
    </div>
  </div>
  <div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>