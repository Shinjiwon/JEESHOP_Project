<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>JEESHOP 관리자 페이지</title>

  <!-- CSS -->
  <%@include file="/WEB-INF/views/include/admincss.jsp" %>
  
  <script>
  	if("${msg}"=="LOGIN_SUCCESS"){
  		alert("로그인 되었습니다.");
  		
  	} else if("${msg}"=="LOGIN_FAIL"){
  		alert("로그인에 실패하였습니다. \n아이디와 비밀번호를 다시 입력하세요.");
  		
  	} else if("${msg}"=="LOGOUT_SUCCESS"){
  		alert("로그아웃되었습니다.");
  		
  	}
  </script>
</head>

<body>
  <!-- 전체 페이지 -->
  <div id="wrapper">

    <!-- 사이드 메뉴 -->
    <%@include file="/WEB-INF/views/include/left_admin.jsp" %>

    <!-- 메인 페이지 -->
    <div id="content-wrapper" class="d-flex flex-column">
      <div id="content">

        <!-- Header-->
        <%@include file="/WEB-INF/views/include/header_admin.jsp" %>

        <!-- 주요 내용 -->
        
        <div class="container-fluid">
			<div class="container" style="width: 450px; height:520px; margin-top:30px;">
					<c:if test="${sessionScope.admin == null }">
					<form id="adminloginForm" class="form-signin" action="/admin/login" method="post" style="padding:50px 30px;">
						<h2 class="form-signin-heading">관리자 로그인</h2>
						<br><br>
						
						<label for="inputId" class="sr-only">아이디</label> 
						<input type="text" id="admin_id" name="admin_id" class="form-control" style="margin-bottom: 15px"
							placeholder="아이디" required autofocus> 
							
						<label for="inputPassword" class="sr-only">비밀번호</label> 
						<input type="password" id="admin_pw" name="admin_pw" class="form-control"
							placeholder="비밀번호" required>
						<br><br><br>
						
						<button id="btn_login" class="btn btn-lg btn-primary btn-block" type="submit">
							로그인
						</button>
					</form>
					</c:if>
				</div>
        </div>
        <!-- 주요 내용  -->

      </div>

      <!-- Footer -->
      <%@include file="/WEB-INF/views/include/footer_admin.jsp" %>

    </div>
    <!-- 메인 페이지 -->
  </div>
  <!-- 전체 페이지 -->

  <!-- JS -->
  <%@include file="/WEB-INF/views/include/adminjs.jsp" %>
  
  <!-- 유효성 검사 -->
  <script type="text/javascript" src="/js/admin/login.js"></script>
</body>

</html>
