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
					<!-- 로그인 안 한 상태 -->
					<c:if test="${sessionScope.admin == null }">
					<br>
					<h2>관리자이시면 상단메뉴의<br>
						 로그인 페이지에서<br/> 
						로그인하세요. :)</h2>
					</c:if>
					
					<!-- 로그인 한 상태 -->
					<c:if test="${sessionScope.admin != null }">
					<br>
					<h2>어서오세요!<br></h2>
					<h2>관리자 페이지 입니다.<br></h2> 
					<h4>관리자 메뉴에서 할 일을 실행하세요:)</h4>
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
  
</body>

</html>
